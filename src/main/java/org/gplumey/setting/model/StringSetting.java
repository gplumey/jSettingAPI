package org.gplumey.setting.model;

public class StringSetting extends Setting<Integer> {

	public StringSetting() {
		super(SettingType.Integer);
	}

	public StringSetting(Object value) {
		this();
		if (value != null) {
			// setValue(Integer.parseInt(value));
		}
	}

}
