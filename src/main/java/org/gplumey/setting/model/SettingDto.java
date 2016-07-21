package org.gplumey.setting.model;

public class SettingDto {
	private SettingType type;
	private Object value;

	public SettingType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public void setType(SettingType type) {
		this.type = type;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
