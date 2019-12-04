/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.configurator;

import java.util.Properties;


/**
 *
 * @author Pussy Destroyer
 */
public class ConfigServer {
    
    private static final Properties properties  =   AppConfig.getPropertie();
    
    public static final String ipAdress;
    public static final int port;
    
    static {
        ipAdress = properties.getProperty("ip.adress");
        port    = Integer.valueOf(properties.getProperty("port"));
    }
    
}
