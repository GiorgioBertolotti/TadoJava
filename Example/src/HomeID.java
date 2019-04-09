public class HomeID {
	public static void main(String[] args) {
		try {
			TadoConnector connector = new TadoConnector("***REMOVED***", "Password123!");
			// connector.setDebug(true);
			connector.initialize();
			for (TadoHome home : connector.getHomes())
				System.out.println("Home ID: " + home.getId());
		} catch (TadoException e) {
			e.printStackTrace();
		}
	}
}