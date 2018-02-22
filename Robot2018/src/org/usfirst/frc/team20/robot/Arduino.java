 package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Arduino {
	I2C Wire;									//initializes I2C communication on port 1
	Zenith ob;
	private boolean prevCube;
	Arduino(int port, Zenith o) {
		Wire = new I2C(Port.kOnboard, port);
		ob = o;
		prevCube = false;
	}

	/**
	 * 
	 * @param writeData: data that needs to be written to the Arduino
	 */
	public void write(byte[] writeData) {
		Wire.writeBulk(writeData);
	}
	/**
	 * @param redAlliance: are we on the red alliance?
	 * @param cube: do we have a cube?
	 * @param climbing: are we climbing?
	 * @param current: are we over our current draw limit?
	 * @param off: should the lights be off?
	 * sends the code for the needed light pattern to the Arduino
	 */
	public void lights(Alliance alliance, boolean cube, boolean climbing, boolean current, boolean off, boolean elevatorMoving){
		if(cube == prevCube){
			cube = false;
		} else {
			prevCube = cube;
		}
		byte[] send = new byte[1];
		if(off){
			send[0] = 21;
		}else if(current){
			send[0] = 28;
		} else if (climbing){
			send[0] = 27;
		} else if (cube) {
			send[0] = 26;
		} else if (elevatorMoving){
			send[0] = (byte)((ob.elevatorMaster.getSelectedSensorPosition(0)/Constants.ELEVATOR_MAX_POSITION)*20);
		} else if (alliance == Alliance.Red){
			if(ob.driveShifter.get() == Value.kForward)
				send[0] = 22;
			else
				send[0] = 23;
		} else if(alliance == Alliance.Blue){
			if(ob.driveShifter.get() == Value.kForward)
				send[0] = 24;
			else
				send[0] = 25;
		}
		write(send);
	}
}