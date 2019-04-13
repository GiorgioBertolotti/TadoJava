import java.util.Date;

public class WeatherState {
	private String type;
	private String value;
	private Date timestamp;

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public WeatherState(String type, String value, Date timestamp) {
		super();
		this.type = type;
		this.value = value;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "WeatherState [type=" + type + ", value=" + value + "]";
	}
}