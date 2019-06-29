package dev.bertolotti.tadojava;

import java.util.List;

public class TadoDevice {
	private String deviceType;
	private String serialNo;
	private String shortSerialNo;
	private String currentFwVersion;
	private TadoConnectionState connectionState;
	private List<String> capabilities;
	private Boolean inPairingMode;
	private String batteryState;
	private List<String> duties;

	public String getDeviceType() {
		return deviceType;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public String getShortSerialNo() {
		return shortSerialNo;
	}

	public String getCurrentFwVersion() {
		return currentFwVersion;
	}

	public TadoConnectionState getConnectionState() {
		return connectionState;
	}

	public List<String> getCapabilities() {
		return capabilities;
	}

	public Boolean getInPairingMode() {
		return inPairingMode;
	}

	public String getBatteryState() {
		return batteryState;
	}

	public List<String> getDuties() {
		return duties;
	}

	public TadoDevice(String deviceType, String serialNo, String shortSerialNo, String currentFwVersion,
			TadoConnectionState connectionState, List<String> capabilities, Boolean inPairingMode, String batteryState,
			List<String> duties) {
		super();
		this.deviceType = deviceType;
		this.serialNo = serialNo;
		this.shortSerialNo = shortSerialNo;
		this.currentFwVersion = currentFwVersion;
		this.connectionState = connectionState;
		this.capabilities = capabilities;
		this.inPairingMode = inPairingMode;
		this.batteryState = batteryState;
		this.duties = duties;
	}

	@Override
	public String toString() {
		return "TadoDevice [deviceType=" + deviceType + ", serialNo=" + serialNo + ", shortSerialNo=" + shortSerialNo
				+ ", currentFwVersion=" + currentFwVersion + ", connectionState=" + connectionState + ", capabilities="
				+ capabilities + ", inPairingMode=" + inPairingMode + ", batteryState=" + batteryState + ", duties="
				+ duties + "]";
	}
}