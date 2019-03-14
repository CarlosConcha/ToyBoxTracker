package com.tracking.gps.NMEA;

import com.tracking.gps.coordenadas.GradosDecimales;

/**
 * Recommended minimum specific GPS/Transit data
	eg1. $GPRMC,081836,A,3751.65,S,14507.36,E,000.0,360.0,130998,011.3,E*62
	eg2. $GPRMC,225446,A,4916.45,N,12311.12,W,000.5,054.7,191194,020.3,E*68
 * @author Carlos Concha
 *
 */
public class GPRMC {

	private Float 	time;
	//validity - A-ok, V-invalid
	private char 			status;
	private GradosDecimales	coordenadas;
	private Float 			speed;
	private Float 			course;
	private Float 			date;
	private Float			variation;
	private char			grado;
	private String			checksum;
	
	
	public GPRMC() {
		this.coordenadas = new GradosDecimales();
	}
	public GPRMC(Float time, char status, GradosDecimales coordenadas, Float speed, Float course, Float date,
			Float variation, char grado, String checksum) {
		super();
		this.time = time;
		this.status = status;
		this.coordenadas = coordenadas;
		this.speed = speed;
		this.course = course;
		this.date = date;
		this.variation = variation;
		this.grado = grado;
		this.checksum = checksum;
	}
	public Float getTime() {
		return time;
	}
	public void setTime(Float time) {
		this.time = time;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public GradosDecimales getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(GradosDecimales coordenadas) {
		this.coordenadas = coordenadas;
	}
	/**
	 * velocidad sobre el piso
	 * @return velocidad en nudos
	 */
	public Float getSpeed() {
		return speed;
	}
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	public Float getCourse() {
		return course;
	}
	public void setCourse(Float course) {
		this.course = course;
	}
	public Float getDate() {
		return date;
	}
	public void setDate(Float date) {
		this.date = date;
	}
	public Float getVariation() {
		return variation;
	}
	public void setVariation(Float variation) {
		this.variation = variation;
	}
	public char getGrado() {
		return grado;
	}
	public void setGrado(char grado) {
		this.grado = grado;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	@Override
	public String toString() {
		return "GPRMC [time=" + time + ", status=" + status + ", coordenadas=" + coordenadas + ", speed=" + speed
				+ ", course=" + course + ", date=" + date + ", variation=" + variation + ", grado=" + grado
				+ ", checksum=" + checksum + "]";
	}
	
}
