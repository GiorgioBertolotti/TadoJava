package dev.bertolotti.tadojava;

import java.util.Date;

public class TadoScheduleChange {
	private Date start;
	private TadoSetting setting;

	public Date getStart() {
		return start;
	}

	public TadoSetting getSetting() {
		return setting;
	}

	public TadoScheduleChange(Date start, TadoSetting setting) {
		super();
		this.start = start;
		this.setting = setting;
	}

	@Override
	public String toString() {
		return "TadoScheduleChange [start=" + start + ", setting=" + setting + "]";
	}
}