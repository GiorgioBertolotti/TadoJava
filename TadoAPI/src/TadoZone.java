import java.util.Date;
import java.util.List;

public class TadoZone {
	private int homeId;
	private int id;
	private String name;
	private String type;
	private Date dateCreated;
	private List<String> deviceTypes;
	private List<TadoDevice> devices;
	private boolean reportAvailable;
	private boolean supportsDazzle;
	private boolean dazzleEnabled;
	private TadoDazzleMode dazzleMode;
	private TadoOpenWindowDetection openWindowDetection;

	public int getHomeId() {
		return homeId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public List<String> getDeviceTypes() {
		return deviceTypes;
	}

	public List<TadoDevice> getDevices() {
		return devices;
	}

	public boolean isReportAvailable() {
		return reportAvailable;
	}

	public boolean isSupportsDazzle() {
		return supportsDazzle;
	}

	public boolean isDazzleEnabled() {
		return dazzleEnabled;
	}

	public TadoDazzleMode getDazzleMode() {
		return dazzleMode;
	}

	public TadoOpenWindowDetection getOpenWindowDetection() {
		return openWindowDetection;
	}

	public TadoZone(int homeId, int id, String name, String type, Date dateCreated, List<String> deviceTypes,
			List<TadoDevice> devices, boolean reportAvailable, boolean supportsDazzle, boolean dazzleEnabled,
			TadoDazzleMode dazzleMode, TadoOpenWindowDetection openWindowDetection) {
		super();
		this.homeId = homeId;
		this.id = id;
		this.name = name;
		this.type = type;
		this.dateCreated = dateCreated;
		this.deviceTypes = deviceTypes;
		this.devices = devices;
		this.reportAvailable = reportAvailable;
		this.supportsDazzle = supportsDazzle;
		this.dazzleEnabled = dazzleEnabled;
		this.dazzleMode = dazzleMode;
		this.openWindowDetection = openWindowDetection;
	}

	@Override
	public String toString() {
		return "TadoZone [homeId=" + homeId + ", id=" + id + ", name=" + name + ", type=" + type + ", dateCreated="
				+ dateCreated + ", deviceTypes=" + deviceTypes + ", devices=" + devices + ", reportAvailable="
				+ reportAvailable + ", supportsDazzle=" + supportsDazzle + ", dazzleEnabled=" + dazzleEnabled
				+ ", dazzleMode=" + dazzleMode + ", openWindowDetection=" + openWindowDetection + "]";
	}
}