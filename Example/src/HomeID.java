public class HomeID {
	public static void main(String[] args) {
		TadoConnector connector = new TadoConnector("***REMOVED***", "Password123!");
		System.out.println("Bearer: " + connector.getBearer());
		System.out.println("Home ID: " + connector.getHome());
	}
}