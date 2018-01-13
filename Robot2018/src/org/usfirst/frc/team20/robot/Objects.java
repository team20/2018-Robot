package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Objects {
	TalonSRX driveMasterLeft, driveMasterRight, driveFollowerLeft, driveFollowerRight, driveFollowerLeftTwo, driveFollowerRightTwo;
	DoubleSolenoid driveShifter;
	public Objects(){
		//DriveTrain - each group is all the information for a motor
		driveMasterLeft = new TalonSRX(10);
		driveMasterLeft.setInverted(false);
		driveMasterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);

		driveMasterRight = new TalonSRX(2);
		driveMasterRight.setInverted(true); //TODO encoder should be on this one

		driveFollowerLeft = new TalonSRX(9);
		driveFollowerLeft.follow(driveMasterLeft);
		driveFollowerLeft.setInverted(true);
		
		driveFollowerRight = new TalonSRX(4);		
		driveFollowerRight.follow(driveMasterRight);
		driveFollowerRight.setInverted(false);
		driveMasterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);
		
		driveFollowerLeftTwo = new TalonSRX(8);
		driveFollowerLeftTwo.follow(driveMasterLeft);
		driveFollowerLeftTwo.setInverted(true);
		
		driveFollowerRightTwo = new TalonSRX(3);		
		driveFollowerRightTwo.follow(driveMasterRight);
		driveFollowerRightTwo.setInverted(true);
		
		driveShifter = new DoubleSolenoid(0, 1);		
	}
}
