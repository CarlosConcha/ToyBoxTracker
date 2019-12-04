/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.gps.util;

/**
 *
 * @author Pussy Destroyer
 */
public class ConvertNmea {
    
    public static Double toDecimalDegrees(Double coordenate){
        Double degrees  = coordenate / 100;
        Double second   = coordenate - (degrees.intValue() * 100);
        return degrees.intValue() + (second / 60);
    }
}
