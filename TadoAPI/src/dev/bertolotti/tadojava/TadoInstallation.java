package dev.bertolotti.tadojava;

import java.util.List;

public class TadoInstallation {
	private int id;
	private String type;
	private int revision;
	private String state;
	private List<TadoDevice> devices;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TadoDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<TadoDevice> devices) {
		this.devices = devices;
	}

	public TadoInstallation(int id, String type, int revision, String state, List<TadoDevice> devices) {
		super();
		this.id = id;
		this.type = type;
		this.revision = revision;
		this.state = state;
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "TadoInstallation [id=" + id + ", type=" + type + ", revision=" + revision + ", state=" + state
				+ ", devices=" + devices + "]";
	}
}