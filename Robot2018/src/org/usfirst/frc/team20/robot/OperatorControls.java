package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.Timer;

public class OperatorControls {
	Collector collector;
	Elevator elevator;
	Arduino arduino;
	Zenith ob;
	boolean override, flipOver, timeStarted;
	double startTime;
	
	public OperatorControls(Collector c, Elevator e, Arduino a, Zenith o){
		collector = c;
		elevator = e;
		ob = o;
		arduino = a;
		override = false;
		flipOver = false;
		timeStarted = false;
		startTime = 0;
	}
	
	/**
	 * runs all of the controls of the operator
	 */
	public void operatorControlsPS4(){
		System.out.println("Position: " + ob.elevatorMaster.getSelectedSensorPosition(0));
		System.out.println("                                      Set Position: " + elevator.getSetPosition());
		//automated
		if(ob.operatorJoy4.getRightYAxis() < 0.1 && ob.operatorJoy4.getRightYAxis() > -0.1){
			try{
				arduino.getSensorData();
				if(arduino.getIRSensor()){
					if(!timeStarted){
						startTime = Timer.getFPGATimestamp();
						collector.intake(1.0);
					}
					collector.close();
					ob.operatorJoy4.setRumble(1.0, 1.0);
					ob.updateCube(true);
				} else {
					collector.open();
					ob.updateCube(false);
				}			
			} catch (Exception e){
				
			} finally {
//				System.out.println("Arudino Sensor Not Working");
			}
		}
		if((Timer.getFPGATimestamp() - startTime) > 1.0){ //time to wait until closes
			collector.intake(0.0);
			timeStarted = false;
		}
		//controlled
		if(ob.operatorJoy4.getXButton()){
			collector.intake(0.5);
			ob.operatorJoy4.setRumble(1.0, 1.0);
		}
		if(ob.operatorJoy4.getCircleButton()){
			collector.stopRollers();
			ob.operatorJoy4.setRumble(0.0, 0.0);
		}
		if(ob.operatorJoy4.getTriButton()){
			collector.outtake();
		}
		if(ob.operatorJoy4.getButtonDUp()){
			elevator.setScaleHigh();
		}
		if(ob.operatorJoy4.getButtonDLeft()){
			elevator.setScaleMid();
		}
		if(ob.operatorJoy4.getButtonDDown()) {
			elevator.setScaleLow();
		}
		if(ob.operatorJoy4.getButtonDRight()) {
			elevator.setSwitch();
		}
		if(ob.operatorJoy4.getRightYAxis() > 0.5) {
			collector.arm45();
		}		
		if(ob.operatorJoy4.getLeftBumperButton()) {
			elevator.setIntake();
		}
		if(Math.abs(ob.operatorJoy4.getLeftYAxis()) > 0.1 && ob.operatorJoy4.getLeftStickButton()){
			System.out.println("Overriding");
			elevator.moveSpeed(ob.operatorJoy4.getLeftYAxis());
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
		if(flipOver && ob.flipSwitch.get()){
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
			collector.arm100();
		}
		if(ob.operatorJoy4.getOptionsButton()){
			elevator.stop();
			collector.stopRollers();
		}
	}	
}