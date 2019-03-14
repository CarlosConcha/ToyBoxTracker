package com.tracking.gps.coordenadas;

public class GradosDecimales {

	private Double	latitud;
	private char	latitudPos; 	
	private Double 	longitud;
	private char	longitudPos;
	
	
	public GradosDecimales() {}
	
	public GradosDecimales(Double latitud, char latitudPos, Double longitud, char longitudPos) {
		this.latitud = latitud;
		this.latitudPos = latitudPos;
		this.longitud = longitud;
		this.longitudPos = longitudPos;
	}

	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	/**
	 * Posicion de la latitud Norte o Sur
	 * @return N/S
	 */
	public char getLatitudPos() {
		return latitudPos;
	}

	public void setLatitudPos(char latitudPos) {
		this.latitudPos = latitudPos;
	}
	/**
	 * Posicion de la longitud Este / Oeste
	 * @return E/W   (East/West)
	 */
	public char getLongitudPos() {
		return longitudPos;
	}

	public void setLongitudPos(char longitudPos) {
		this.longitudPos = longitudPos;
	}

	@Override
	public String toString() {
		return "GradosDecimales [latitud=" + latitud + ", longitud=" + longitud + "]";
	}
	
	
}
