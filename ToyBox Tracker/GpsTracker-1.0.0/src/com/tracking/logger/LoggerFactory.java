/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Pussy Destroyer
 */
public class LoggerFactory extends Logger{
    
    protected LoggerFactory(String name){
        super(name);
        PropertyConfigurator.configure("log4j.properties");
    }
   
}
