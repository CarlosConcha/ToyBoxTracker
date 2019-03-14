package com.tracking.gps.init;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.tracking.gps.device.AVL02;
import com.tracking.gps.device.impl.DeviceParse;
import com.tracking.gps.device.parse.AVL02Parse;
import com.tracking.websocket.client.Endpoint;;

public class server {

	private static int PORT = 8000;
    private static final long PAUSE_BETWEEEN_MSGS = 3000; // millisecs
    private static ByteBuffer echoBuffer = ByteBuffer.allocate(256);
    private static ConcurrentHashMap<Integer, SocketChannel> chm
                        = new ConcurrentHashMap<Integer, SocketChannel>();
    
    
    private static int msg = 0;
    public static void main(String args[]) throws Exception {
        // Create a new selector
        Selector selector = Selector.open();
        // Open a listener on each port, and register each one
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket ss = ssc.socket();            
        InetSocketAddress address = new InetSocketAddress(PORT);
        ss.bind(address);
        //registers ACCEPT
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Going to listen on " + PORT);
        //sendMsgsToRandomClients();        
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();            
            String msg = new String();            
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    // Accept the new connection
                    ServerSocketChannel sscNew = (ServerSocketChannel) key
                            .channel();
                    SocketChannel sc = sscNew.accept();
                    sc.configureBlocking(false);
                    // Add the new connection to the selector                    
                    sc.register(selector, SelectionKey.OP_READ);
                    // Add the socket channel to the list
                    chm.put(sc.hashCode(), sc);
                    it.remove();
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    // Read the data
                    SocketChannel sc = (SocketChannel) key.channel();            
                    int code = 0;
                    while ((code = sc.read(echoBuffer)) > 0) {
                        byte b[] = new byte[echoBuffer.position()];
                        echoBuffer.flip();
                        echoBuffer.get(b);
                        msg+=new String(b, "UTF-8");
                    }
                    // removes the new line
                    if(msg.length()>1)
                           msg = msg.substring(0, msg.length()-2);
                    
                    if (code == -1 ||
                        msg.toUpperCase().indexOf("BYE")>-1){
                        chm.remove(sc.hashCode());
                        sc.close();
                    } else {
                        echoBuffer.clear();
                    }
                   //System.out.println("msg: " + msg  + " from: " + sc + "code:  " + code );
                   DeviceParse<AVL02> device = new AVL02Parse(msg);
                   AVL02 avl02 = device.parse();
                   System.out.println(avl02);
                   /*final Endpoint endpoint = new Endpoint(new URI("wss://real.okcoin.cn:10440/websocket/okcoinapi"));
                   endpoint.addMessageHandler(new Endpoint.MessageHandler() {
					@Override
					public void handleMessage(String message) {
						System.out.println(message);
						
					}
				});
                   endpoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");*/
                   it.remove();
                }
            }
        }        
    }        
    /**
     * This method sends messages to a random outPutStream
     */
    private static void sendMsgsToRandomClients() {
        new Thread("Send-to-Clients") {
            public void run() {
                try {
                    while (true) {
                        Random generator = new Random();
                        if(chm.keySet().size()>0){
                            Integer randomKey = new ArrayList<Integer>(
                                    chm.keySet()).get(generator.nextInt(chm.keySet().size()));
                            SocketChannel sc = chm.get(randomKey);
                            try {
                                msg++;                                
                                ByteBuffer buf = ByteBuffer.wrap(("From server to Client msg n� - "+ msg + "\n").getBytes());
                                sc.write(buf);
                            } catch (IOException e) {
                                e.printStackTrace();
                                chm.remove(randomKey);
                            }                            
                        }
                        Thread.sleep(PAUSE_BETWEEEN_MSGS);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
