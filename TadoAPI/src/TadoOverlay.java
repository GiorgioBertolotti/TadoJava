import org.json.JSONObject;

public class TadoOverlay {
	String type;
	TadoSetting setting;
	Termination termination;

	public String getType() {
		return type;
	}

	public TadoSetting getSetting() {
		return setting;
	}

	public Termination getTermination() {
		return termination;
	}

	public TadoOverlay(String type, TadoSetting setting, Termination termination) {
		super();
		this.type = type;
		this.setting = setting;
		this.termination = termination;
	}

	public TadoOverlay(TadoSetting setting, Termination termination) {
		super();
		this.setting = setting;
		this.termination = termination;
	}

	public JSONObject toJSONObject() {
		JSONObject root = new JSONObject();
		if (this.type != null)
			root.put("type", this.type);
		root.put("setting", this.setting.toJSONObject());
		root.put("termination", this.termination.toJSONObject());
		return root;
	}

	@Override
	public String toString() {
		return "TadoOverlay [type=" + type + ", setting=" + setting + ", termination=" + termination + "]";
	}
}