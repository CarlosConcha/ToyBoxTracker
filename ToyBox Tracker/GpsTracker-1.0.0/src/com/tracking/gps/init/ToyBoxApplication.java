/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.gps.init;

import com.tracking.client.Stomp;
import com.tracking.client.WebSocketClient;
import com.tracking.impl.ConfigureServer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;




/**
 *
 * @author Pussy Destroyer
 */

public class ToyBoxApplication extends ConfigureServer{
    
    private static final Logger logger = Logger.getLogger(ToyBoxApplication.class);
    
  
    
    public static void main(String[] args) {
        try {
            
            Selector selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            
            SelectionKey key = null;
            
            while(true){
                selector.select();
                
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                
                while(iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    if(key.isAcceptable()){
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                        logger.info("remote client connected --> "+channel.getRemoteAddress());
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer bb = ByteBuffer.allocate(1024);
                        channel.read(bb);
                        String result = new String(bb.array()).trim();
                        logger.info("msg: "+result);
                        String dummy = "$$B4867965020614190|AA$GPRMC,221357.00,A,3334.86253,S,07039.02052,W,0.043,,250219,,,A*71|1.58|0.83|1.34|000000000000|20190225221357|03740000 |00000000|32D24F13|0000|0.0000|0006|02F2";
                        WebSocketClient.getInstance(dummy).connect();
                        if (result.length() <= 0) {
                            channel.close();
                            logger.info("closing connection...");
                        }
                    }
                }
                
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        
    }

    
    
}
