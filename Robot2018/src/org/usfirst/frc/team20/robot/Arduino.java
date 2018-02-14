package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Arduino {
	I2C Wire;									//initializes I2C communication on port 1
	private final int numOfBytes = 1;					//number of bytes stored in sensorData
	private byte[] sensorData = new byte[numOfBytes];	//all data from sensors is stored here
	private byte IRSensor;								//data from IR sensor
	
	Arduino(int port) {
		Wire = new I2C(Port.kOnboard, port);
	}
	
	/**
	 * get array of data from Arduino and sets each variable to the correct value
	 */
	public void getSensorData() {
		Wire.read(1, numOfBytes, sensorData);
		IRSensor = sensorData[0];
	}
	
	/**
	 * checks for the presence of an object in front of the IR sensor
	 * @return true if there is an object in front of the IR sensor
	 */
	public boolean getIRSensor() {
		if (IRSensor == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param writeData: data that needs to be written to the Arduino
	 */
	public void write(byte[] writeData) {
		Wire.writeBulk(writeData);
	}
	/**
	 * 
	 * @param redAlliance: are we on the red alliance?
	 * @param cube: do we have a cube?
	 * @param climbing: are we climbing?
	 * @param current: are we over our current draw limit?
	 * @param off: should the lights be off?
	 * sends the code for the needed light pattern to the Arduino
	 */
	public void lights(boolean redAlliance, boolean cube, boolean climbing, boolean current, boolean off){
		byte[] send = new byte[1];
		if(current){
			send[0] = 5;
		} else if (climbing){
			send[0] = 4;
		} else if (cube) {
			send[0] = 3;
		} else if (redAlliance){
			send[0] = 1;
		} else if(!redAlliance){
			send[0] = 2;
		} else {
			send[0] = 0;
		}
		write(send);
	}
}
