package org.usfirst.frc.team20.robot;

public class DriverControls {
	DriveTrain drive;
	Controller driverJoy;
	double speedStraight = 0, speedLeft = 0, speedRight = 0;
	
	public DriverControls(DriveTrain d){
		drive = d;
		driverJoy = new Controller(0);
	}
	
	public void driverControls(){
		speedStraight = -driverJoy.getLeftYAxis();
		speedLeft = driverJoy.getLeftTriggerAxis();
		speedRight = driverJoy.getRightTriggerAxis();
		drive.drive(speedStraight, speedRight, speedLeft);
	}
}
