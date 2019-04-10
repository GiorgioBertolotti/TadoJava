public class HomeID {
	public static void main(String[] args) {
		try {
			TadoConnector connector = new TadoConnector("***REMOVED***", "Password123!");
			// connector.setDebug(true);
			connector.initialize();
			for (TadoHome home : connector.getHomes()) {
				System.out.println(home.toString());
				System.out.println(home.getState(connector));
				for (TadoZone zone : home.getZones(connector)) {
					System.out.println(zone.toString());
					System.out.println(zone.getZoneState(connector));
				}
			}
		} catch (TadoException e) {
			e.printStackTrace();
		}
	}
}