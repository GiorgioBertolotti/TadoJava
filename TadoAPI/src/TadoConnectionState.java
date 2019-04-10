import java.util.Date;

public class TadoConnectionState {
	private boolean value;
	private Date timestamp;

	public boolean isValue() {
		return value;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public TadoConnectionState(boolean value, Date timestamp) {
		super();
		this.value = value;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TadoConnectionState [value=" + value + ", timestamp=" + timestamp + "]";
	}
}