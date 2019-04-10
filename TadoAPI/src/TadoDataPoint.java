import org.json.JSONObject;

public class TadoDataPoint {
	private String name;
	private JSONObject datapoint;

	public String getName() {
		return name;
	}

	public JSONObject getDatapoint() {
		return datapoint;
	}

	public TadoDataPoint(String name, JSONObject datapoint) {
		super();
		this.name = name;
		this.datapoint = datapoint;
	}

	@Override
	public String toString() {
		return "TadoActivityDataPoint [name=" + name + ", datapoint=" + datapoint + "]";
	}
}