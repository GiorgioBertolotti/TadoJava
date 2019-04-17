public class MobileLocation {
	private boolean stale;
	private boolean atHome;
	private double degreesBearingFromHome;
	private double radiansBearingFromHome;
	private double relativeDistanceFromHomeFence;

	public boolean isStale() {
		return stale;
	}

	public boolean isAtHome() {
		return atHome;
	}

	public double getDegreesBearingFromHome() {
		return degreesBearingFromHome;
	}

	public double getRadiansBearingFromHome() {
		return radiansBearingFromHome;
	}

	public double getRelativeDistanceFromHomeFence() {
		return relativeDistanceFromHomeFence;
	}

	public MobileLocation(boolean stale, boolean atHome, double degreesBearingFromHome, double radiansBearingFromHome,
			double relativeDistanceFromHomeFence) {
		super();
		this.stale = stale;
		this.atHome = atHome;
		this.degreesBearingFromHome = degreesBearingFromHome;
		this.radiansBearingFromHome = radiansBearingFromHome;
		this.relativeDistanceFromHomeFence = relativeDistanceFromHomeFence;
	}

	@Override
	public String toString() {
		return "MobileLocation [stale=" + stale + ", atHome=" + atHome + ", degreesBearingFromHome="
				+ degreesBearingFromHome + ", radiansBearingFromHome=" + radiansBearingFromHome
				+ ", relativeDistanceFromHomeFence=" + relativeDistanceFromHomeFence + "]";
	}
}