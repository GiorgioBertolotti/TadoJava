import java.util.Date;
import java.util.List;

public class TadoZoneState {
	private String tadoMode;
	private boolean geolocationOverride;
	private Date geolocationOverrideDisableTime;
	private TadoSetting setting;
	private TadoScheduleChange nextScheduleChange;
	private String linkState;
	private List<TadoDataPoint> activityDataPoints;
	private List<TadoDataPoint> sensorDataPoints;

	public String getTadoMode() {
		return tadoMode;
	}

	public boolean isGeolocationOverride() {
		return geolocationOverride;
	}

	public Date getGeolocationOverrideDisableTime() {
		return geolocationOverrideDisableTime;
	}

	public TadoSetting getSetting() {
		return setting;
	}

	public TadoScheduleChange getNextScheduleChange() {
		return nextScheduleChange;
	}

	public String getLinkState() {
		return linkState;
	}

	public List<TadoDataPoint> getActivityDataPoints() {
		return activityDataPoints;
	}

	public List<TadoDataPoint> getSensorDataPoints() {
		return sensorDataPoints;
	}

	public TadoZoneState(String tadoMode, boolean geolocationOverride, Date geolocationOverrideDisableTime,
			TadoSetting setting, TadoScheduleChange nextScheduleChange, String linkState,
			List<TadoDataPoint> activityDataPoints, List<TadoDataPoint> sensorDataPoints) {
		super();
		this.tadoMode = tadoMode;
		this.geolocationOverride = geolocationOverride;
		this.geolocationOverrideDisableTime = geolocationOverrideDisableTime;
		this.setting = setting;
		this.nextScheduleChange = nextScheduleChange;
		this.linkState = linkState;
		this.activityDataPoints = activityDataPoints;
		this.sensorDataPoints = sensorDataPoints;
	}

	@Override
	public String toString() {
		return "TadoZoneState [tadoMode=" + tadoMode + ", geolocationOverride=" + geolocationOverride
				+ ", geolocationOverrideDisableTime=" + geolocationOverrideDisableTime + ", setting=" + setting
				+ ", nextScheduleChange=" + nextScheduleChange + ", linkState=" + linkState + ", activityDataPoints="
				+ activityDataPoints + ", sensorDataPoints=" + sensorDataPoints + "]";
	}
}