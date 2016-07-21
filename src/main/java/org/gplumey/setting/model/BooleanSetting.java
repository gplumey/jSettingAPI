package org.gplumey.setting.model;

public class BooleanSetting extends Setting<Boolean> {

	public BooleanSetting() {
		super(SettingType.Boolean);
	}

	public BooleanSetting(Object value) {
		this();
		if (value != null) {
			// setValue(Boolean.parseBoolean(value));
		}
	}

}
