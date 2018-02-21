package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

public class Zenith implements Loggable{
	TalonSRX driveMasterLeft, driveMasterRight;
	VictorSPX driveFollowerLeft, driveFollowerRight, driveFollowerLeftTwo, driveFollowerRightTwo;
	TalonSRX collectorMaster, elevatorMaster, elevatorFollower, climberMaster;
	VictorSPX collectorFollower;//, climberFollower;
	DoubleSolenoid driveShifter, grabber, singleUp, doubleUp, climberSpringPin;
	PS4Controller driverJoy4, operatorJoy4;
	DigitalInput flipSwitch, cubeSensor;
	DriverStation driverStation;
	RobotController controller;
	double gyroSetpoint = 0, gyroAngle = 0, leftSide = 0, rightSide = 0, driveLeftSetpoint = 0, driveRightSetpoint = 0, elevatorSetpoint = 0;
	boolean cube = false;

	public Zenith(){
		//DriveTrain - each group is all the information for a motor
		driveMasterLeft = new TalonSRX(1);
		driveMasterLeft.setInverted(true);
		driveMasterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);
		driveMasterLeft.configClosedloopRamp(0.1, 1000);
		driveMasterLeft.configOpenloopRamp(0.1, 1000);

		driveMasterRight = new TalonSRX(2);
		driveMasterRight.setInverted(true);
		driveMasterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);
		driveMasterRight.configClosedloopRamp(0.1, 1000);
		driveMasterRight.configOpenloopRamp(0.1, 1000);

		driveFollowerLeft = new VictorSPX(3);
		driveFollowerLeft.follow(driveMasterLeft);
		driveFollowerLeft.setInverted(true);

		driveFollowerRight = new VictorSPX(4);
		driveFollowerRight.follow(driveMasterRight);
		driveFollowerRight.setInverted(true);

		driveFollowerLeftTwo = new VictorSPX(5);
		driveFollowerLeftTwo.follow(driveMasterLeft);
		driveFollowerLeftTwo.setInverted(false);

		driveFollowerRightTwo = new VictorSPX(6);
		driveFollowerRightTwo.follow(driveMasterRight);
		driveFollowerRightTwo.setInverted(false);

		driveShifter = new DoubleSolenoid(14, 0, 1);

		//Manipulator
		collectorMaster = new TalonSRX(7);
		collectorMaster.setInverted(false);
		collectorFollower = new VictorSPX(8);
		collectorFollower.setInverted(true);
		collectorFollower.follow(collectorMaster);
		singleUp = new DoubleSolenoid(14, 2, 3);
		grabber = new DoubleSolenoid(14, 4, 5);
		doubleUp = new DoubleSolenoid(14, 6, 7);
		flipSwitch = new DigitalInput(1);
		cubeSensor = new DigitalInput(0);
		
		//Elevator
		elevatorMaster = new TalonSRX(9);
		elevatorMaster.setInverted(false);
		elevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);
		elevatorMaster.configClosedloopRamp(0.2, 1000);
		elevatorMaster.configOpenloopRamp(0.2, 1000);
		elevatorMaster.enableVoltageCompensation(true);
		elevatorFollower = new TalonSRX(10);
		elevatorFollower.setInverted(false);
		elevatorFollower.follow(elevatorMaster);
		
		//Climber
		climberMaster = new TalonSRX(11);
		climberMaster.setInverted(false);
//		climberFollower = new VictorSPX(12);
//		climberFollower.setInverted(true);
//		climberFollower.follow(climberMaster);
//		climberSpringPin = new DoubleSolenoid(15, 0, 1);
		
		//Joysticks
		driverJoy4 = new PS4Controller(0);
		operatorJoy4 = new PS4Controller(1);
		
		//Blackbox Related Objects
		driverStation = DriverStation.getInstance();
	}
	
	/**
	 * updates the set point of the gyroscope for Blackbox
	 * @param set: set point of the gyroscope
	 */
	public void updateGyroSetpoint(double set){
		gyroSetpoint = set;
	}
	
	/**
	 * updates the angle of the gyroscope for Blackbox
	 * @param a: current angle of the gyroscope
	 */
	public void updateGyroAngle(double a){
		gyroAngle = a;
	}

	/**
	 * updates the set percent output of the left drive train for Blackbox
	 * @param l: current set percent output of the left drive train
	 */
	public void updateLeftSide(double l){
		leftSide = l;
	}

	/**
	 * updates the set percent output of the right drive train for Blackbox
	 * @param r: current set percent output of the right drive train
	 */
	public void updateRightSide(double r){
		rightSide = r;
	}

	/**
	 * updates the set encoder position of the left drive train for Blackbox
	 * @param l: current set encoder position of the left drive train
	 */
	public void updateLeftSetpoint(double l){
		driveLeftSetpoint = l;
	}

	/**
	 * updates the set encoder position of the right drive train for Blackbox
	 * @param r: current set encoder position of the right drive train
	 */
	public void updateRightSetpoint(double r){
		driveRightSetpoint = r;
	}

	/**
	 * updates the set encoder position of the elevator for Blackbox
	 * @param e: current set encoder position of the elevator
	 */
	public void updateElevatorSetpoint(double e){
		driveRightSetpoint = e;
	}

	/**
	 * updates whether or not the robot has a cube for Blackbox
	 * @param c: true if we have a cube, false otherwise
	 */
	public void updateCube(boolean c){
		cube = c;
 	}
	
	/**
	 * @return total current draw from all the motors
	 */
	public double getTotalCurrent(){
		double current = 0;
		current += driveMasterLeft.getOutputCurrent() + driveFollowerLeft.getOutputCurrent() + driveFollowerLeftTwo.getOutputCurrent();
		current += driveMasterRight.getOutputCurrent() + driveFollowerRight.getOutputCurrent() + driveFollowerRightTwo.getOutputCurrent();
		current += collectorMaster.getOutputCurrent() + collectorFollower.getOutputCurrent();
		current += elevatorMaster.getOutputCurrent() + elevatorFollower.getOutputCurrent();
		current += climberMaster.getOutputCurrent();
//		current += climberFollower.getOutputCurrent();
		return current;
	}

	public boolean currentLimit(){
		return getTotalCurrent() > Constants.CURRENT_LIMIT;
	}
	/**
	 * Generates a log of all of the information to be send to the Driver Station to be saved in a .csv
	 * @return the log generated by the robot to be saved by the Driver Station
	 */
	@Override
	public String log() {
		String output = "";
		if(driverStation.isAutonomous()){
			output += "Auto,";
		} else {
			output += "Tele,";
		}
 		output += driverStation.getMatchTime() + ",";
		output += RobotController.getInputVoltage() + ",";
		output += getTotalCurrent() + ",";
		output += gyroSetpoint + "," + gyroAngle;
		output += driverJoy4.getLeftYAxis() + "," + driverJoy4.getLeftTriggerAxis() + "," + driverJoy4.getRightTriggerAxis() + ",";
		output += leftSide + "," + rightSide + ",";
		output += driveMasterRight.getOutputCurrent() + "," + driveFollowerRight.getOutputCurrent() + "," + driveFollowerRightTwo.getOutputCurrent() + ",";
		output += driveMasterLeft.getOutputCurrent() + "," + driveFollowerLeft.getOutputCurrent() + "," + driveFollowerLeftTwo.getOutputCurrent() + ",";
		output += driveLeftSetpoint + "," + driveRightSetpoint + "," + driveMasterLeft.getSelectedSensorPosition(0) + "," + driveMasterRight.getSelectedSensorPosition(0) + ",";
		output += (driveMasterLeft.getSelectedSensorVelocity(0)/4096*600) + "," + (driveMasterRight.getSelectedSensorVelocity(0)/4096*600) + ",";
		output += driveShifter.get() + ",";
		output += elevatorMaster.getOutputCurrent() + "," + elevatorSetpoint + "," + elevatorMaster.getSelectedSensorPosition(0);
		output += collectorMaster.getOutputCurrent() + "," + grabber.get() + "," + cube + "," + singleUp.get() + "," + doubleUp.get() + ",";
		output += flipSwitch.get() + "\n";
		return output;
	}
}
