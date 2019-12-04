/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.client;

import com.tracking.gps.device.AVL02;
import com.tracking.gps.device.parse.AVL02Parse;
import com.tracking.interfaces.IStomp;
import java.net.URI;

/**
 *
 * @author Pussy Destroyer
 */
public class WebSocketClient extends Stomp{
    
    private static WebSocketClient webSocketClient;
    private AVL02 device;
    private transient String data;
    
    private WebSocketClient(final String data){
        AVL02Parse avl02 = new AVL02Parse(data);
        device = avl02.parse();
    }
    
    public static WebSocketClient getInstance(final String data){
        if(webSocketClient == null){
            webSocketClient = new WebSocketClient(data);
        }
        return webSocketClient;
    }
    @Override
    public void  connect(){
        addHandle(new IStomp() {
            @Override
            public URI LUR() {
                return URI.create("ws://localhost:8080/gs-guide-websocket");
            }
        });
        sendToWebSocket(device);
    }
}
