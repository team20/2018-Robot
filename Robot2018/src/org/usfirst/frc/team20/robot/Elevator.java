package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Elevator {
	Objects ob;
	private int setPosition = 0;
	private static final int TICKS_PER_INCH = 200;
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
		ob.elevatorMaster.config_kP(0, p, 1000);
		ob.elevatorMaster.config_kI(0, i, 1000);
		ob.elevatorMaster.config_kD(0, d, 1000);
		ob.elevatorMaster.config_kF(0, f, 1000);
	}
	public void setIntake(){
		setPosition = Constants.INTAKE_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	public void setExchange(){
		setPosition = Constants.EXCHANGE_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	public void setSwitch(){
		setPosition = Constants.SWITCH_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	public void setScaleLow(){
		setPosition = Constants.SCALE_LOW_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	public void setScaleMid(){
		setPosition = Constants.SCALE_MID_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	public void setScaleHigh(){
		setPosition = Constants.SCALE_HIGH_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	public void stop(){
		setPosition = ob.elevatorMaster.getSelectedSensorPosition(0);
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);		
	}
	public void upIncrement(){
		setPosition = ob.elevatorMaster.getSelectedSensorPosition(0) + 12*TICKS_PER_INCH;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	public void downIncrement(){
		setPosition = ob.elevatorMaster.getSelectedSensorPosition(0) - 12*TICKS_PER_INCH;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	public int getSetPosition(){
		return setPosition;
	}
	public void moveSpeed(double speed){
		ob.elevatorMaster.set(ControlMode.PercentOutput, speed);
	}
}
