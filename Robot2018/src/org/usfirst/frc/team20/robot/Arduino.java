package org.usfirst.frc.team20.robot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Arduino {
	I2C Wire;
	private byte[] sensorData;
	private byte IRSensor;

	Arduino(int port) {
		Wire = new I2C(Port.kOnboard, port);	//initializes I2C communication on port 1
		sensorData = new byte[1];				//data from sensors
	}

	/**
	 * get array of data from Arduino and sets each variable to the correct value
	 */
	public void getSensorData() {
		Wire.read(1, 1, sensorData);	// gets array of data from Arduino
		IRSensor = sensorData[0]; 		// sets each variable to the correct value (for now there is just one)
	}

	/**
	 * checks for the presence of an object in front of the IR sensor
	 * @return true if there is an object in front of the IR sensor
	 */
	public boolean getIRSensor() {	// checks for the presence of an object in front of the IR sensor
		if (IRSensor == 1)
			return true;
		else
			return false;
	}

	/**
	 * @param writeData: data that needs to be written to the Arduino
	 */
	public void write(byte[] writeData) {	// writes an array of bytes to the Arduino
		Wire.writeBulk(writeData, 1);
	}

	/**
	 * @param redAlliance: are we on the red alliance?
	 * @param cube: do we have a cube?
	 * @param climbing: are we climbing?
	 * @param current: are we over our current draw limit?
	 * @param off: should the lights be off?
	 * sends the code for the needed light pattern to the Arduino
	 */
	public void lights(boolean redAlliance, boolean cube, boolean climbing, boolean current, boolean off) {
		byte[] send = new byte[1];
		if (current)
			send[0] = 7;
		else if (climbing)
			send[0] = 6;
		else if (cube)
			send[0] = 5;
		else if (redAlliance)
			send[0] = 3;
		else if (!redAlliance)
			send[0] = 4;
		else
			send[0] = 0;
		write(send);
	}
}