package dev.bertolotti.tadojava;

public class TadoDazzleMode {
	private boolean supported;
	private boolean enabled;

	public boolean isSupported() {
		return supported;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public TadoDazzleMode(boolean supported, boolean enabled) {
		super();
		this.supported = supported;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "TadoDazzleMode [supported=" + supported + ", enabled=" + enabled + "]";
	}
}