/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.configurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pussy Destroyer
 */
public class AppConfig {

    private static Properties properties;
    
    
    public static Properties getPropertie(){
        properties = new Properties();
        InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }
}
