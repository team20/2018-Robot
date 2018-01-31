package org.usfirst.frc.team20.robot;

public class OperatorControls {
	Collector collector;
	Elevator elevator;
	Objects ob;
	boolean override;
	
	public OperatorControls(Collector c, Elevator e, Objects o){
		collector = c;
		elevator = e;
		ob = o;
		override = false;
	}
	
	public void operatorControls(){
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
			collector.open();
		}		
		if(ob.operatorJoy.getRightYAxis() < -0.5) {
			collector.close();
		}
		if(ob.operatorJoy.getLeftYAxis() > 0.1 || ob.operatorJoy.getLeftYAxis() < -0.1){
			elevator.moveSpeed(ob.operatorJoy.getLeftYAxis());
			override = true;
		} else {
			if(override == true){
				elevator.moveSpeed(0.0);
				override = false;
			}
		}
		if(ob.operatorJoy.getButtonStart() && ob.flipSwitch.get()){
			collector.arm180();
		}
		if(ob.operatorJoy.getButtonX()){
			collector.arm45();
		}
	}	
}
