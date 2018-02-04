package org.usfirst.frc.team20.robot;

public class OperatorControls {
	Collector collector;
	Elevator elevator;
	Arduino arduino;
	Objects ob;
	boolean override;
	
	public OperatorControls(Collector c, Elevator e, Arduino a, Objects o){
		collector = c;
		elevator = e;
		ob = o;
		arduino = a;
		override = false;
	}
	
	public void operatorControls(){
		//automated
		if(ob.operatorJoy.getRightYAxis() < 0.1 && ob.operatorJoy.getRightYAxis() > -0.1){
			try{
				arduino.getSensorData();
				if(arduino.getIRSensor()){
					collector.close();
				} else {
					collector.open();
				}			
			} catch (Exception e){
				
			} finally {
				System.out.println("Arudino Sensor Not Working");
			}
		}
		//controlled
		if(ob.operatorJoy.getButtonY()){
			collector.intake();
		}
		if(ob.operatorJoy.getButtonB()){
			collector.stopRollers();
		}
		if(ob.operatorJoy.getButtonA()){
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
		if((ob.operatorJoy.getLeftYAxis() > 0.1 || ob.operatorJoy.getLeftYAxis() < -0.1) && ob.operatorJoy.getLeftAxisButton()){
			elevator.moveSpeed(ob.operatorJoy.getLeftYAxis()/2);
			override = true;
		} else {
			if(override == true){
				elevator.moveSpeed(0.0);
				override = false;
			}
		}
		if(ob.operatorJoy.getLeftYAxis() > 0.5){
			elevator.upIncrement();
		}
		if(ob.operatorJoy.getLeftYAxis() < -0.5){
			elevator.downIncrement();
		}
		if(ob.operatorJoy.getButtonStart() && ob.flipSwitch.get()){
			collector.arm180();
		}
		if(ob.operatorJoy.getLeftTriggerAxis() > 0.1){
			collector.open();
		}
		if(ob.operatorJoy.getRightTriggerAxis() > 0.1){
			collector.close();
		}
	}	
}
