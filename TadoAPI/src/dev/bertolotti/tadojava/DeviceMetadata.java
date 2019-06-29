package dev.bertolotti.tadojava;

public class DeviceMetadata {
	private String platform;
	private String osVersion;
	private String model;
	private String locale;

	public String getPlatform() {
		return platform;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public String getModel() {
		return model;
	}

	public String getLocale() {
		return locale;
	}

	public DeviceMetadata(String platform, String osVersion, String model, String locale) {
		super();
		this.platform = platform;
		this.osVersion = osVersion;
		this.model = model;
		this.locale = locale;
	}

	@Override
	public String toString() {
		return "DeviceMetadata [platform=" + platform + ", osVersion=" + osVersion + ", model=" + model + ", locale="
				+ locale + "]";
	}
}