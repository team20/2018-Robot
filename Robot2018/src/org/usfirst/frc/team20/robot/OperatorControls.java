package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.Timer;

public class OperatorControls {
	Collector collector;
	Elevator elevator;
	Zenith ob;
	boolean override, flipOver, timeStarted, resetting, intakeMode, maxHeight, setIntake;
	double startTime;
	
	public OperatorControls(Collector c, Elevator e, Zenith o){
		collector = c;
		elevator = e;
		ob = o;
		override = false;
		flipOver = false;
		timeStarted = false;
		resetting = false;
		intakeMode = false;
		maxHeight = false;
		setIntake = false;
		startTime = 0;
	}
	
	/**
	 * runs all of the controls of the operator
	 */
	public void operatorControlsPS4(){
		System.out.println("Elevator Position: " + ob.elevatorMaster.getSelectedSensorPosition(0)); 
		System.out.println("Elevator Setpoint: " + elevator.getSetPosition()); 
		//automated
		if(ob.elevatorMaster.getSelectedSensorPosition(0) < Constants.ELEVATOR_MAX_POSITION){
			maxHeight = true;
		} else {
			maxHeight = false;
		}
		if(intakeMode){
			if(!ob.cubeSensor.get()){
				if(!timeStarted){
					startTime = Timer.getFPGATimestamp();
					collector.intake(1.0);
					timeStarted = true;
				}
				collector.close();
				ob.updateCube(true);
			} else {
				ob.updateCube(false);
			}
			if((Timer.getFPGATimestamp() - startTime) > 0.5 && timeStarted){ //time to wait until closes
				collector.intake(0.0);
				intakeMode = false;
				timeStarted = false;
			}
		}
		//controlled
		if(ob.operatorJoy4.getXButton()){
			elevator.setIntake();
			collector.armIntakePosition();
			collector.intake(0.5);
			collector.open();
			intakeMode = true;
			setIntake = true;
			ob.operatorJoy4.setRumble(1.0, 1.0);
		}
		if(ob.operatorJoy4.getCircleButton()){
			collector.stopRollers();
			ob.operatorJoy4.setRumble(0.0, 0.0);
			intakeMode = false;
		}
		if(ob.operatorJoy4.getTriButton()){
			collector.outtake();
			ob.operatorJoy4.setRumble(100, 100);
		}
		if(ob.operatorJoy4.getButtonDUp()){
			elevator.setScaleHigh();
		}
//		if(ob.operatorJoy4.getOptionsButton() && ob.operatorJoy4.getShareButton()) {
//			resetting = true;
//		}
		if(ob.operatorJoy4.getButtonDLeft()){
			elevator.setScaleMid();
		}
		if(ob.operatorJoy4.getButtonDDown()) {
			elevator.setScaleLow();
		}
		if(ob.operatorJoy4.getButtonDRight()) {
			elevator.setSwitch();
		}
		if(ob.operatorJoy4.getRightYAxis() < -0.5) {
			collector.arm45();
		}		
		if(ob.operatorJoy4.getRightYAxis() > 0.5){
			collector.armIntakePosition();
		}
		if(ob.operatorJoy4.getLeftBumperButton()) {
			elevator.setIntake();
			setIntake = true;
		}
		if(Math.abs(ob.operatorJoy4.getLeftYAxis()) > 0.1 && ob.operatorJoy4.getTrackpadButton()){
			if(!maxHeight){
				double speed = 0;
				if(ob.operatorJoy4.getLeftYAxis() < -0.1){
					speed = 1.11*(ob.operatorJoy4.getLeftYAxis() + 0.1);
				} else {
					speed = 1.11*(ob.operatorJoy4.getLeftYAxis() - 0.1);					
				}
				elevator.moveSpeed(speed/2);
			} else {
				System.out.println("AT MAX HEIGHT");
				elevator.moveSpeed(-0.06);
			}
			override = true;
		} else {
			if(override){
				elevator.moveSpeed(0.0);
				override = false;
			}
		}
		if(ob.operatorJoy4.getLeftYAxis() > 0.5 && !ob.operatorJoy4.getLeftStickButton() && !override){
			elevator.upIncrement();
		}
		if(ob.operatorJoy4.getLeftYAxis() < -0.5 && !ob.operatorJoy4.getLeftStickButton() && !override){
			elevator.downIncrement();
		}
		if(ob.operatorJoy4.getLeftTriggerAxis() > 0.1){
			elevator.flipPosition();
			collector.arm45();
			flipOver = true;
		}
//		if(flipOver && !ob.flipSwitch.get()){
		if(flipOver && ob.elevatorMaster.getSelectedSensorPosition(0) < Constants.ELEVATOR_STAGE_THRESHOLD){
			collector.arm180();
			flipOver = false;
		}
		if(ob.operatorJoy4.getRightBumperButton()){
			collector.open();
		}
		if(ob.operatorJoy4.getRightTriggerAxis() > 0.1){
			collector.close();
		}
		if(ob.operatorJoy4.getSquareButton()){
			elevator.setIntake();
			setIntake = true;
			collector.arm100();
		}
		if(ob.operatorJoy4.getOptionsButton()){
			elevator.stop();
			collector.stopRollers();
		}
		if(setIntake && !elevator.elevatorMoving()){
//			elevator.setIntake();
			System.out.println("HIT SETPOINT");
			setIntake = false;
		}
	}	
}