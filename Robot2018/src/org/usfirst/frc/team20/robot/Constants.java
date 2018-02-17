package org.usfirst.frc.team20.robot;

public class Constants {

	public static final double NOMINAL_VOLTAGE = 0.3; //Mars - 0.3
	public static final double TURN_P_LEFT = 0.020; //Mars - 0.02
	public static final double TURN_P_RIGHT = 0.020; //Mars - 0.02
	public static final double DRIVING_P = 0.010; //Mars - 0.010
	public static final double TURNING_DEADBAND = 2.0; //Mars - 2.0
	public static final double TICKS_PER_INCH = 620; //Mars - 650, 2018 - 620
	public static final double STOPPING_INCHES = 0.0;
	public static final int ELEVATOR_DEADBAND = 100;
	public static final int SPLINE_FACTOR = 6;
	
	public static final int INTAKE_POSITION = 0;
	public static final int SWITCH_POSITION = 12510;
	public static final int SCALE_LOW_POSITION = 33360;
	public static final int SCALE_MID_POSITION = 41700;
	public static final int SCALE_HIGH_POSITION = 50040;
	public static final int ELEVATOR_MAX_POSITION = 60812;
	public static final double CURRENT_LIMIT = 500000;

	public static final int PIDIDX = 0; //TODO what is this???
}