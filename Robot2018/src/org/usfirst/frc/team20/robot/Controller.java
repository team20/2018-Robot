//Author: Atharva Gawde
package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	Joystick joy;
	public Controller(int port){
		joy = new Joystick(port);
	}
	public boolean getButtonA(){
		return joy.getRawButton(1);
	}
	public boolean getButtonB(){
		return joy.getRawButton(2);
	}
	public boolean getButtonX(){
		return joy.getRawButton(3);
	}
	public boolean getButtonY(){
		return joy.getRawButton(4);
	}
	public boolean getButtonLeftBumper(){
		return joy.getRawButton(5);
	}
	public boolean getButtonRightBumper(){
		return joy.getRawButton(6);
	}
	public boolean getButtonBack(){
		return joy.getRawButton(7);
	}
	public boolean getButtonStart(){
		return joy.getRawButton(8);
	}
	public boolean getButtonDUp(){
		return joy.getPOV() == 0;
	}
	public boolean getButtonDDown(){
		return joy.getPOV() == 180;
	}
	public boolean getButtonDRight(){
		return joy.getPOV() == 90;
	}
	public boolean getButtonDLeft(){
		return joy.getPOV() == 270;
	}
	public double getLeftXAxis(){
		return joy.getRawAxis(0);
	}
	public double getLeftYAxis(){
		return joy.getRawAxis(1);
	}
	public double getLeftTriggerAxis(){
		return joy.getRawAxis(2);
	}
	public double getRightTriggerAxis(){
		return joy.getRawAxis(3);
	}
	public double getRightXAxis(){
		return joy.getRawAxis(4);
	}
	public double getRightYAxis(){
		return joy.getRawAxis(5);
	}
	public boolean getLeftAxisButton(){
		return joy.getRawButton(9);
	}
	public boolean getRightAxisButton(){
		return joy.getRawButton(10);
	}
	public void vibrate(){
		joy.setRumble(RumbleType.kLeftRumble, 1.0);	
	}
}