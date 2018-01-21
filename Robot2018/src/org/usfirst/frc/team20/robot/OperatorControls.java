package org.usfirst.frc.team20.robot;

public class OperatorControls {
	Controller operatorJoy;
	Collector collector;
	
	public OperatorControls(Collector c){
		operatorJoy = new Controller(1);
		collector = c;
	}
	
	public void operatorControls(){
		if(operatorJoy.getButtonY()){
			collector.intake();
		}
		if(operatorJoy.getButtonX() || operatorJoy.getButtonB()){
			collector.stopRollers();
		}
		if(operatorJoy.getButtonA()){
			collector.outtake();
		}
		if(operatorJoy.getLeftTriggerAxis() > 0){
			collector.open();
		} else {
			collector.close();
		}
	}
}
