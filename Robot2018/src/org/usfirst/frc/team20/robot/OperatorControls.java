package org.usfirst.frc.team20.robot;

public class OperatorControls {
	Collector collector;
	Elevator elevator;
	Arduino arduino;
	Zenith ob;
	boolean override, flipOver;
	
	public OperatorControls(Collector c, Elevator e, Arduino a, Zenith o){
		collector = c;
		elevator = e;
		ob = o;
		arduino = a;
		override = false;
		flipOver = false;
	}
	
	/**
	 * runs all of the controls of the operator
	 */
	public void operatorControls(){
		System.out.println("Position: " + ob.elevatorMaster.getSelectedSensorPosition(0));
		System.out.println("                                      Set Position: " + elevator.getSetPosition());
		//automated
		if(ob.operatorJoy.getRightYAxis() < 0.1 && ob.operatorJoy.getRightYAxis() > -0.1){
			try{
				arduino.getSensorData();
				if(arduino.getIRSensor()){
					collector.intake();
					collector.close();
					ob.operatorJoy.vibrate();
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
		//controlled
		if(ob.operatorJoy.getButtonA()){
			collector.intake();
			ob.operatorJoy.vibrate();
		}
		if(ob.operatorJoy.getButtonB()){
			collector.stopRollers();
		}
		if(ob.operatorJoy.getButtonY()){
			collector.outtake();
		}
		if(ob.operatorJoy.getButtonDUp()){
			elevator.setScaleHigh();
		}
		if(ob.operatorJoy.getButtonDLeft()){
			elevator.setScaleMid();
		}
		if(ob.operatorJoy.getButtonDDown()) {
			elevator.setScaleLow();
		}
		if(ob.operatorJoy.getButtonDRight()) {
			elevator.setSwitch();
		}
		if(ob.operatorJoy.getButtonBack()) {
			elevator.setIntake();
		}
		if(ob.operatorJoy.getRightYAxis() > 0.5) {
			collector.arm45();
		}		
		if(ob.operatorJoy.getRightYAxis() < -0.5) {
			collector.armIntakePosition();
		}
		if(Math.abs(ob.operatorJoy.getLeftYAxis()) > 0.1 && ob.operatorJoy.getLeftAxisButton()){
			System.out.println("Overriding");
			elevator.moveSpeed(ob.operatorJoy.getLeftYAxis());
			override = true;
		} else {
			if(override){
				elevator.moveSpeed(0.0);
				override = false;
			}
		}
		if(ob.operatorJoy.getLeftYAxis() > 0.5 && !ob.operatorJoy.getLeftAxisButton() && !override){
			elevator.upIncrement();
		}
		if(ob.operatorJoy.getLeftYAxis() < -0.5 && !ob.operatorJoy.getLeftAxisButton() && !override){
			elevator.downIncrement();
		}
		if(ob.operatorJoy.getButtonStart()){
			elevator.flipPosition();
			collector.arm45();
			flipOver = true;
		}
		if(flipOver && ob.flipSwitch.get()){
			collector.arm180();
			flipOver = false;
		}
		if(ob.operatorJoy.getRightTriggerAxis() > 0.1){
			collector.open();
		}
		if(ob.operatorJoy.getLeftTriggerAxis() > 0.1){
			collector.close();
		}
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
					collector.intake();
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
		//controlled
		if(ob.operatorJoy4.getXButton()){
			collector.intake();
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