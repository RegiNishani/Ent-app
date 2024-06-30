package com.op.bootfaces.util;

public enum UserType {
//	ADMIN("Administrator"),
	EMPLOYEE("Employee User"),
	CUSTOMER("Customer User");

	private String label;

	UserType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
