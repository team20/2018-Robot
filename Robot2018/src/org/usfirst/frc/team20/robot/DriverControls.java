package org.usfirst.frc.team20.robot;

public class DriverControls {
	DriveTrain drive;
	Objects ob;
	double speedStraight = 0, speedLeft = 0, speedRight = 0;
	
	public DriverControls(DriveTrain d, Objects o){
		drive = d;
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
	}
}
