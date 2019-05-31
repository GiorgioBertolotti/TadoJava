public class TadoState {
	private String presence;

	public String getPresence() {
		return presence;
	}

	public TadoState(String presence) throws TadoException {
		super();
		if (presence == null || (!presence.equals("AWAY") && !presence.equals("HOME")))
			throw new TadoException("error", "Presence value can only be HOME or AWAY.");
		this.presence = presence;
	}

	@Override
	public String toString() {
		return "TadoState [presence=" + presence + "]";
	}
}