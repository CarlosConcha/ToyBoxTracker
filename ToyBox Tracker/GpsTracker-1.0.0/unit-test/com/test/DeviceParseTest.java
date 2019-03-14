package com.test;

import org.junit.Test;

import com.tracking.gps.device.AVL02;
import com.tracking.gps.device.impl.DeviceParse;
import com.tracking.gps.device.parse.AVL02Parse;

public class DeviceParseTest {

	@Test
	public void AVL02Parse() {
		String trama = "$$B4867965020614190|AA$GPRMC,221357.00,A,3334.86253,S,07039.02052,W,0.043,"
				+",250219,,,A*71|1.58|0.83|1.34|000000000000|20190225221357|03740000 |00000000" + 
				"|32D24F13|0000|0.0000|0006|02F2";
		DeviceParse<AVL02> device = new AVL02Parse(trama);
		AVL02 avl = device.parse();
		System.out.println(avl);
	}
}