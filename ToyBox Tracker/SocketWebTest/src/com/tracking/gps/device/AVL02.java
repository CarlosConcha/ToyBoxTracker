package com.tracking.gps.device;

import java.io.Serializable;

import com.tracking.gps.device.data.DataGPRMC;

public class AVL02 implements Serializable {

	private static final long serialVersionUID = -3575781330121136646L;
	
	private static final String	header;
	private String			len;
	private String 			imei;
	private String			alarm;	
	private DataGPRMC		data;
	private String 			adc;
	private String			lacci;
	private String 			temperature;
	private short			serialId;
	private String 			checksum;
	
	static {
		header = "$$";
	}
	
	public AVL02() {
	}

	public AVL02(String len, String imei, String alarm, DataGPRMC data, String adc, String lacci, String temperature,
			short serialId, String checksum) {
		this.len = len;
		this.imei = imei;
		this.alarm = alarm;
		this.data = data;
		this.adc = adc;
		this.lacci = lacci;
		this.temperature = temperature;
		this.serialId = serialId;
		this.checksum = checksum;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	public DataGPRMC getData() {
		return data;
	}

	public void setData(DataGPRMC data) {
		this.data = data;
	}

	public String getAdc() {
		return adc;
	}

	public void setAdc(String adc) {
		this.adc = adc;
	}

	public String getLacci() {
		return lacci;
	}

	public void setLacci(String lacci) {
		this.lacci = lacci;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public short getSerialId() {
		return serialId;
	}

	public void setSerialId(short serialId) {
		this.serialId = serialId;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public static String getHeader() {
		return header;
	}

	@Override
	public String toString() {
		return "AVL02 [len=" + len + ", imei=" + imei + ", alarm=" + alarm + ", data=" + data + ", adc=" + adc
				+ ", lacci=" + lacci + ", temperature=" + temperature + ", serialId=" + serialId + ", checksum="
				+ checksum + "]";
	}
	
	
	
}
