package org.gplumey.setting.model;

public class IntegerSetting extends Setting<Integer> {

	public IntegerSetting() {
		super(SettingType.Integer);
	}

	public IntegerSetting(String value) {
		this();
		if (value != null) {
			setValue(Integer.parseInt(value));
		}
	}

}
