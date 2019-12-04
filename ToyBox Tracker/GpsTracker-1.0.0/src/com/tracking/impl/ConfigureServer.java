/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.impl;

import com.tracking.configurator.ConfigServer;
import com.tracking.interfaces.IServerTracking;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import org.apache.log4j.Logger;


/**
 *
 * @author Pussy Destroyer
 */
public abstract class ConfigureServer implements IServerTracking{

    private static final Logger logger = Logger.getLogger(ConfigureServer.class);
    
    protected static ServerSocketChannel server;

    static {
        _initConfig();
    }
    
    private static void _initConfig(){
        try {
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(ConfigServer.ipAdress, ConfigServer.port));
            server.configureBlocking(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(),ex);
        }
    }
    @Override
    public InetSocketAddress localAdress() {
        return new InetSocketAddress(ConfigServer.ipAdress, ConfigServer.port);
    }
   
}
