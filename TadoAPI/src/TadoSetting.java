public class TadoSetting {
	private String type;
	private String power;
	private TadoTemperature temperature;

	public String getType() {
		return type;
	}

	public String getPower() {
		return power;
	}

	public TadoTemperature getTemperature() {
		return temperature;
	}

	public TadoSetting(String type, String power, TadoTemperature temperature) {
		super();
		this.type = type;
		this.power = power;
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "TadoSetting [type=" + type + ", power=" + power + ", temperature=" + temperature + "]";
	}
}