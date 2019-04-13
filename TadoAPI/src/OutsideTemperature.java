import java.util.Date;

public class OutsideTemperature {
	private double celsius;
	private double fahrenheit;
	private Date timestamp;
	private String type;
	private double celsiusPrecision;
	private double fahrenheitPrecision;

	public double getCelsius() {
		return celsius;
	}

	public double getFahrenheit() {
		return fahrenheit;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getType() {
		return type;
	}

	public double getCelsiusPrecision() {
		return celsiusPrecision;
	}

	public double getFahrenheitPrecision() {
		return fahrenheitPrecision;
	}

	public OutsideTemperature(double celsius, double fahrenheit, Date timestamp, String type, double celsiusPrecision,
			double fahrenheitPrecision) {
		super();
		this.celsius = celsius;
		this.fahrenheit = fahrenheit;
		this.timestamp = timestamp;
		this.type = type;
		this.celsiusPrecision = celsiusPrecision;
		this.fahrenheitPrecision = fahrenheitPrecision;
	}

	@Override
	public String toString() {
		return "OutsideTemperature [celsius=" + celsius + ", fahrenheit=" + fahrenheit + ", timestamp=" + timestamp
				+ ", type=" + type + ", celsiusPrecision=" + celsiusPrecision + ", fahrenheitPrecision="
				+ fahrenheitPrecision + "]";
	}
}