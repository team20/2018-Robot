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
		if (driverJoy.getRightTriggerAxis() > 0.1) {
			speedStraight = driverJoy.getRightTriggerAxis()*0.75;
		} else if (driverJoy.getLeftTriggerAxis() > 0.1) {
			speedStraight = -driverJoy.getLeftTriggerAxis()*0.75;
		} else {
			speedStraight = 0.0;
		}
		if (driverJoy.getLeftXAxis() < -0.1) {
			speedLeft = -driverJoy.getLeftXAxis()*0.75;
		} else {
			speedLeft = 0.0;
		}
		if (driverJoy.getLeftXAxis() > 0.1) {
			speedRight = driverJoy.getLeftXAxis()*0.75;
		} else {
			speedRight = 0.0;
		}
		if (speedStraight > 0 || speedStraight < 0 || speedLeft > 0 || speedRight > 0) {
			drive.drive(speedStraight, speedRight, speedLeft);
		} else {
			drive.stopDrive();
		}

	}
}
