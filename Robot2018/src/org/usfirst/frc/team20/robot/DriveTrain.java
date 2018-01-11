
 //Author: Roland Rao, Ben Hogan, and Sydney Walker
package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DriveTrain {
	Objects ob;
	
	public DriveTrain(Objects o){
		ob = o;
	}
	
	public void drive(double speed, double rightTurn, double leftTurn) { // drives
		if (ob.driveShifter.get() == DoubleSolenoid.Value.kReverse) {
			rightTurn *= .95;
			leftTurn *= .95;
		}
		ob.driveMasterRight.set(ControlMode.PercentOutput ,speed - rightTurn + leftTurn);
		ob.driveMasterLeft.set(ControlMode.PercentOutput, -speed + leftTurn - rightTurn);
	}
	
	public void stopDrive() {
		ob.driveMasterRight.set(ControlMode.PercentOutput, 0.0);
		ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.0);
	}
	
	public void shiftHigh() { // shifts into high gear ratio
		ob.driveShifter.set(DoubleSolenoid.Value.kReverse);
	}

	public void shiftLow() { // shifts into low gear ratio
		ob.driveShifter.set(DoubleSolenoid.Value.kForward);
	}
}