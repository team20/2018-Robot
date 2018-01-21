package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Elevator {
	Objects ob;
	public Elevator(Objects o){
		ob = o;
	}
	public void setPosition(int position){
		ob.elevator.set(ControlMode.Position, position);
	}
}
