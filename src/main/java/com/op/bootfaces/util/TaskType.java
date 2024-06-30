package com.op.bootfaces.util;

public enum TaskType {
	BUG("Bug"),
	ENHANCEMENT("Enhancement"),
	UPGRADE("Upgrade");

	private String label;

	TaskType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
