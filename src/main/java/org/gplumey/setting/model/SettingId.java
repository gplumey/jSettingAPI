package org.gplumey.setting.model;

import java.io.Serializable;

public class SettingId implements Serializable {
	private final String folder;
	private final String name;

	public String getFolder() {
		return folder;
	}

	public String getName() {
		return name;
	}

	public SettingId(String folder, String name) {
		super();
		this.folder = folder;
		this.name = name;
	}

}
