package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Elevator {
	Zenith ob;
	private int setPosition = 0, prevPosition = 0;
	private static final int TICKS_PER_INCH = 695;

	public Elevator(Zenith o){
		ob = o;
		setPosition = ob.elevatorMaster.getSelectedSensorPosition(0);
		setPID(0.075, 0.000015, 1.1, 0.0); //dual 775 tune = p 0.05, 0.00001, 0.0, 0.0
	}
	
	/**
	 * sets the elevator to the selected position in auto
	 * @param position: the auto position the elevator should go to
	 */
	public void getAutoPosition(int position){
		switch(position){
			case RobotModes.POSITION_INTAKE:
				setIntake();
				break;
			case RobotModes.POSITION_SWITCH:
				setSwitch();
				break;
			case RobotModes.POSITION_SCALE:
				setScaleMid();
		}
	}

	/**
	 * inserts the p i d f values into the Talon SRX
	 * @param p: proportional value
	 * @param i: integral value
	 * @param d: derivative value
	 * @param f: feed forward value
	 */
	public void setPID(double p, double i, double d, double f){
		ob.elevatorMaster.config_kP(0, p, 1000);
		ob.elevatorMaster.config_kI(0, i, 1000);
		ob.elevatorMaster.config_kD(0, d, 1000);
		ob.elevatorMaster.config_kF(0, f, 1000);
	}

	/**
	 * brings the elevator to the intake position
	 */
	public void setIntake(){
		setPosition = Constants.INTAKE_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	
	/**
	 * sets the elevator to the switch position
	 */
	public void setSwitch(){
		setPosition = Constants.SWITCH_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}

	/**
	 * sets the elevator to the lowest scale position
	 */
	public void setScaleLow(){
		setPosition = Constants.SCALE_LOW_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}

	/**
	 * sets the elevator to the balanced scale position
	 */
	public void setScaleMid(){
		setPosition = Constants.SCALE_MID_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}

	/**
	 * sets the elevator to the highest scale position
	 */
	public void setScaleHigh(){
		setPosition = Constants.SCALE_HIGH_POSITION;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}

	/**
	 * sets the elevator set
	 * 
	 *  position to its current position
	 */
	public void stop(){
		setPosition = ob.elevatorMaster.getSelectedSensorPosition(0);
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);		
	}

	/**
	 * brings the elevator up six inches
	 */
	public void upIncrement(){
		setPosition = ob.elevatorMaster.getSelectedSensorPosition(0) - (int)6*TICKS_PER_INCH;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	
	/**
	 * brings the elevator down six inches
	 */
	public void downIncrement(){
		setPosition = ob.elevatorMaster.getSelectedSensorPosition(0) + (int)6*TICKS_PER_INCH;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}

	/**
	 * sets the elevator 15 inches lower
	 */
	public void flipPosition(){
		setPosition += 7.5*TICKS_PER_INCH;
		ob.updateElevatorSetpoint(setPosition);
		ob.elevatorMaster.set(ControlMode.Position, setPosition);
	}
	
	/**
	 * @return the set point of the elevator
	 */
	public int getSetPosition(){
		return setPosition;
	}
	
	/**
	 * @return true if the elevator is within deadband of its set position
	 */
	public boolean elevatorMoving(){
		if(Math.abs(ob.elevatorMaster.getSelectedSensorPosition(0) - prevPosition) > 10){
			prevPosition = ob.elevatorMaster.getSelectedSensorPosition(0);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * moves the elevator at a speed (percent output)
	 * @param speed: speed of the elevator (-1.0 to 1.0)
	 */
	public void moveSpeed(double speed){
		ob.elevatorMaster.set(ControlMode.PercentOutput, speed);
	}
	
	/**
	 * resets the elevator to the intake position,
	 * waits for the current to spike,
	 * zeros the elevator encoder
	 */
	public boolean reset(){
		ob.elevatorMaster.set(ControlMode.PercentOutput, -0.1);
		System.out.println(ob.elevatorMaster.getOutputCurrent());
		if(ob.elevatorMaster.getOutputCurrent() > 100) { //TODO tune the value for the current threshold
			ob.elevatorMaster.set(ControlMode.PercentOutput, 0.0);
			ob.elevatorMaster.setSelectedSensorPosition(0, 0, 1000);
			return true;
		} else {
			return false;
		}
	}	 
	
	/**
	 * @return true if the elevator is above the stationary stage
	 */
	public boolean aboveThreshold(){
		return ob.elevatorMaster.getSelectedSensorPosition(0) < Constants.ELEVATOR_STAGE_THRESHOLD;
	}
}