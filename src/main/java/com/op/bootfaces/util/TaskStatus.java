package com.op.bootfaces.util;

public enum TaskStatus {
	INPROGRESS("In Progress"),
	COMPLETED("Completed");

	private String label;

	TaskStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
