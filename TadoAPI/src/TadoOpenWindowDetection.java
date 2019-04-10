public class TadoOpenWindowDetection {
	private boolean supported;
	private boolean enabled;
	private int timeoutInSeconds;

	public boolean isSupported() {
		return supported;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public int getTimeoutInSeconds() {
		return timeoutInSeconds;
	}

	public TadoOpenWindowDetection(boolean supported, boolean enabled, int timeoutInSeconds) {
		super();
		this.supported = supported;
		this.enabled = enabled;
		this.timeoutInSeconds = timeoutInSeconds;
	}

	@Override
	public String toString() {
		return "TadoOpenWindowDetection [supported=" + supported + ", enabled=" + enabled + ", timeoutInSeconds="
				+ timeoutInSeconds + "]";
	}
}