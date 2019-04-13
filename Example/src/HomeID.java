public class HomeID {
	public static void main(String[] args) {
		try {
			TadoConnector connector = new TadoConnector("***REMOVED***", "Password123!");
			// connector.setDebug(true);
			connector.initialize();
			for (TadoHome home : connector.getHomes()) {
				System.out.println(home.toString());
				System.out.println(home.getState(connector));
				System.out.println(home.getWeather(connector));
				System.out.println("ZONES:");
				for (TadoZone zone : home.getZones(connector)) {
					System.out.println(zone.toString());
					System.out.println(zone.getZoneState(connector));
				}
				System.out.println("DEVICES:");
				for (TadoDevice device : home.getDevices(connector)) {
					System.out.println(device.toString());
				}
				System.out.println("INSTALLATIONS:");
				for (TadoInstallation installation : home.getInstallations(connector)) {
					System.out.println(installation.toString());
				}
			}
		} catch (TadoException e) {
			e.printStackTrace();
		}
	}
}