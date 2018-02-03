package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotController;

public class Objects implements Loggable{
	TalonSRX driveMasterLeft, driveMasterRight;//, driveFollowerLeft, driveFollowerRight, driveFollowerLeftTwo, driveFollowerRightTwo;
	VictorSPX driveFollowerLeft, driveFollowerRight, driveFollowerLeftTwo, driveFollowerRightTwo;
	TalonSRX collectorMaster, elevatorMaster;
	VictorSPX collectorFollower, elevatorFollower;
	DoubleSolenoid driveShifter;//, grabber;//, piston45, piston180;
	Controller driverJoy, operatorJoy;
	DigitalInput flipSwitch;
//	PowerDistributionPanel PDBoard;
	DriverStation driverStation;
	RobotController controller;
	double gyroSetpoint = 0, gyroAngle = 0, leftSide = 0, rightSide = 0, driveLeftSetpoint = 0, driveRightSetpoint = 0, elevatorSetpoint = 0;
	boolean cube = false;
	public Objects(){
		//DriveTrain - each group is all the information for a motor
		driveMasterLeft = new TalonSRX(1);//2017 = 10
		driveMasterLeft.setInverted(false);
		driveMasterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);

		driveMasterRight = new TalonSRX(2);//4
		driveMasterRight.setInverted(false);
		driveMasterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);

		driveFollowerLeft = new VictorSPX(3);
//		driveFollowerLeft = new TalonSRX(9);
		driveFollowerLeft.follow(driveMasterLeft);
		driveFollowerLeft.setInverted(false); 

		driveFollowerRight = new VictorSPX(4);
//		driveFollowerRight = new TalonSRX(2);		
		driveFollowerRight.follow(driveMasterRight);
		driveFollowerRight.setInverted(false);
		
		driveFollowerLeftTwo = new VictorSPX(5);
//		driveFollowerLeftTwo = new TalonSRX(8);
		driveFollowerLeftTwo.follow(driveMasterLeft);
		driveFollowerLeftTwo.setInverted(true);
		
		driveFollowerRightTwo = new VictorSPX(6);
//		driveFollowerRightTwo = new TalonSRX(3);		
		driveFollowerRightTwo.follow(driveMasterRight);
		driveFollowerRightTwo.setInverted(true);
		
		driveShifter = new DoubleSolenoid(0, 1);		

		collectorMaster = new TalonSRX(1);
		collectorFollower = new VictorSPX(0);
		collectorFollower.follow(collectorMaster);
//		grabber = new DoubleSolenoid(5, 4);
//		piston45 = new DoubleSolenoid(2, 3);
//		piston180 = new DoubleSolenoid(0, 1);
		flipSwitch = new DigitalInput(0);
		
		elevatorMaster = new TalonSRX(7);
		elevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDIDX, 1000);
		elevatorMaster.config_kP(0, 0.5, 1000);
		elevatorMaster.config_kI(0, 0.0, 1000);
		elevatorMaster.config_kD(0, 0.0, 1000);
		elevatorMaster.config_kF(0, 0.0, 1000);
		elevatorFollower = new VictorSPX(0);
		elevatorFollower.follow(elevatorMaster);
		driverJoy = new Controller(0);
		operatorJoy = new Controller(1);
		
//		PDBoard = new PowerDistributionPanel();
		driverStation = DriverStation.getInstance();
	}
	
	public void updateGyroSetpoint(double set){
		gyroSetpoint = set;
	}
	public void updateGyroAngle(double a){
		gyroAngle = a;
	}
	public void updateLeftSide(double l){
		leftSide = l;
	}
	public void updateRightSide(double r){
		rightSide = r;
	}
	public void updateLeftSetpoint(double l){
		driveLeftSetpoint = l;
	}
	public void updateRightSetpoint(double r){
		driveRightSetpoint = r;
	}
	public void updateElevatorSetpoint(double e){
		driveRightSetpoint = e;
	}
	public void updateCube(boolean c){ //TODO add the get things
		cube = c;
 	}
	@Override
	public String log() { //TODO make everything work
		String output = "";
		if(driverStation.isAutonomous()){
			output += "Auto,";
		} else {
			output += "Tele,";
		}
		output += driverStation.getMatchTime() + ",";
		output += RobotController.getInputVoltage() + ",";
		output += "Total Current WOOO" + ",";
//		output += PDBoard.getTotalCurrent() + ",";
		output += gyroSetpoint + "," + gyroAngle;
		output += driverJoy.getLeftYAxis() + "," + driverJoy.getLeftTriggerAxis() + "," + driverJoy.getRightTriggerAxis() + ",";
		output += leftSide + "," + rightSide + ",";
		output += driveMasterRight.getOutputCurrent() + "," + driveFollowerRight.getOutputCurrent() + "," + driveFollowerRightTwo.getOutputCurrent() + ",";
		output += driveMasterLeft.getOutputCurrent() + "," + driveFollowerLeft.getOutputCurrent() + "," + driveFollowerLeftTwo.getOutputCurrent() + ",";
		output += driveLeftSetpoint + "," + driveRightSetpoint + "," + driveMasterLeft.getSelectedSensorPosition(0) + "," + driveMasterRight.getSelectedSensorPosition(0) + ",";
		output += (driveMasterLeft.getSelectedSensorVelocity(0)/4096*600) + "," + (driveMasterRight.getSelectedSensorVelocity(0)/4096*600) + ",";
		output += driveShifter.get() + ",";
		output += elevatorMaster.getOutputCurrent() + "," + elevatorSetpoint + "," + elevatorMaster.getSelectedSensorPosition(0);
		output += collectorMaster.getOutputCurrent() + ",";// + grabber.get() + "," + cube + ",";// + piston45.get() + "," + piston180.get() + ",";
		output += flipSwitch.get() + "\n";
		return output;
	}
}
