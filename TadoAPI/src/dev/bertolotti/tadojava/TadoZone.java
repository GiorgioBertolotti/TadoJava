package dev.bertolotti.tadojava;

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
	private OpenWindowDetection openWindowDetection;

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

	public OpenWindowDetection getOpenWindowDetection() {
		return openWindowDetection;
	}

	public TadoZone(int homeId, int id, String name, String type, Date dateCreated, List<String> deviceTypes,
			List<TadoDevice> devices, boolean reportAvailable, boolean supportsDazzle, boolean dazzleEnabled,
			TadoDazzleMode dazzleMode, OpenWindowDetection openWindowDetection) {
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

	public TadoZoneState getState(TadoConnector connector) throws TadoException {
		return connector.getZoneState(this.homeId, this.id);
	}

	public Capability getCapabilities(TadoConnector connector) throws TadoException {
		return connector.getZoneCapabilities(this.homeId, this.id);
	}

	public boolean getEarlyStart(TadoConnector connector) throws TadoException {
		return connector.getZoneEarlyStart(this.homeId, this.id);
	}

	public boolean setEarlyStart(boolean enabled, TadoConnector connector) throws TadoException {
		return connector.setZoneEarlyStart(this.homeId, this.id, enabled);
	}

	public TadoOverlay getOverlay(TadoConnector connector) throws TadoException {
		return connector.getZoneOverlay(this.homeId, this.id);
	}

	public TadoOverlay setOverlay(TadoOverlay overlay, TadoConnector connector) throws TadoException {
		return connector.setZoneOverlay(this.homeId, this.id, overlay);
	}

	public void deleteOverlay(TadoConnector connector) throws TadoException {
		connector.deleteZoneOverlay(this.homeId, this.id);
	}

	@Override
	public String toString() {
		return "TadoZone [homeId=" + homeId + ", id=" + id + ", name=" + name + ", type=" + type + ", dateCreated="
				+ dateCreated + ", deviceTypes=" + deviceTypes + ", devices=" + devices + ", reportAvailable="
				+ reportAvailable + ", supportsDazzle=" + supportsDazzle + ", dazzleEnabled=" + dazzleEnabled
				+ ", dazzleMode=" + dazzleMode + ", openWindowDetection=" + openWindowDetection + "]";
	}
}