package org.usfirst.frc.team20.robot;

public class DriverControls {
	DriveTrain drive;
	Collector collector;
	Zenith ob;
	Climber climb;
	double speedStraight = 0, speedLeft = 0, speedRight = 0, climbSpeed = 0;
	boolean climbing = false;
	boolean leds = true;
	
	public DriverControls(DriveTrain d, Collector c, Zenith o, Climber cl){
		drive = d;
		collector = c;
		ob = o;
		climb = cl;
	}
	

	/**
	 * contains all of the controls for the driver joystick
	 */
	public void driverControlsPS4(){ //TODO Alex, make climber speed variable on joystick???
		if(Math.abs(ob.driverJoy4.getLeftYAxis()) > 0.1){
			speedStraight = -ob.driverJoy4.getLeftYAxis();			
		} else {
			speedStraight = 0.0;
		}
		speedLeft = ob.driverJoy4.getLeftTriggerAxis();
		speedRight = ob.driverJoy4.getRightTriggerAxis();
		drive.drive(speedStraight, speedRight, speedLeft);
		
		if(ob.driverJoy4.getTriButton()){
			drive.shiftHigh();
		}
		if(ob.driverJoy4.getCircleButton()){
			drive.shiftLow();
		}
		if(ob.driverJoy4.getLeftBumperButton()){
			collector.open();
		}
		if(ob.driverJoy4.getRightBumperButton()){
			collector.outtake();
		}
		if(ob.driverJoy4.getXButton()){
			climb.stop();
		}
		if(Math.abs(ob.driverJoy4.getRightYAxis()) > 0.1){
			climbSpeed = -ob.driverJoy4.getRightYAxis();
		} else {
			climbSpeed = 0.0;
		}
		climb.climb(climbSpeed);
		if(ob.driverJoy4.getOptionsButton()){
			leds = false;
		}
		if(ob.cube){
			ob.driverJoy4.setRumble(100, 100);
		} else {
			ob.driverJoy4.setRumble(0.0, 0.0);
		}
	}
}