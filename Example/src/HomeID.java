public class HomeID {
	public static void main(String[] args) {
		try {
			TadoConnector connector = new TadoConnector("***REMOVED***", "Password123!");
			// connector.setDebug(true);
			connector.initialize();
			for (TadoHome home : connector.getHomes()) {
				System.out.println(home.toString());
				for (TadoZone zone : connector.getZones(home)) {
					System.out.println(zone.toString());
				}
				System.out.println(connector.getState(home));
			}
		} catch (TadoException e) {
			e.printStackTrace();
		}
	}
}