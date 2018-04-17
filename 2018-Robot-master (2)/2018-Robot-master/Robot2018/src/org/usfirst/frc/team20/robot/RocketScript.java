package org.usfirst.frc.team20.robot;

import java.util.ArrayList;

public class RocketScript {
	/*
	 * each method returns an auto mode auto in the form of an ArrayList of Strings
	 */
	
	static public ArrayList<String> austinsBullshit(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -1.0 + ";" + RobotModes.SPLINE_LEFT_CORNER);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -0.75 + ";" + RobotModes.SPLINE_LEFT_CORNER_SLOW + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.PLACE_SCALE));
		auto.add(RobotModes.WAIT + ";" + 0.25);
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.25);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.SPLINE + ";" + 0.75 + ";" + RobotModes.SPLINE_LEFT_CORNER_CUBE);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.SPLINE_AND_INTAKE + ";" + 0.4 + ";" + RobotModes.SPLINE_LEFT_CORNER_PICKUP);
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_LEFT_CUBE_TO_SCALE);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -0.5 + ";" + RobotModes.SPLINE_LEFT_NULL_TO_SCALE + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.PLACE_SCALE));
		return auto;
	}
	
	//TODO CHECK ALL PLACEING (SCALE VS SWITCH), MAKE SURE ALL SCALE HAVE SENSOR INCORPORATED
	static public ArrayList<String> cross(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.TIME_DRIVE + ";" + -0.6 + ";" + 3.0);
		return auto;
	}	

	static public ArrayList<String> splineCenterToLeftSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 0.9 + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		auto.add(RobotModes.WAIT + ";" + 0.4);
		auto.add(RobotModes.SPLINE + ";" + -1.0 + ";" + RobotModes.SPLINE_LEFT_SWITCH_BACK_UP);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.SPLINE_AND_INTAKE + ";" + 0.75 + ";" + RobotModes.SPLINE_SWITCH_TO_CUBE);
		auto.add(RobotModes.TIME_DRIVE + ";" + -0.75 + ";" + 0.8);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 1.0 + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToRightSwitch(){
		ArrayList<String> auto = new ArrayList<>();	
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 1.0 + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		auto.add(RobotModes.WAIT + ";" + 0.25);
		auto.add(RobotModes.SPLINE + ";" + -1.0 + ";" + RobotModes.SPLINE_RIGHT_SWITCH_BACK_UP);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.SPLINE_AND_INTAKE + ";" + 0.8 + ";" + RobotModes.SPLINE_SWITCH_TO_CUBE);
		auto.add(RobotModes.TIME_DRIVE + ";" + -0.75 + ";" + 1.0);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 0.8 + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}

	static public ArrayList<String> splineCenterToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.ARM_100 + "");
		auto.add(RobotModes.SPLINE + ";" + 1.0 + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SCALE);
		auto.add(RobotModes.SPLINE + ";" + 0.75 + ";" + RobotModes.SPLINE_RIGHT_SCALE_DECELERATION);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.ARM_INTAKE_POSITION + "");
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.PLACE_SCALE + "");
		return auto;
	}

	static public ArrayList<String> splineCenterToLeftScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.ARM_100 + "");
		auto.add(RobotModes.SPLINE + ";" + 1.0 + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SCALE);
		auto.add(RobotModes.SPLINE + ";" + 0.75 + ";" + RobotModes.SPLINE_LEFT_SCALE_DECELERATION);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.ARM_INTAKE_POSITION + "");
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.SLOW_SPIT + "");
		return auto;
	}

	static public ArrayList<String> splineLeftToLeftScaleSide(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.ARM_100 + "");
		auto.add(RobotModes.SPLINE + ";" + -1.0 + ";" + RobotModes.SPLINE_LEFT_TO_LEFT_SCALE_SIDE);
		auto.add(RobotModes.SPLINE + ";" + -0.85 + ";" + RobotModes.SPLINE_LEFT_SCALE_DECELERATION_BACKWARDS);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.OVER_BACK + "");
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.SLOW_SPIT + "");
		return auto;
	}

	static public ArrayList<String> splineLeftToLeftScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_LEFT_TO_LEFT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}
	
	static public ArrayList<String> splineLeftToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_LEFT_TO_RIGHT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}

	static public ArrayList<String> splineRightToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_RIGHT_TO_RIGHT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}

	static public ArrayList<String> splineRightToLeftScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_RIGHT_TO_LEFT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}
	
	static public ArrayList<String> splineLeftTwoCube(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_RIGHT_TO_RIGHT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.DROP));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.WAIT + ";" + 0.75);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.SPLINE + ";" + 0.75 + ";" + RobotModes.SPLINE_RIGHT_SCALE_TO_CUBE);
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
		auto.add(RobotModes.TIME_DRIVE + ";" + 0.5 + ";" + 1.0);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}
	
	static public ArrayList<String> splineRightTwoCube(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.85 + ";" + RobotModes.SPLINE_RIGHT_TO_RIGHT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT)); //slow spit
		auto.add(RobotModes.WAIT + ";" + 0.5); //2.0
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.WAIT + ";" + 0.75); //1.5
		auto.add(Integer.toString(RobotModes.DROP));
		auto.add(RobotModes.SPLINE_AND_INTAKE + ";" + 0.5 + ";" + RobotModes.SPLINE_RIGHT_SCALE_TO_CUBE);
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
		auto.add(RobotModes.TIME_DRIVE + ";" + 0.5 + ";" + 1.0);
		auto.add(Integer.toString(RobotModes.PLACE_SCALE));
		return auto;
	}
	static public ArrayList<String> splineRightTwoScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.85 + ";" + RobotModes.SPLINE_RIGHT_TO_RIGHT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT)); //slow spit
		auto.add(RobotModes.WAIT + ";" + 0.5); //2.0
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.WAIT + ";" + 0.75); //1.5
		auto.add(Integer.toString(RobotModes.DROP));
		auto.add(RobotModes.SPLINE_AND_INTAKE + ";" + 0.5 + ";" + RobotModes.SPLINE_RIGHT_SCALE_TO_CUBE);
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.SPLINE + ";" + -0.75 + ";" + RobotModes.SPLINE_RIGHT_SCALE_TO_SCALE);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}
	static public ArrayList<String> splineLeftTwoScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.85 + ";" + RobotModes.SPLINE_LEFT_TO_LEFT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.WAIT + ";" + 0.75);
		auto.add(Integer.toString(RobotModes.DROP));
		auto.add(RobotModes.SPLINE_AND_INTAKE + ";" + 0.85 + ";" + RobotModes.SPLINE_LEFT_SCALE_TO_CUBE);
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.TIME_DRIVE_TO_SENSOR + ";" + -0.5 + ";" + 3.0);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}
}