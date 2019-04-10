import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class TadoHome {
	private int id;
	private String name;
	private String dateTimeZone;
	private Date dateCreated;
	private String temperatureUnit;
	private boolean installationCompleted;
	private boolean simpleSmartScheduleEnabled;
	private double awayRadiusInMeters;
	private boolean usePreSkillsApps;
	private boolean christmasModeEnabled;
	private TadoContact contactDetails;
	private TadoAddress address;
	private TadoGeolocation geolocation;
	private boolean consentGrantSkippable;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDateTimeZone() {
		return dateTimeZone;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getTemperatureUnit() {
		return temperatureUnit;
	}

	public boolean isInstallationCompleted() {
		return installationCompleted;
	}

	public boolean isSimpleSmartScheduleEnabled() {
		return simpleSmartScheduleEnabled;
	}

	public double getAwayRadiusInMeters() {
		return awayRadiusInMeters;
	}

	public boolean isUsePreSkillsApps() {
		return usePreSkillsApps;
	}

	public boolean isChristmasModeEnabled() {
		return christmasModeEnabled;
	}

	public TadoContact getContactDetails() {
		return contactDetails;
	}

	public TadoAddress getAddress() {
		return address;
	}

	public TadoGeolocation getGeolocation() {
		return geolocation;
	}

	public boolean isConsentGrantSkippable() {
		return consentGrantSkippable;
	}

	public TadoHome(int id) {
		super();
		this.id = id;
	}

	public TadoHome(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public TadoHome(int id, String name, String dateTimeZone, Date dateCreated, String temperatureUnit,
			boolean installationCompleted, boolean simpleSmartScheduleEnabled, double awayRadiusInMeters,
			boolean usePreSkillsApps, boolean christmasModeEnabled, TadoContact contactDetails, TadoAddress address,
			TadoGeolocation geolocation, boolean consentGrantSkippable) {
		super();
		this.id = id;
		this.name = name;
		this.dateTimeZone = dateTimeZone;
		this.dateCreated = dateCreated;
		this.temperatureUnit = temperatureUnit;
		this.installationCompleted = installationCompleted;
		this.simpleSmartScheduleEnabled = simpleSmartScheduleEnabled;
		this.awayRadiusInMeters = awayRadiusInMeters;
		this.usePreSkillsApps = usePreSkillsApps;
		this.christmasModeEnabled = christmasModeEnabled;
		this.contactDetails = contactDetails;
		this.address = address;
		this.geolocation = geolocation;
		this.consentGrantSkippable = consentGrantSkippable;
	}

	public List<TadoZone> getZones(TadoConnector connector) throws TadoException {
		return connector.getZones(this.id);
	}

	public TadoState getState(TadoConnector connector) throws TadoException {
		return connector.getState(this.id);
	}

	@Override
	public String toString() {
		return "TadoHome [id=" + id + ", name=" + name + ", dateTimeZone=" + dateTimeZone + ", dateCreated="
				+ dateCreated + ", temperatureUnit=" + temperatureUnit + ", installationCompleted="
				+ installationCompleted + ", simpleSmartScheduleEnabled=" + simpleSmartScheduleEnabled
				+ ", awayRadiusInMeters=" + awayRadiusInMeters + ", usePreSkillsApps=" + usePreSkillsApps
				+ ", christmasModeEnabled=" + christmasModeEnabled + ", contactDetails=" + contactDetails + ", address="
				+ address + ", geolocation=" + geolocation + ", consentGrantSkippable=" + consentGrantSkippable + "]";
	}
}