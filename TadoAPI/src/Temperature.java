import org.json.JSONObject;

public class Temperature {
	private Double celsius;
	private Double fahrenheit;

	public double getCelsius() {
		return celsius;
	}

	public double getFahrenheit() {
		return fahrenheit;
	}

	public void setCelsius(double celsius) {
		this.celsius = celsius;
	}

	public void setFahrenheit(double fahrenheit) {
		this.fahrenheit = fahrenheit;
	}

	public Temperature(Double celsius, Double fahrenheit) throws TadoException {
		super();
		if (celsius == null && fahrenheit == null)
			throw new TadoException("error", "Please specify at least celsius or fahrenheit temperature.");
		if (celsius != null)
			this.celsius = celsius;
		if (fahrenheit != null)
			this.fahrenheit = fahrenheit;
	}

	public Temperature() {
		super();
	}

	public JSONObject toJSONObject() {
		JSONObject root = new JSONObject();
		if (this.celsius != null)
			root.put("celsius", this.celsius);
		if (this.fahrenheit != null)
			root.put("fahrenheit", this.fahrenheit);
		return root;
	}

	@Override
	public String toString() {
		return "TadoTemperature [celsius=" + celsius + ", fahrenheit=" + fahrenheit + "]";
	}
}