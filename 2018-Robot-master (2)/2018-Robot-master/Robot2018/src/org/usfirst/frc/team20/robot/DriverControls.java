package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.Timer;

public class DriverControls {
	DriveTrain drive;
	Collector collector;
	Zenith ob;
	Climber climb;
	double speedStraight = 0, speedLeft = 0, speedRight = 0, climbSpeed = 0, speedRightSlow = 0, speedLeftSlow = 0, startTime = 0;
	boolean climbing = false, leds = true, prevCube = false, timerStarted = false, collectorBeenRun = false;
	
	public DriverControls(DriveTrain d, Collector c, Zenith o, Climber cl){
		drive = d;
		collector = c;
		ob = o;
		climb = cl;
	}
	
	/**
	 * contains all of the controls for the driver joystick
	 */
	public void driverControlsPS4(){
		if(Math.abs(ob.driverJoy4.getLeftYAxis()) > 0.1){
			speedStraight = -ob.driverJoy4.getLeftYAxis();			
		} else {
			speedStraight = 0.0;
		}
		if(ob.elevatorMaster.getSelectedSensorPosition(0) < Constants.ELEVATOR_FAST_DRIVING_MAX){
			ob.driveMasterLeft.configOpenloopRamp(0.4, 1000);
			ob.driveMasterRight.configOpenloopRamp(0.4, 1000);
			System.out.println("low ramp rate");
		} else {
			ob.driveMasterLeft.configOpenloopRamp(0.1, 1000);
			ob.driveMasterRight.configOpenloopRamp(0.1, 1000);	
		}
		if(ob.elevatorMaster.getSelectedSensorPosition(0) < Constants.ELEVATOR_STAGE_THRESHOLD){
			if (ob.driverJoy4.getSquareButton()) {
				speedLeft = ob.driverJoy4.getLeftTriggerAxis()*0.25;
				speedRight = ob.driverJoy4.getRightTriggerAxis()*0.25;	
			} else {
				speedLeft = ob.driverJoy4.getLeftTriggerAxis()*0.5;
				speedRight = ob.driverJoy4.getRightTriggerAxis()*0.5;
			}
		} else {
			if (ob.driverJoy4.getSquareButton()) {
				speedLeft = ob.driverJoy4.getLeftTriggerAxis()*0.33;
				speedRight = ob.driverJoy4.getRightTriggerAxis()*0.33;
			} else {
				speedLeft = ob.driverJoy4.getLeftTriggerAxis()*0.65;
				speedRight = ob.driverJoy4.getRightTriggerAxis()*0.65;
			}
		}
		drive.drive(speedStraight, speedRight, speedLeft);
		if(ob.driverJoy4.getTriButton()){
			drive.shiftHigh();
		}
		if(ob.driverJoy4.getCircleButton()){
			drive.shiftLow();
		}
		if(ob.driverJoy4.getLeftBumperButton()){
			collector.open();
		} // This will allow slow spit with square and right bump, and normal with just right bump
		if(ob.driverJoy4.getRightBumperButton() && ob.driverJoy4.getSquareButton()){
			collector.outtakeSlow();
			collectorBeenRun = true;
		} else if (ob.driverJoy4.getRightBumperButton()){
			collector.outtake();
			collectorBeenRun = true;
		} else if (collectorBeenRun){
			collector.stopRollers();
			collectorBeenRun = false;
		}
		if(ob.driverJoy4.getRightYAxis() > 0.1){
			climbSpeed = ob.driverJoy4.getRightYAxis();
		} else {
			climbSpeed = 0.0;
		}
		climb.climb(climbSpeed);
		if(ob.driverJoy4.getOptionsButton()){
			leds = false;
		}
		if(ob.cube && ob.cube != prevCube){
			prevCube = ob.cube;
			startTime = Timer.getFPGATimestamp();
			ob.driverJoy4.setRumble(1.0);
			timerStarted = true;
		}
		if(timerStarted){
			if(Math.abs(Timer.getFPGATimestamp() - startTime) > 1.0){
				ob.driverJoy4.setRumble(0.0);
				timerStarted = false;
			}
		}
	}
}