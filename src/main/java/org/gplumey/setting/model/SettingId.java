package org.gplumey.setting.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class SettingId implements Serializable {
	@DBRef
	private final Folder folder;
	private final String name;

	public Folder getFolder() {
		return folder;
	}

	public String getName() {
		return name;
	}

	public SettingId(Folder folder, String name) {
		super();
		this.folder = folder;
		this.name = name;
	}

}
