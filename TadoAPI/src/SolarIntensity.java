import java.util.Date;

public class SolarIntensity {
	private String type;
	private double percentage;
	private Date timestamp;

	public String getType() {
		return type;
	}

	public double getPercentage() {
		return percentage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public SolarIntensity(String type, double percentage, Date timestamp) {
		super();
		this.type = type;
		this.percentage = percentage;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "SolarIntensity [type=" + type + ", percentage=" + percentage + ", timestamp=" + timestamp + "]";
	}
}