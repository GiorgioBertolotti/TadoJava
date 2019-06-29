package dev.bertolotti.tadojava;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

public class TimerTermination extends Termination {
	private int durationInSeconds;
	private Date expiry;
	private Integer remainingTimeInSeconds;

	public int getDurationInSeconds() {
		return durationInSeconds;
	}

	public Date getExpiry() {
		return expiry;
	}

	public int getRemainingTimeInSeconds() {
		return remainingTimeInSeconds;
	}

	public TimerTermination(String typeSkillBasedApp, int durationInSeconds, Date expiry, int remainingTimeInSeconds,
			Date projectedExpiry) {
		super("TIMER", typeSkillBasedApp, projectedExpiry);
		this.durationInSeconds = durationInSeconds;
		this.expiry = expiry;
		this.remainingTimeInSeconds = remainingTimeInSeconds;
	}

	public TimerTermination(int durationInSeconds) {
		super("TIMER");
		this.durationInSeconds = durationInSeconds;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject root = new JSONObject();
		root.put("type", this.getType());
		root.put("durationInSeconds", this.durationInSeconds);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		if (this.getTypeSkillBasedApp() != null)
			root.put("typeSkillBasedApp", this.getTypeSkillBasedApp());
		if (this.expiry != null)
			root.put("expiry", dtf.format(this.expiry.toInstant()));
		if (this.remainingTimeInSeconds != null)
			root.put("remainingTimeInSeconds", this.remainingTimeInSeconds);
		if (this.getprojectedExpiry() != null)
			root.put("projectedExpiry", dtf.format(this.getprojectedExpiry().toInstant()));
		return root;
	}

	@Override
	public String toString() {
		return "TimerTermination [durationInSeconds=" + durationInSeconds + ", expiry=" + expiry
				+ ", remainingTimeInSeconds=" + remainingTimeInSeconds + "]";
	}
}