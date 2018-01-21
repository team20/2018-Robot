package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Objects {
	TalonSRX driveMasterLeft, driveMasterRight, driveFollowerLeft, driveFollowerRight, driveFollowerLeftTwo, driveFollowerRightTwo;
	TalonSRX collector;
	TalonSRX elevator;
	DoubleSolenoid driveShifter, grabber;//, piston45, piston180;
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

		collector = new TalonSRX(1);
		grabber = new DoubleSolenoid(5, 4);
//		piston45 = new DoubleSolenoid(2, 3);
//		piston180 = new DoubleSolenoid(0, 1);
		
		elevator = new TalonSRX(7);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);
		elevator.config_kP(0, 1/16000, 1000);
		elevator.config_kI(0, 0.0, 1000);
		elevator.config_kD(0, 0.0, 1000);
	}
}
