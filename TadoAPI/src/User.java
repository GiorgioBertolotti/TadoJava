import java.util.List;
import java.util.Map;

public class User {
	private String name;
	private String email;
	private String username;
	private Map<Integer, String> homes;
	private String locale;
	private List<MobileDevice> mobileDevices;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public Map<Integer, String> getHomes() {
		return homes;
	}

	public String getLocale() {
		return locale;
	}

	public List<MobileDevice> getMobileDevices() {
		return mobileDevices;
	}

	public User(String name, String email, String username, Map<Integer, String> homes, String locale,
			List<MobileDevice> mobileDevices) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.homes = homes;
		this.locale = locale;
		this.mobileDevices = mobileDevices;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", username=" + username + ", homes=" + homes + ", locale="
				+ locale + ", mobileDevices=" + mobileDevices + "]";
	}
}