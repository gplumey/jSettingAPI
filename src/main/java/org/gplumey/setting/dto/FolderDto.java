package org.gplumey.setting.dto;

public class FolderDto {
	private FolderDto parent;
	private String name;

	public FolderDto getParent() {
		return parent;
	}

	public void setParent(FolderDto parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
