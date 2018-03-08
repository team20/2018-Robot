package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DriveTrain {
	Zenith ob;
	
 	public DriveTrain(Zenith o){
		ob = o;
	}
	
	/**
	 * makes the drive train move in arcade drive
	 * @param speed: straight axis value
	 * @param rightTurn: right axis value
	 * @param leftTurn: left axis value
	 */
	public void drive(double speed, double rightTurn, double leftTurn) {
		ob.driveMasterRight.set(ControlMode.PercentOutput, speed - rightTurn + leftTurn);
		ob.driveMasterLeft.set(ControlMode.PercentOutput, -speed + leftTurn - rightTurn);
        ob.updateLeftSide(speed - rightTurn + leftTurn);
        ob.updateRightSide(-speed + leftTurn - rightTurn);
	}
	
	public void turn(double speed, boolean left){
		if(left){
			ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.0);
			ob.driveMasterRight.set(ControlMode.PercentOutput, speed);
		} else {
			ob.driveMasterLeft.set(ControlMode.PercentOutput, -speed);
			ob.driveMasterRight.set(ControlMode.PercentOutput, 0.0);			
		}
	}
	/**
	 * stops the drive train's movement
	 */
	public void stopDrive() {
		ob.driveMasterRight.set(ControlMode.PercentOutput, 0.0);
		ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.0);
	}
	
	/**
	 * puts the drive train in high gear (fast)
	 */
	public void shiftHigh() {
		ob.driveShifter.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * puts the drive train in low gear (strong)
	 */
	public void shiftLow() {
		ob.driveShifter.set(DoubleSolenoid.Value.kForward);
	}
}