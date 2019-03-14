package com.tracking.gps.device.data;

import com.tracking.gps.NMEA.GPRMC;

public class DataGPRMC {

	private GPRMC gprmc;
	private Float pdop;
	private Float hdop;
	private Float vdop;
	private String status;
	private String rtc;
	private String Voltage;
	
	
	public DataGPRMC() {
	}
	public DataGPRMC(GPRMC gprmc, Float pdop, Float hdop, Float vdop, String status, String rtc, String voltage) {
		this.gprmc = gprmc;
		this.pdop = pdop;
		this.hdop = hdop;
		this.vdop = vdop;
		this.status = status;
		this.rtc = rtc;
		Voltage = voltage;
	}
	
	public GPRMC getGprmc() {
		return gprmc;
	}
	public void setGprmc(GPRMC gprmc) {
		this.gprmc = gprmc;
	}
	/**
	 * Dilución de Precisión en Posición
	 * @return
	 */
	public Float getPdop() {
		return pdop;
	}
	public void setPdop(Float pdop) {
		this.pdop = pdop;
	}
	public Float getHdop() {
		return hdop;
	}
	public void setHdop(Float hdop) {
		this.hdop = hdop;
	}
	public Float getVdop() {
		return vdop;
	}
	public void setVdop(Float vdop) {
		this.vdop = vdop;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRtc() {
		return rtc;
	}
	public void setRtc(String rtc) {
		this.rtc = rtc;
	}
	public String getVoltage() {
		return Voltage;
	}
	public void setVoltage(String voltage) {
		Voltage = voltage;
	}
	@Override
	public String toString() {
		return "DataGPRMC [gprmc=" + gprmc + ", pdop=" + pdop + ", hdop=" + hdop + ", vdop=" + vdop + ", status="
				+ status + ", rtc=" + rtc + ", Voltage=" + Voltage + "]";
	}
	
	
}
