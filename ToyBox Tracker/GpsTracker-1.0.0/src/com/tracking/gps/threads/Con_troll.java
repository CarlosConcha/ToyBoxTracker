/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.gps.threads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pussy Destroyer
 */
public class Con_troll implements Runnable{

    private SocketChannel channel;
    
    public Con_troll(final SocketChannel channel){
        this.channel = channel;
    }
    @Override
    public void run() {
        if(channel != null && channel.isConnected()){
            try {
                ByteBuffer buffer = ByteBuffer.allocate(100);
                while(channel.read(buffer) > -1){
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.print((char) buffer.get());
                    }

                buffer.clear();
                }
            } catch (IOException ex) {
                Logger.getLogger(Con_troll.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
