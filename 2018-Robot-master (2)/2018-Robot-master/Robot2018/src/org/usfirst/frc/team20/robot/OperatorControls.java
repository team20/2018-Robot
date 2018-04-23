package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class OperatorControls {
	Collector collector;
	Elevator elevator;
	Zenith ob;
	boolean override, flipOver, timeStarted, intakeMode, maxHeight, downTimerStarted, flipPositionSet,
	flipped, positionChange, stayDown;
	double startTime;
	double rumble;
	int toRun;
	private final double FLIP_WAIT_TIME = 0.75;
	
	public OperatorControls(Collector c, Elevator e, Zenith o){
		collector = c;
		elevator = e;
		ob = o;
		override = flipOver = timeStarted = intakeMode = maxHeight = downTimerStarted = flipPositionSet = flipped = positionChange = false;
		stayDown = false;
		startTime = 0;
		rumble = 0;
		toRun = 0;
	}
	
	/**
	 * runs all of the controls of the operator
	 */
	public void operatorControlsPS4(){
		//CONTROLLER
		if(ob.operatorJoy4.getXButton()){
			collector.intake(0.65);
			collector.open();
			intakeMode = true;
			rumble = .5;
		}
		if(ob.operatorJoy4.getCircleButton()){
			collector.stopRollers();
			rumble = 0;
			intakeMode = false;
		}
		if(ob.operatorJoy4.getTriButton()){
			collector.intake(.65);
			rumble = 1;
		}else if (!DriverControls.collectorBeenRun&&!intakeMode){
			collector.stopRollers();
		}
		if(ob.operatorJoy4.getButtonDUp()){
			positionChange = false;
			elevator.setScaleHigh();
			// collector.arm45();
		}
		if(ob.operatorJoy4.getOptionsButton() && ob.operatorJoy4.getShareButton()) {
			elevator.reset();
		}
		if(ob.operatorJoy4.getButtonDLeft()){
			positionChange = true;
			elevator.setScaleMid();
		}
		if(ob.operatorJoy4.getButtonDDown()) {
			positionChange = true;
			elevator.setScaleLow();
		}
		if(ob.operatorJoy4.getButtonDRight()) {
			toRun = 0;
			if(elevator.aboveThreshold()){
				collector.armIntakePosition();
				startTime = Timer.getFPGATimestamp();
				downTimerStarted = true;
			} else {
 				runFunction();
			}
		}
		if(ob.operatorJoy4.getRightYAxis() < -0.5) {
			collector.arm45();
		}		
		if(ob.operatorJoy4.getRightYAxis() > 0.5){
			collector.armIntakePosition();
		}
		if(ob.operatorJoy4.getLeftBumperButton()) {
			toRun = 2;
			if(flipped){
				collector.armIntakePosition();
				startTime = Timer.getFPGATimestamp();
				downTimerStarted = true;
			}
			if(!downTimerStarted){
				runFunction();
			}
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
				elevator.stop();
				elevator.moveSpeed(0.0);
				override = false;
			}
		}
		if(ob.operatorJoy4.getLeftYAxis() < -0.5 && !ob.operatorJoy4.getLeftStickButton() && !override){
			elevator.upIncrement();
		}
		if(ob.operatorJoy4.getLeftYAxis() > 0.5 && !ob.operatorJoy4.getLeftStickButton() && !override){
			elevator.downIncrement();
		}
		if(ob.operatorJoy4.getTrackpadButton() && Math.abs(ob.operatorJoy4.getLeftYAxis()) < 0.1){
			stayDown = true;
		}
		if(ob.operatorJoy4.getLeftTriggerAxis() > 0.1){
//			if(!flipPositionSet){
//				elevator.flipPosition();	
//				flipPositionSet = true;
//			}
			collector.arm45();
			flipOver = true;
		}
		if(ob.operatorJoy4.getLeftTriggerAxis() == 0){
			flipPositionSet = false;
		}
		if(ob.operatorJoy4.getRightBumperButton()){
			collector.open();
		}
		if(ob.operatorJoy4.getRightTriggerAxis() > 0.1){
			collector.close();
			collector.stopRollers();
			intakeMode = false;
		}
		if(ob.operatorJoy4.getSquareButton()){
			toRun = 1;
			collector.arm100();							//This was also in the if(), should any of it be
			if(flipped){								//-Andrew
				startTime = Timer.getFPGATimestamp();
				downTimerStarted = true;
			} else {
				runFunction();
			}
		}
		if(ob.operatorJoy4.getOptionsButton()){
			elevator.stop();
			collector.stopRollers();
			rumble = 0;
		}
		//SAFETY INFORMATION
		System.out.println("CURRENT: " + ob.elevatorMaster.getOutputCurrent());
//		elevator.limitCurrent();
		elevator.limitPosition();
		if(ob.elevatorMaster.getSelectedSensorPosition(0) < Constants.ELEVATOR_MAX_POSITION){
			maxHeight = true;
		} else {
			maxHeight = false;
		}
		if(ob.doubleUp.get() == Value.kForward && ob.singleUp.get() == Value.kForward){
			flipped = true;
		} else {
			flipped = false;
		}
		//BOOLEANS
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
			if(timeStarted && (Timer.getFPGATimestamp() - startTime) > 0.5){ //time to wait until closes
				collector.stopRollers();
				rumble = 0;
				intakeMode = false;
				timeStarted = false;
				if(!ob.cubeSensor.get()){
					ob.updateCube(true);
//					if(stayDown){
//						stayDown = false;
//					} else {
//						collector.arm100();						
//					}
				} else {
					intakeMode = true;
				}
			}
		}
		if(collector.intakeOn){
			rumble = 1.0;
		} else {
			rumble = 0.0;
		}
		if(downTimerStarted){
			if(flipWaitTime()){
				runFunction();
				downTimerStarted = false;
			}
		}
		if(flipOver && elevator.aboveThreshold()){
			collector.arm180();
			flipOver = false;
		}
		if(flipped && positionChange){
			elevator.flipPosition();
			positionChange = false;
		}
		ob.operatorJoy4.setRumble(rumble);
	}	

	/**
	 * @return true if the elevator has waiting long enough to flip the manipulator
	 */
	private boolean flipWaitTime(){
		if(Math.abs(startTime - Timer.getFPGATimestamp()) > FLIP_WAIT_TIME){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * runs the function designated by the integer toRun
	 * toRun = 0; sets the elevator to switch height
	 * toRun = 1; sets the elevator to intake position with the arm at the 100 degree position
	 * toRun = 2; sets the elevator and arm to intake position and into intake mode
	 */
	private void runFunction(){
		switch(toRun){
		case 0: 
			elevator.setSwitch();
			break;
		case 1:
			elevator.setIntake();
			break;
		case 2: 
			elevator.setIntake();
			collector.armIntakePosition();
			collector.intake(0.5);
			intakeMode = true;
			break;
		}
	}
}