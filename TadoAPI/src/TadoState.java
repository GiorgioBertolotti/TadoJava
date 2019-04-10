public class TadoState {
	private String presence;

	public String getPresence() {
		return presence;
	}

	public TadoState(String presence) {
		super();
		this.presence = presence;
	}

	@Override
	public String toString() {
		return "TadoState [presence=" + presence + "]";
	}
}