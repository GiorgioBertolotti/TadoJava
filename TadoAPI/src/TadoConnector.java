import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TadoConnector {
	private String username;
	private String password;
	private String clientSecret;
	private String bearer;
	private String refreshToken;
	private int homeID;
	private int zone;
	private OkHttpClient client;
	public static final MediaType FORM = MediaType.parse("multipart/form-data");

	public TadoConnector(String username, String password) {
		this.username = username;
		this.password = password;
		client = new OkHttpClient();
		this.clientSecret = getClientSecret();
		getBearerTokens();
		this.homeID = getHomeID();
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

	public int getHome() {
		return homeID;
	}

	public void setHome(int home) {
		this.homeID = home;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	private String getClientSecret() {
		try {
			String jsonResponse = doGetRequest("https://my.tado.com/webapp/env.js", null);
			JSONObject json = new JSONObject(jsonResponse);
			return json.getJSONObject("config").getJSONObject("oauth").getString("clientSecret");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void getBearerTokens() {
		Map<String, String> body = new HashMap<>();
		body.put("client_id", "tado-web-app");
		body.put("grant_type", "password");
		body.put("scope", "home.user");
		body.put("username", this.username);
		body.put("password", this.password);
		body.put("client_secret", this.clientSecret);
		try {
			String response = doPostRequest("https://auth.tado.com/oauth/token", body, null);
			JSONObject json = new JSONObject(response);
			this.bearer = json.getString("access_token");
			this.refreshToken = json.getString("refresh_token");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Integer getHomeID() {
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + this.bearer);
			String jsonResponse = doGetRequest("https://my.tado.com/api/v2/me", headers);
			JSONObject json = new JSONObject(jsonResponse);
			return json.getInt("homeId");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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
		RequestBody formBody = builder.build();
		Request request;
		if (headers != null)
			request = new Request.Builder().url(url).post(formBody).headers(Headers.of(headers)).build();
		else
			request = new Request.Builder().url(url).post(formBody).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
}