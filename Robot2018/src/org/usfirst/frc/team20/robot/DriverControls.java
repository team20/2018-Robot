package org.usfirst.frc.team20.robot;

public class DriverControls {
	DriveTrain drive;
	Collector collector;
	Zenith ob;
	Climber climb;
	double speedStraight = 0, speedLeft = 0, speedRight = 0;
	boolean climbing = false;
	
	public DriverControls(DriveTrain d, Collector c, Zenith o){
		drive = d;
		collector = c;
		ob = o;
	}
	

	/**
	 * contains all of the controls for the driver joystick
	 */
	public void driverControls(){
		speedStraight = -ob.driverJoy.getLeftYAxis();
		speedLeft = ob.driverJoy.getLeftTriggerAxis();
		speedRight = ob.driverJoy.getRightTriggerAxis();
		drive.drive(speedStraight, speedLeft, speedRight);
		if(ob.driverJoy.getButtonY()){
			drive.shiftHigh();
		}
		if(ob.driverJoy.getButtonB()){
			drive.shiftLow();
		}
		if(ob.driverJoy.getRightYAxis() > 0.5){
			collector.outtake();
		}
		if(ob.driverJoy.getRightYAxis() > -0.5){
			collector.open();
		}
		if(ob.driverJoy.getButtonA()){
			climb.climb();
			climbing = true;
		}
	}
	/**
	 * contains all of the controls for the driver joystick
	 */
	public void driverControlsPS4(){
		speedStraight = -ob.driverJoy4.getLeftYAxis();
		speedLeft = ob.driverJoy4.getLeftTriggerAxis();
		speedRight = ob.driverJoy4.getRightTriggerAxis();
		drive.drive(speedStraight, speedLeft, speedRight);
		
		if(ob.driverJoy4.getRightYAxis() > 0.1){
			drive.shiftHigh();
		}
		if(ob.driverJoy4.getRightYAxis() < -0.1){
			drive.shiftLow();
		}
		
		if(ob.driverJoy4.getLeftBumperButton()){
			collector.open();
		}
		if(ob.driverJoy4.getRightBumperButton()){
			collector.outtake();
		}
		
		if(ob.driverJoy4.getTriButton()){
			climb.extend();
		} else {
			climb.stop();
		}
		if(ob.driverJoy4.getCircleButton()){
			climb.stop();
		}
		if(ob.driverJoy4.getXButton()){
			climb.climb();
		} else {
			climb.stop();
		}
	}
}
