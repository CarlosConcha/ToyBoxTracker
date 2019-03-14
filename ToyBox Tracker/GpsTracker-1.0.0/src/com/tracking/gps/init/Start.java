package com.tracking.gps.init;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.util.Iterator;
import java.util.Set;

public class Start {

	public static void main(String[] args) throws Exception {
		Selector selector = Selector.open();
		ServerSocketChannel ssShannel = ServerSocketChannel.open();
		ssShannel.configureBlocking(false);
		ssShannel.socket().bind(new InetSocketAddress(1234));
	
		ssShannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true) {
			if(selector.select() <= 0) {
				continue;
			}
			processReadySet(selector.selectedKeys());
		}

	}
	public static void processReadySet(Set readySet) throws Exception {
	    Iterator iterator = readySet.iterator();
	    while (iterator.hasNext()) {
	      SelectionKey key = (SelectionKey) iterator.next();
	      iterator.remove();
	     /* if (key.isAcceptable()) {
	        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
	        SocketChannel sChannel = (SocketChannel) ssChannel.accept();
	        sChannel.configureBlocking(false);
	        sChannel.register(key.selector(), SelectionKey.OP_READ);
	      }*/
	      if (key.isAcceptable()) {
	        String msg = processRead(key);
	        if (msg.length() > 0) {
	          SocketChannel sChannel = (SocketChannel) key.channel();
	          ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
	          sChannel.write(buffer);
	        }
	      }
	    }
	  }
	  public static String processRead(SelectionKey key) throws Exception {
	    SocketChannel sChannel = (SocketChannel) key.channel();
	    ByteBuffer buffer = ByteBuffer.allocate(1024);
	    int bytesCount = sChannel.read(buffer);
	    if (bytesCount > 0) {
	      buffer.flip();
	      return new String(buffer.array());
	    }
	    return "NoMessage";
	  }
	

}
