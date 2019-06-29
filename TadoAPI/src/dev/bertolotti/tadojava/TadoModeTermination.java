package dev.bertolotti.tadojava;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

public class TadoModeTermination extends Termination {

	public TadoModeTermination(String typeSkillBasedApp, Date projectedExpiry) {
		super("TADO_MODE", typeSkillBasedApp, projectedExpiry);
	}

	public TadoModeTermination() {
		super("TADO_MODE");
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject root = new JSONObject();
		root.put("type", this.getType());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		if (this.getTypeSkillBasedApp() != null)
			root.put("typeSkillBasedApp", this.getTypeSkillBasedApp());
		if (this.getprojectedExpiry() != null)
			root.put("projectedExpiry", dtf.format(this.getprojectedExpiry().toInstant()));
		return root;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}