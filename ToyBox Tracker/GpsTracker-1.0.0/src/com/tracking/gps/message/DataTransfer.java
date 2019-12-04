/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.gps.message;

import java.io.Serializable;

/**
 *
 * @author Pussy Destroyer
 */
public class DataTransfer implements Serializable{
    
    private Double	latitud;
    private char	latitudPos; 	
    private Double 	longitud;
    private char	longitudPos;

    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the latitudPos
     */
    public char getLatitudPos() {
        return latitudPos;
    }

    /**
     * @param latitudPos the latitudPos to set
     */
    public void setLatitudPos(char latitudPos) {
        this.latitudPos = latitudPos;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the longitudPos
     */
    public char getLongitudPos() {
        return longitudPos;
    }

    /**
     * @param longitudPos the longitudPos to set
     */
    public void setLongitudPos(char longitudPos) {
        this.longitudPos = longitudPos;
    }
    
    
}
