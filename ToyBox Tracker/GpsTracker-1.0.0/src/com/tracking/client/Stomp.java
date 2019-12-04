/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.client;

import com.google.gson.Gson;
import com.tracking.gps.device.AVL02;
import com.tracking.gps.device.impl.DeviceParse;
import com.tracking.gps.message.DataTransfer;
import com.tracking.gps.util.ConvertNmea;
import com.tracking.interfaces.IStomp;
import com.tracking.logger.LoggerFactory;
import eu.mivrenik.stomp.StompFrame;
import eu.mivrenik.stomp.client.StompClient;
import eu.mivrenik.stomp.client.listener.StompMessageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Pussy Destroyer
 */
public abstract class Stomp implements StompMessageListener{

    private static final Logger logger = LoggerFactory.getLogger(Stomp.class);
    
    
    private  StompClient stompSocket = null;
    private  IStomp _stomp;
    
    public void sendToWebSocket(AVL02 device){
        stompSocket = new StompClient(_stomp.LUR());
        
        boolean connected;
        try {
            //esperando por la conexion
            connected = stompSocket.connectBlocking();
        } catch (InterruptedException e ) {
           // logger.error(e.getMessage(),e);
            return;
        }
        if (!connected) {
          // logger.error("fail trying connect to server..");
            return;
        }

        // Subscribing to a topic once STOMP connection is established
        stompSocket.subscribe("/topic/gps", this);

        //Sending JSON message to a server
        DataTransfer data = new DataTransfer();
        data.setLatitud(device.getData().getGprmc().getCoordenadas().getLatitud());
        data.setLongitud(device.getData().getGprmc().getCoordenadas().getLongitud());
        NMEAToDecimal(data,device);
        String message = new Gson().toJson(data, DataTransfer.class);
        logger.debug("sending json to server "+message);
        stompSocket.send("/app/location", message);
    }
    protected void addHandle(IStomp stomp){
        _stomp = stomp;
    }
    
    @Override
    public void onMessage(StompFrame sf) {
        logger.info("Server Msg: " + sf.getBody());
        stompSocket.close();
    }
    public abstract void connect();

    private void NMEAToDecimal(DataTransfer data, AVL02 device) {
        data.setLatitud(ConvertNmea.toDecimalDegrees(device.getData().getGprmc().getCoordenadas().getLatitud())*-1);
        data.setLongitud(ConvertNmea.toDecimalDegrees(device.getData().getGprmc().getCoordenadas().getLongitud())*-1);
    }
}
