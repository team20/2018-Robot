package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Collector {
	Zenith ob;

	public Collector(Zenith o){
		ob = o;
	}
	
	/**
	 * runs the rollers in order to collect a cube
	 */
	public void intake(double speed){
		ob.collectorMaster.set(ControlMode.PercentOutput, speed);
	}

	/**
	 * runs the rollers in order to release a cube (with force)
	 */
	public void outtake(){
		ob.collectorMaster.set(ControlMode.PercentOutput, -1.0);
	}
	
	/**
	 * stops the rollers from moving
	 */
	public void stopRollers(){
		ob.collectorMaster.set(ControlMode.PercentOutput, 0.0);
	}
	
	/**
	 * closes the manipulator
	 */
	public void close(){
//		ob.grabber.set(Value.kForward);
	}

	/**
	 * opens the manipulator
	 */
	public void open(){
//		ob.grabber.set(Value.kReverse);
	}

	/**
	 * sets the manipulator to the horizontal position (to allow intaking)
	 */
	public void armIntakePosition(){
//		ob.piston45.set(Value.kReverse);
//		ob.piston180.set(Value.kReverse);
	}
	
	/**
	 * sets the manipulator upwards at a 45 degree angle
	 */
	public void arm45(){
//		ob.piston45.set(Value.kForward);
//		ob.piston180.set(Value.kReverse);
	}
	
	/**
	 * sets the manipulator in the starting position - up 100 degrees
	 */
	public void arm100(){
//		ob.piston45.set(Value.kReverse);
//		ob.piston180.set(Value.kForward);
	}

	/**
	 * flips the manipulator over the top of the elevator carriage
	 */
	public void arm180(){
//		ob.piston45.set(Value.kForward);
//		ob.piston180.set(Value.kForward);
	}
}