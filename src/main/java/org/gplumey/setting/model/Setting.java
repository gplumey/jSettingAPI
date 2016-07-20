package org.gplumey.setting.model;

import org.springframework.data.annotation.Id;

abstract public class Setting<T> {
	private SettingId id;
	private final SettingType type;
	private T value;

	public Setting(SettingType type) {
		super();
		this.type = type;
	}

	@Id
	public SettingId getId() {
		return id;
	}

	public void setId(SettingId id) {
		this.id = id;
	}

	public SettingType getType() {
		return type;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
