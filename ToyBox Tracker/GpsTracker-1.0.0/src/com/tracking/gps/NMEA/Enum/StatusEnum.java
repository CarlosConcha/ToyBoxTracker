package com.tracking.gps.NMEA.Enum;

public enum StatusEnum {

	A ("OK"),
	V ("INVALID");
	
	private final String state;
	
	private StatusEnum(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
	
}
