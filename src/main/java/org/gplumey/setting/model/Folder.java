package org.gplumey.setting.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Folder {
	@DBRef
	private Folder parent;
	private String name;
	private String id;

	// private I18NLabel label;

	public Folder getParent() {
		return parent;
	}

	public void setParent(Folder parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	// public I18NLabel getLabel() {
	// return label;
	// }
	//
	// public void setLabel(I18NLabel label) {
	// this.label = label;
	// }

	public void setId(String id) {
		this.id = id;
	}

}
