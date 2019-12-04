/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import eu.mivrenik.stomp.StompFrame;
import eu.mivrenik.stomp.client.StompClient;
import eu.mivrenik.stomp.client.listener.StompMessageListener;
import java.net.URI;




/**
 *
 * @author Pussy Destroyer
 */

public class WebSocketClientStompTest2 {
    
    public static void main(String[] args) {
        final StompClient stompSocket = new StompClient(URI.create("ws://localhost:8080/gs-guide-websocket"));

        // Wait for a connection to establish
        boolean connected;
        try {
            connected = stompSocket.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        if (!connected) {
            System.out.println("Failed to connect to the socket");
            return;
        }

        // Subscribing to a topic once STOMP connection is established
        stompSocket.subscribe("/topic/greetings", new StompMessageListener() {

            @Override
            public void onMessage(StompFrame stompFrame) {
                System.out.println("Server message: " + stompFrame.getBody());

                // Disconnect
                stompSocket.close();
            }
        });

        // Sending JSON message to a server
        String message = "{\"name\": \"Jack\"}";
        stompSocket.send("/app/hello", message);
    }
}
