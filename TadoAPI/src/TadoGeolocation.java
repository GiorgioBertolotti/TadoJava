public class TadoGeolocation {
	private double latitude;
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public TadoGeolocation(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "TadoGeolocation [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}