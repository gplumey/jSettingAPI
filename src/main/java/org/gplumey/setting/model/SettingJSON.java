package org.gplumey.setting.model;

public class SettingJSON {
	private SettingType type;
	private String value;

	public SettingType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setType(SettingType type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
