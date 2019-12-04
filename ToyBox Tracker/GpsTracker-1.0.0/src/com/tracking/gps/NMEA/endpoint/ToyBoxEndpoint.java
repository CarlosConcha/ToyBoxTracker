  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.gps.NMEA.endpoint;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 *
 * @author Pussy Destroyer
 */
public class ToyBoxEndpoint extends Endpoint{

    private Session session;
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        this.session.addMessageHandler(new MessageHandler.Whole<String>() {

            @Override
            public void onMessage(String message) {
                System.out.println("!!RETRIEVV -->"+message);
            }
        });
        try {
            this.session.getBasicRemote().sendText("\"CONNECT\n" +
"accept-version:1.1,1.0\n" +
"heart-beat:10000,10000\n" +
"\n" +
"\"");
        } catch (IOException ex) {
            Logger.getLogger(ToyBoxEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
}
