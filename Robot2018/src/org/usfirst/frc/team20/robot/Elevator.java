package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Elevator {
	Objects ob;
	private int setPosition = 0;
	public Elevator(Objects o){
		ob = o;
	}
	public void getAutoPosition(int position){
		switch(position){
			case RobotModes.POSITION_INTAKE:
				setIntake();
				break;
			case RobotModes.POSITION_SWITCH:
				setSwitch();
				break;
			case RobotModes.POSITION_SCALE:
				setScaleHigh();
		}
	}
	public void setPID(double p, double i, double d, double f){
		ob.driveMasterLeft.config_kP(0, p, 1000);
		ob.driveMasterLeft.config_kI(0, i, 1000);
		ob.driveMasterLeft.config_kD(0, d, 1000);
		ob.driveMasterLeft.config_kF(0, f, 1000);
	}
	public void setIntake(){
		setPosition = Constants.INTAKE_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.driveMasterLeft.set(ControlMode.Position, setPosition);
	}
	public void setExchange(){
		setPosition = Constants.EXCHANGE_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.driveMasterLeft.set(ControlMode.Position, setPosition);
	}
	public void setSwitch(){
		setPosition = Constants.SWITCH_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.driveMasterLeft.set(ControlMode.Position, setPosition);
	}
	public void setScaleLow(){
		setPosition = Constants.SCALE_LOW_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.driveMasterLeft.set(ControlMode.Position, setPosition);
	}
	public void setScaleMid(){
		setPosition = Constants.SCALE_MID_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.driveMasterLeft.set(ControlMode.Position, setPosition);
	}
	public void setScaleHigh(){
		setPosition = Constants.SCALE_HIGH_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.driveMasterLeft.set(ControlMode.Position, setPosition);
	}
	public void stop(){
		setPosition = ob.driveMasterLeft.getSelectedSensorPosition(0);
		ob.updateElevatorSetpoint(setPosition);
		ob.driveMasterLeft.set(ControlMode.Position, setPosition);		
	}
	public int getSetPosition(){
		return setPosition;
	}
	public void moveSpeed(double speed){
		ob.driveMasterLeft.set(ControlMode.PercentOutput, speed);
	}
}
