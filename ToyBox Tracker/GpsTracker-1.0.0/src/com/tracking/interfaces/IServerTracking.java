/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.interfaces;

import java.net.InetSocketAddress;

/**
 *
 * @author Pussy Destroyer
 */
public interface IServerTracking {
    public default InetSocketAddress localAdress(){
        return new InetSocketAddress("0.0.0.0", 6300);
    }
    
}
