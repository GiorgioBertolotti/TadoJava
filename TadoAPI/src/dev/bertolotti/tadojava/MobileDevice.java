package dev.bertolotti.tadojava;

import java.util.Map;

public class MobileDevice {
	private int homeId;
	private String name;
	private int id;
	private Map<String, Object> settings;
	private MobileLocation location;
	private DeviceMetadata deviceMetadata;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Map<String, Object> getSettings() {
		return settings;
	}

	public MobileLocation getLocation() {
		return location;
	}

	public DeviceMetadata getDeviceMetadata() {
		return deviceMetadata;
	}

	public int getHomeId() {
		return homeId;
	}

	public MobileDevice(int homeId, String name, int id, Map<String, Object> settings, MobileLocation location,
			DeviceMetadata deviceMetadata) {
		super();
		this.homeId = homeId;
		this.name = name;
		this.id = id;
		this.settings = settings;
		this.location = location;
		this.deviceMetadata = deviceMetadata;
	}

	public Map<String, Object> getSettings(TadoConnector connector) throws TadoException {
		return connector.getMobileDeviceSettings(this.homeId, this.id);
	}

	public boolean setGeoTracking(boolean enabled, TadoConnector connector) throws TadoException {
		return connector.setGeoTracking(this.homeId, this.id, enabled);
	}

	@Override
	public String toString() {
		return "MobileDevice [homeId=" + homeId + ", name=" + name + ", id=" + id + ", settings=" + settings
				+ ", location=" + location + ", deviceMetadata=" + deviceMetadata + "]";
	}
}