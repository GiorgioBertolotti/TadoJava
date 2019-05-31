public class HomeID {
	public static void main(String[] args) {
		try {
			TadoConnector connector = new TadoConnector("your@email.com", "Password123!");
			// connector.setDebug(true);
			connector.initialize();
			for (TadoHome home : connector.getHomes()) {
				System.out.println(home.toString());
				System.out.println(home.getState(connector));
				try {
					System.out.println(home.setState(new TadoState("AWAY"), connector));
				} catch (TadoException e) {
					e.printStackTrace();
				}
				System.out.println(home.getWeather(connector));
				System.out.println("ZONES:");
				for (TadoZone zone : home.getZones(connector)) {
					System.out.println(zone.toString());
					System.out.println("ZONE STATE:");
					System.out.println(zone.getState(connector));
					System.out.println("ZONE CAPABILITIES:");
					System.out.println(zone.getCapabilities(connector).toString());
					System.out.println("ZONE EARLY START:");
					System.out.println(zone.getEarlyStart(connector));
					/*
					 * zone.setEarlyStart(false, connector);
					 * System.out.println(zone.getEarlyStart(connector)); zone.setEarlyStart(true,
					 * connector); System.out.println(zone.getEarlyStart(connector));
					 */
					System.out.println("ZONE OVERLAY");
					try {
						System.out.println(zone.getOverlay(connector));
					} catch (TadoException e) {
						if (e.getCode().equals("notFound")) {
							System.out.println("This zone has no overlay.");
						} else
							throw e;
					}
					/*
					 * TadoOverlay overlay = new TadoOverlay(new TadoSetting(true, 25.0, null), new
					 * TadoModeTermination()); System.out.println(zone.setOverlay(overlay,
					 * connector)); zone.deleteOverlay(connector);
					 */
				}
				System.out.println("DEVICES:");
				for (TadoDevice device : home.getDevices(connector)) {
					System.out.println(device.toString());
				}
				System.out.println("INSTALLATIONS:");
				for (TadoInstallation installation : home.getInstallations(connector)) {
					System.out.println(installation.toString());
				}
				System.out.println("USERS:");
				for (User user : home.getUsers(connector)) {
					System.out.println(user.toString());
				}
				System.out.println("MOBILE DEVICES:");
				for (MobileDevice device : home.getMobileDevices(connector)) {
					// unnecessary, just to show how to use getMobileDevice, you could simply do
					// System.out.println(device.toString());
					System.out.println(home.getMobileDevice(device.getId(), connector).toString());
					System.out.println("MOBILE DEVICE SETTINGS:");
					System.out.println(device.getSettings(connector).toString());
					/*
					 * device.setGeoTracking(false, connector);
					 * System.out.println(device.getSettings(connector).toString());
					 * device.setGeoTracking(true, connector);
					 * System.out.println(device.getSettings(connector).toString());
					 */
				}
			}
		} catch (TadoException e) {
			e.printStackTrace();
		}
	}
}