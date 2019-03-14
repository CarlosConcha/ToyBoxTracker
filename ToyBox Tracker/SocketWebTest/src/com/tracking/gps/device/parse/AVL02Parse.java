package com.tracking.gps.device.parse;

import com.tracking.gps.NMEA.GPRMC;
import com.tracking.gps.device.AVL02;
import com.tracking.gps.device.data.DataGPRMC;
import com.tracking.gps.device.impl.DeviceParse;
import com.tracking.gps.util.UtilParse;

public class AVL02Parse implements DeviceParse<AVL02>{

	private final AVL02 avlDevice = new AVL02();
	private String trama;
	//constructor
	public AVL02Parse(final String trama) {
		this.trama = trama;
	}
	
	@Override
	public AVL02 parse() {
		if (trama != null && !trama.isEmpty() && trama.substring(0,2).equals(AVL02.getHeader())) {
			try {
			String segmentos[] = trama.split("\\|");
			avlDevice.setLen(segmentos[0].substring(2, 4));
			avlDevice.setImei(segmentos[0].substring(4));
			
			String segmentoGprmc[] = segmentos[1].split(",");
			avlDevice.setAlarm(segmentoGprmc[0].substring(0, 2));
			
			GPRMC gprmc = new GPRMC();
			DataGPRMC data = new DataGPRMC();
			gprmc.setTime(UtilParse.floatParse(segmentoGprmc[1]));
			gprmc.setStatus(UtilParse.charParse(segmentoGprmc[2]));
			gprmc.getCoordenadas().setLatitud(UtilParse.doubleParse(segmentoGprmc[3]));
			gprmc.getCoordenadas().setLatitudPos(UtilParse.charParse(segmentoGprmc[4]));
			gprmc.getCoordenadas().setLongitud(UtilParse.doubleParse(segmentoGprmc[5]));
			gprmc.getCoordenadas().setLongitudPos(UtilParse.charParse(segmentoGprmc[6]));
			gprmc.setSpeed(UtilParse.floatParse(segmentoGprmc[7]));
			gprmc.setCourse(UtilParse.floatParse(segmentoGprmc[8]));
			gprmc.setDate(UtilParse.floatParse(segmentoGprmc[9]));
			gprmc.setVariation(UtilParse.floatParse(segmentoGprmc[10]));
			gprmc.setGrado(UtilParse.charParse(segmentoGprmc[11]));
			gprmc.setChecksum(segmentoGprmc[12]);
			
			data.setGprmc(gprmc);
			data.setPdop(UtilParse.floatParse(segmentos[3]));
			data.setHdop(UtilParse.floatParse(segmentos[4]));
			data.setVdop(UtilParse.floatParse(segmentos[5]));
			data.setStatus(segmentos[6]);
			data.setRtc(segmentos[7]);
			data.setVoltage(segmentos[8]);
			
			avlDevice.setData(data);
			avlDevice.setAdc(segmentos[9]);
			avlDevice.setLacci(segmentos[10]);
			avlDevice.setTemperature(segmentos[11]);
			avlDevice.setSerialId(UtilParse.shortParse(segmentos[12]));
			avlDevice.setChecksum(segmentos[13]);
			}catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("error al parsear campo");
			}
		}
		return avlDevice;
	}
}