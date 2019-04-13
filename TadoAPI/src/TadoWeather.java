public class TadoWeather {
	private SolarIntensity solarIntensity;
	private OutsideTemperature outsideTemperature;
	private WeatherState weatherState;

	public SolarIntensity getSolarIntensity() {
		return solarIntensity;
	}

	public OutsideTemperature getOutsideTemperature() {
		return outsideTemperature;
	}

	public WeatherState getWeatherState() {
		return weatherState;
	}

	public TadoWeather(SolarIntensity solarIntensity, OutsideTemperature outsideTemperature,
			WeatherState weatherState) {
		super();
		this.solarIntensity = solarIntensity;
		this.outsideTemperature = outsideTemperature;
		this.weatherState = weatherState;
	}

	@Override
	public String toString() {
		return "TadoWeather [solarIntensity=" + solarIntensity + ", outsideTemperature=" + outsideTemperature
				+ ", weatherState=" + weatherState + "]";
	}
}