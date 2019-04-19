import org.json.JSONObject;

public class TadoSetting {
	private String type;
	private boolean _power;
	private Temperature temperature;

	public String getType() {
		return type;
	}

	public String getPower() {
		return (this._power) ? "ON" : "OFF";
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public TadoSetting(boolean power, Temperature temperature) {
		super();
		this.type = "HEATING";
		this._power = power;
		this.temperature = temperature;
	}

	public TadoSetting(boolean power, Double celsius, Double fahrenheit) throws TadoException {
		super();
		this.type = "HEATING";
		this._power = power;
		this.temperature = new Temperature(celsius, fahrenheit);
	}

	public TadoSetting(String type, boolean power, Temperature temperature) {
		super();
		this.type = type;
		this._power = power;
		this.temperature = temperature;
	}

	public TadoSetting(String type, boolean power, Double celsius, Double fahrenheit) throws TadoException {
		super();
		this.type = type;
		this._power = power;
		this.temperature = new Temperature(celsius, fahrenheit);
	}

	public JSONObject toJSONObject() {
		JSONObject root = new JSONObject();
		root.put("type", this.type);
		root.put("power", (this._power) ? "ON" : "OFF");
		root.put("temperature", this.temperature.toJSONObject());
		return root;
	}

	@Override
	public String toString() {
		return "TadoSetting [type=" + type + ", power=" + ((this._power) ? "ON" : "OFF") + ", temperature="
				+ temperature + "]";
	}
}