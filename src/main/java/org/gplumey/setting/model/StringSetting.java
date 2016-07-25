package org.gplumey.setting.model;

public class StringSetting extends Setting<String> {

	public StringSetting() {
		super(SettingType.String);
	}

	public StringSetting(Object value) {
		this();
		if (value != null) {
			// setValue(Integer.parseInt(value));
		}
	}

}
