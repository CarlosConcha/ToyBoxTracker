/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.tracking.gps.NMEA.endpoint.ToyBoxEndpoint;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Pussy Destroyer
 */

public class WebSocketClientTest {
    private WebSocketContainer container;
    private ToyBoxEndpoint endpoint;
    
    @Before
    public void init(){
     this.container = ContainerProvider.getWebSocketContainer();
     this.endpoint = new ToyBoxEndpoint();
    }
    
    @Test
    public void pingServer() throws URISyntaxException, DeploymentException, IOException{
        this.container.connectToServer(this.endpoint, new URI("ws://localhost:8080/app/hello"));
        this.endpoint.sendMessage("olaaa peaso de websocket!!!");
    }
}
