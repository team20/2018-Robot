package org.usfirst.frc.team20.robot;

public class DriverControls {
	DriveTrain drive;
	Collector collector;
	Objects ob;
	double speedStraight = 0, speedLeft = 0, speedRight = 0;
	
	public DriverControls(DriveTrain d, Collector c, Objects o){
		drive = d;
		collector = c;
		ob = o;
	}
	
	public void driverControls(){
		speedStraight = -ob.driverJoy.getLeftYAxis();
		speedLeft = ob.driverJoy.getLeftTriggerAxis();
		speedRight = ob.driverJoy.getRightTriggerAxis();
		drive.drive(speedStraight, speedRight, speedLeft);
		if(ob.driverJoy.getButtonY()){
			drive.shiftHigh();
		}
		if(ob.driverJoy.getButtonB()){
			drive.shiftLow();
		}
		if(ob.driverJoy.getLeftYAxis() > 0.5){
			collector.outtake();
		}
		if(ob.driverJoy.getLeftYAxis() > -0.5){
			collector.open();
		}
	}
}
