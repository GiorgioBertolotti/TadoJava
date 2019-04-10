import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TadoConnector {
	private String username;
	private String password;
	private String clientSecret;
	private String bearer;
	private String refreshToken;
	private OkHttpClient client;
	private boolean initialized = false;
	private boolean debug = false;
	public static final MediaType FORM = MediaType.parse("multipart/form-data");

	public TadoConnector(String username, String password, String clientSecret) {
		this.username = username;
		this.password = password;
		this.clientSecret = clientSecret;
	}

	public TadoConnector(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void initialize() throws TadoException {
		if (!initialized) {
			client = new OkHttpClient();
			if (this.clientSecret == null)
				this.clientSecret = getClientSecret();
			getBearerTokens();
			this.initialized = true;
		}
	}

	public void refresh() {
		if (initialized) {
			// TODO: refreshBearerTokens();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBearer() {
		return bearer;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	private String getClientSecret() {
		try {
			String jsonResponse = doGetRequest("https://my.tado.com/webapp/env.js", null);
			debugMessage("getClientSecret response: " + jsonResponse);
			jsonResponse = jsonResponse.substring(9).trim();
			jsonResponse = jsonResponse.substring(0, jsonResponse.length() - 1).trim();
			JSONObject json = new JSONObject(jsonResponse);
			return json.getJSONObject("config").getJSONObject("oauth").optString("clientSecret");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void getBearerTokens() throws TadoException {
		Map<String, String> body = new HashMap<>();
		body.put("client_id", "tado-web-app");
		body.put("grant_type", "password");
		body.put("scope", "home.user");
		body.put("username", this.username);
		body.put("password", this.password);
		body.put("client_secret", this.clientSecret);
		try {
			String response = doPostRequest("https://auth.tado.com/oauth/token", body, null);
			debugMessage("getBearerTokens response: " + response);
			JSONObject json = new JSONObject(response);
			checkException(json);
			this.bearer = json.optString("access_token");
			this.refreshToken = json.optString("refresh_token");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Integer> getHomesIDs() throws TadoException {
		if (!this.initialized)
			throw new TadoException("error", "You must initialize the TadoConnector");
		return _getHomesIDs(0);
	}

	public List<Integer> _getHomesIDs(int attempt) throws TadoException {
		List<Integer> toReturn = new ArrayList<>();
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + this.bearer);
			String jsonResponse = doGetRequest("https://my.tado.com/api/v2/me", headers);
			debugMessage("getHomesIDs response: " + jsonResponse);
			JSONObject json = new JSONObject(jsonResponse);
			checkException(json);
			for (Object o : json.getJSONArray("homes")) {
				if (o instanceof JSONObject) {
					JSONObject home = (JSONObject) o;
					toReturn.add(home.getInt("id"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TadoException e) {
			if (attempt > 1) {
				throw e;
			} else {
				refresh();
				toReturn = _getHomesIDs(attempt + 1);
			}
		}
		return toReturn;
	}

	public List<TadoHome> getHomes() throws TadoException {
		if (!this.initialized)
			throw new TadoException("error", "You must initialize the TadoConnector");
		return _getHomes(0);
	}

	private List<TadoHome> _getHomes(int attempt) throws TadoException {
		List<TadoHome> toReturn = new ArrayList<>();
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + this.bearer);
			String jsonResponse = doGetRequest("https://my.tado.com/api/v2/me", headers);
			debugMessage("getHomes response: " + jsonResponse);
			JSONObject json = new JSONObject(jsonResponse);
			checkException(json);
			for (Object o : json.getJSONArray("homes")) {
				if (o instanceof JSONObject) {
					JSONObject jsonHome = (JSONObject) o;
					toReturn.add(_getHome(jsonHome.getInt("id"), 0));

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TadoException e) {
			if (attempt > 1) {
				throw e;
			} else {
				refresh();
				toReturn = _getHomes(attempt + 1);
			}
		}
		return toReturn;
	}

	public TadoHome getHome(int id) throws TadoException {
		if (!this.initialized)
			throw new TadoException("error", "You must initialize the TadoConnector");
		return _getHome(id, 0);
	}

	private TadoHome _getHome(int id, int attempt) throws TadoException {
		TadoHome toReturn = null;
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + this.bearer);
			String jsonResponse = doGetRequest("https://my.tado.com/api/v2/homes/" + id, headers);
			debugMessage("getHome response: " + jsonResponse);
			JSONObject json = new JSONObject(jsonResponse);
			checkException(json);
			Date dateCreated = Date.from(Instant.parse(json.optString("dateCreated")));
			JSONObject jsonContactDetails = json.getJSONObject("contactDetails");
			TadoContact contactDetails = new TadoContact(jsonContactDetails.optString("name"),
					jsonContactDetails.optString("email"), jsonContactDetails.optString("phone"));
			JSONObject jsonAddress = json.getJSONObject("address");
			TadoAddress address = new TadoAddress(jsonAddress.optString("addressLine1"),
					jsonAddress.optString("addressLine2"), jsonAddress.optString("zipCode"),
					jsonAddress.optString("city"), jsonAddress.optString("state"), jsonAddress.optString("country"));
			JSONObject jsonGeolocation = json.getJSONObject("geolocation");
			TadoGeolocation geolocation = new TadoGeolocation(jsonGeolocation.optDouble("latitude"),
					jsonGeolocation.optDouble("longitude"));
			toReturn = new TadoHome(json.getInt("id"), json.optString("name"), json.optString("dateTimeZone"),
					dateCreated, json.optString("temperatureUnit"), json.optBoolean("installationCompleted"),
					json.optBoolean("simpleSmartScheduleEnabled"), json.optDouble("awayRadiusInMeters"),
					json.optBoolean("usePreSkillsApps"), json.optBoolean("christmasModeEnabled"), contactDetails,
					address, geolocation, json.optBoolean("consentGrantSkippable"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TadoException e) {
			if (attempt > 1) {
				throw e;
			} else {
				refresh();
				toReturn = _getHome(id, attempt + 1);
			}
		}
		return toReturn;
	}

	public List<TadoZone> getZones(TadoHome tadoHome) throws TadoException {
		return getZones(tadoHome.getId());
	}

	public List<TadoZone> getZones(int idHome) throws TadoException {
		if (!this.initialized)
			throw new TadoException("error", "You must initialize the TadoConnector");
		return _getZones(idHome, 0);
	}

	private List<TadoZone> _getZones(int idHome, int attempt) throws TadoException {
		List<TadoZone> toReturn = new ArrayList<>();
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + this.bearer);
			String jsonResponse = doGetRequest("https://my.tado.com/api/v2/homes/" + idHome + "/zones", headers);
			debugMessage("getZones response: " + jsonResponse);
			try {
				// IF IT CAN PARSE THE JSONOBJECT IT WILL PROBABLY BE AN EXCEPTION
				JSONObject json = new JSONObject(jsonResponse);
				checkException(json);
			} catch (JSONException e) {
				// IF IT CANNOT PARSE THE JSONOBJECT IT WILL BE AN ARRAY OF ZONES, WHICH IS THE
				// EXPECTED RESULT
				JSONArray jsonArray = new JSONArray(jsonResponse);
				for (Object obj : jsonArray) {
					if (obj instanceof JSONObject) {
						JSONObject jsonZone = (JSONObject) obj;
						Date dateCreated = Date.from(Instant.parse(jsonZone.optString("dateCreated")));
						JSONArray jsonDeviceTypes = jsonZone.getJSONArray("deviceTypes");
						List<String> deviceTypes = new ArrayList<>();
						for (Object deviceType : jsonDeviceTypes) {
							if (deviceType instanceof String)
								deviceTypes.add((String) deviceType);
						}
						JSONArray jsonDevices = jsonZone.getJSONArray("devices");
						List<TadoDevice> devices = new ArrayList<>();
						for (Object jsonDevice : jsonDevices) {
							if (jsonDevice instanceof JSONObject) {
								JSONObject device = (JSONObject) jsonDevice;
								JSONObject jsonConnectionState = device.getJSONObject("connectionState");
								Date timestamp = Date.from(Instant.parse(jsonConnectionState.optString("timestamp")));
								TadoConnectionState connsectionState = new TadoConnectionState(
										jsonConnectionState.getBoolean("value"), timestamp);
								JSONArray jsonCapabilities = device.getJSONObject("characteristics")
										.getJSONArray("capabilities");
								List<String> capabilities = new ArrayList<>();
								for (Object capability : jsonCapabilities) {
									if (capability instanceof String)
										capabilities.add((String) capability);
								}
								JSONArray jsonDuties = device.getJSONArray("duties");
								List<String> duties = new ArrayList<>();
								for (Object duty : jsonDuties) {
									if (duty instanceof String)
										duties.add((String) duty);
								}
								TadoDevice toAdd = new TadoDevice(device.optString("deviceType"),
										device.optString("serialNo"), device.optString("shortSerialNo"),
										device.optString("currentFwVersion"), connsectionState, capabilities,
										device.optString("batteryState"), duties);
								devices.add(toAdd);
							}
						}
						JSONObject jsonDazzleMode = jsonZone.getJSONObject("dazzleMode");
						TadoDazzleMode dazzleMode = new TadoDazzleMode(jsonDazzleMode.getBoolean("supported"),
								jsonDazzleMode.getBoolean("enabled"));
						JSONObject jsonOpenWindowDetection = jsonZone.getJSONObject("openWindowDetection");
						TadoOpenWindowDetection openWindowDetection = new TadoOpenWindowDetection(
								jsonOpenWindowDetection.getBoolean("supported"),
								jsonOpenWindowDetection.getBoolean("enabled"),
								jsonOpenWindowDetection.getInt("timeoutInSeconds"));
						TadoZone zone = new TadoZone(idHome, jsonZone.getInt("id"), jsonZone.optString("name"),
								jsonZone.optString("type"), dateCreated, deviceTypes, devices,
								jsonZone.getBoolean("reportAvailable"), jsonZone.getBoolean("supportsDazzle"),
								jsonZone.getBoolean("dazzleEnabled"), dazzleMode, openWindowDetection);
						toReturn.add(zone);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TadoException e) {
			if (attempt > 1) {
				throw e;
			} else {
				refresh();
				toReturn = _getZones(idHome, attempt + 1);
			}
		}
		return toReturn;
	}

	public TadoState getHomeState(TadoHome tadoHome) throws TadoException {
		return getHomeState(tadoHome.getId());
	}

	public TadoState getHomeState(int idHome) throws TadoException {
		if (!this.initialized)
			throw new TadoException("error", "You must initialize the TadoConnector");
		return _getHomeState(idHome, 0);
	}

	private TadoState _getHomeState(int idHome, int attempt) throws TadoException {
		TadoState toReturn = null;
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + this.bearer);
			String jsonResponse = doGetRequest("https://my.tado.com/api/v2/homes/" + idHome + "/state", headers);
			debugMessage("getHomeState response: " + jsonResponse);
			JSONObject json = new JSONObject(jsonResponse);
			checkException(json);
			toReturn = new TadoState(json.optString("presence"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TadoException e) {
			if (attempt > 1) {
				throw e;
			} else {
				refresh();
				toReturn = _getHomeState(idHome, attempt + 1);
			}
		}
		return toReturn;
	}

	public TadoZoneState getZoneState(TadoZone tadoZone) throws TadoException {
		return getZoneState(tadoZone.getHomeId(), tadoZone.getId());
	}

	public TadoZoneState getZoneState(int idHome, int idZone) throws TadoException {
		if (!this.initialized)
			throw new TadoException("error", "You must initialize the TadoConnector");
		return _getZoneState(idHome, idZone, 0);
	}

	private TadoZoneState _getZoneState(int idHome, int idZone, int attempt) throws TadoException {
		TadoZoneState toReturn = null;
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + this.bearer);
			String jsonResponse = doGetRequest(
					"https://my.tado.com/api/v2/homes/" + idHome + "/zones/" + idZone + "/state", headers);
			debugMessage("getZoneState response: " + jsonResponse);
			JSONObject json = new JSONObject(jsonResponse);
			checkException(json);
			Date geolocationOverrideDisableTime;
			if (json.optString("geolocationOverrideDisableTime").isEmpty())
				geolocationOverrideDisableTime = null;
			else
				geolocationOverrideDisableTime = Date
						.from(Instant.parse(json.optString("geolocationOverrideDisableTime")));
			JSONObject jsonSetting = json.getJSONObject("setting");
			JSONObject jsonTemperature = jsonSetting.getJSONObject("temperature");
			TadoTemperature temperature = new TadoTemperature(jsonTemperature.optDouble("celsius"),
					jsonTemperature.optDouble("fahrenheit"));
			TadoSetting setting = new TadoSetting(jsonSetting.optString("type"), jsonSetting.optString("power"),
					temperature);
			JSONObject jsonScheduleChange = json.optJSONObject("nextScheduleChange");
			TadoScheduleChange nextScheduleChange = null;
			if (jsonScheduleChange != null) {
				Date start = Date.from(Instant.parse(jsonScheduleChange.optString("start")));
				JSONObject jsonSetting2 = jsonScheduleChange.getJSONObject("setting");
				JSONObject jsonTemperature2 = jsonSetting2.getJSONObject("temperature");
				TadoTemperature temperature2 = new TadoTemperature(jsonTemperature2.optDouble("celsius"),
						jsonTemperature2.optDouble("fahrenheit"));
				TadoSetting setting2 = new TadoSetting(jsonSetting2.optString("type"), jsonSetting2.optString("power"),
						temperature2);
				nextScheduleChange = new TadoScheduleChange(start, setting2);
			}
			JSONObject jsonActivityDataPoints = json.optJSONObject("activityDataPoints");
			List<TadoDataPoint> activityDataPoints = new ArrayList<>();
			if (jsonActivityDataPoints != null) {
				Iterator<String> keys = jsonActivityDataPoints.keys();
				while (keys.hasNext()) {
					String name = keys.next();
					JSONObject datapoint = null;
					if (jsonActivityDataPoints.get(name) instanceof JSONObject) {
						datapoint = jsonActivityDataPoints.getJSONObject(name);
					}
					activityDataPoints.add(new TadoDataPoint(name, datapoint));
				}
			}
			JSONObject jsonSensorDataPoints = json.optJSONObject("sensorDataPoints");
			List<TadoDataPoint> sensorDataPoints = new ArrayList<>();
			if (jsonActivityDataPoints != null) {
				Iterator<String> keys = jsonSensorDataPoints.keys();
				while (keys.hasNext()) {
					String name = keys.next();
					JSONObject datapoint = null;
					if (jsonSensorDataPoints.get(name) instanceof JSONObject) {
						datapoint = jsonSensorDataPoints.getJSONObject(name);
					}
					sensorDataPoints.add(new TadoDataPoint(name, datapoint));
				}
			}
			toReturn = new TadoZoneState(json.optString("tadoMode"), json.getBoolean("geolocationOverride"),
					geolocationOverrideDisableTime, setting, nextScheduleChange,
					json.getJSONObject("link").getString("state"), activityDataPoints, sensorDataPoints);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TadoException e) {
			if (attempt > 1) {
				throw e;
			} else {
				refresh();
				toReturn = _getZoneState(idHome, idZone, attempt + 1);
			}
		}
		return toReturn;
	}

	private void checkException(JSONObject json) throws TadoException {
		if (json.has("errors")) {
			JSONArray errorsJson = json.getJSONArray("errors");
			JSONObject errorJson = errorsJson.getJSONObject(0);
			throw new TadoException(errorJson.optString("code"), errorJson.optString("title"));
		}
	}

	private void debugMessage(String message) {
		if (this.debug) {
			System.out.println("[TADO_JAVA_DEBUG] " + message);
		}
	}

	private String doGetRequest(String url, Map<String, String> headers) throws IOException {
		Request request;
		if (headers != null)
			request = new Request.Builder().url(url).headers(Headers.of(headers)).build();
		else
			request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	private String doPostRequest(String url, Map<String, String> body, Map<String, String> headers) throws IOException {
		Builder builder = new FormBody.Builder();
		for (Map.Entry<String, String> entry : body.entrySet()) {
			builder.add(entry.getKey(), entry.getValue());
		}
		FormBody formBody = builder.build();
		Request request;
		if (headers != null)
			request = new Request.Builder().url(url).post(formBody).headers(Headers.of(headers)).build();
		else
			request = new Request.Builder().url(url).post(formBody).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
}