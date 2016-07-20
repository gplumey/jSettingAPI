package org.gplumey.setting.model;

import java.time.LocalDate;

public class DateSetting extends Setting<LocalDate> {

	public DateSetting() {
		super(SettingType.Date);
	}

	public DateSetting(String value) {
		this();
		if (value != null) {
			setValue(LocalDate.parse(value));
		}
	}

}
