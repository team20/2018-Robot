package org.usfirst.frc.team20.robot;

import java.util.ArrayList;

public class RocketScript {
	/*
	 * each method returns an auto mode auto in the form of an ArrayList of Strings
	 */
	static public ArrayList<String> testS(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + 1.0 + ";" + RobotModes.TEST_S);
		return auto;
	}

	static public ArrayList<String> cross(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.TIME_DRIVE + ";" + -0.6 + ";" + 2.0);
		return auto;
	}	

	static public ArrayList<String> splineCenterToLeftSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 0.75 + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToRightSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 0.75 + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}

	static public ArrayList<String> splineCenterToLeftSwitchTwo(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 0.75 + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -1.0 + ";" + RobotModes.SPLINE_LEFT_SWITCH_BACK_UP + ";" + RobotModes.POSITION_INTAKE);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.SPLINE_AND_INTAKE_STOP + ";" + 0.7 + ";" + RobotModes.SPLINE_SWITCH_TO_CUBE);
		auto.add(Integer.toString(RobotModes.INCREMENT));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -0.6 + ";" + RobotModes.SPLINE_SWITCH_CUBE_BACKUP + ";" + RobotModes.POSITION_SWITCH);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + .9 + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToRightSwitchTwo(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 0.75 + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -1.0 + ";" + RobotModes.SPLINE_RIGHT_SWITCH_BACK_UP + ";" + RobotModes.POSITION_INTAKE);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.SPLINE_AND_INTAKE_STOP + ";" + 0.7 + ";" + RobotModes.SPLINE_SWITCH_TO_CUBE);
		auto.add(Integer.toString(RobotModes.INCREMENT));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -0.6 + ";" + RobotModes.SPLINE_SWITCH_CUBE_BACKUP + ";" + RobotModes.POSITION_SWITCH);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + .9 + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		return auto;
	}

	static public ArrayList<String> splineCenterToLeftScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.ARM_100 + "");
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE + ";" + 1.0 + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SCALE);
		auto.add(RobotModes.SPLINE + ";" + 0.75 + ";" + RobotModes.SPLINE_LEFT_SCALE_DECELERATION);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.ARM_INTAKE_POSITION + "");
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.SLOW_SPIT_SCALE + "");
		return auto;
	}

	static public ArrayList<String> splineCenterToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.ARM_100 + "");
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE + ";" + 1.0 + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SCALE);
		auto.add(RobotModes.SPLINE + ";" + 0.75 + ";" + RobotModes.SPLINE_RIGHT_SCALE_DECELERATION);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.ARM_INTAKE_POSITION + "");
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.PLACE_SCALE + "");
		return auto;
	}

	static public ArrayList<String> splineLeftToLeftScaleSide(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.ARM_100 + "");
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE + ";" + -1.0 + ";" + RobotModes.SPLINE_LEFT_TO_LEFT_SCALE_SIDE);
		auto.add(RobotModes.SPLINE + ";" + -0.85 + ";" + RobotModes.SPLINE_LEFT_SCALE_DECELERATION_BACKWARDS);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.OVER_BACK + "");
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.PLACE_SCALE + "");
		return auto;
	}

	static public ArrayList<String> splineLeftToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_LEFT_TO_RIGHT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT_SCALE));
		return auto;
	}

	static public ArrayList<String> splineLeftToRightScaleTwoCube(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.8 + ";" + RobotModes.SPLINE_LEFT_TO_RIGHT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.PLACE_SCALE));
		auto.add(RobotModes.WAIT + ";" + 0.4);
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.1);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.SPLINE_AND_INTAKE_STOP + ";" + 0.5 + ";" + RobotModes.SPLINE_RIGHT_SCALE_TO_CUBE);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -0.65 + ";" + RobotModes.SPLINE_RIGHT_SCALE_TO_SCALE + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.WAIT + ";" + 0.3);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.PLACE_SCALE));
		return auto;
	}

	static public ArrayList<String> splineRightToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_RIGHT_TO_RIGHT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT_SCALE));
		return auto;
	}

	static public ArrayList<String> splineRightToLeftScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_RIGHT_TO_LEFT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT_SCALE));
		return auto;
	}
	
	static public ArrayList<String> splineLeftTwoCube(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_LEFT_TO_LEFT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT_SCALE));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.WAIT + ";" + 0.75);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.SPLINE + ";" + 0.75 + ";" + RobotModes.SPLINE_LEFT_SCALE_TO_CUBE);
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
		auto.add(RobotModes.TIME_DRIVE + ";" + 0.5 + ";" + 1.0);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT_SCALE));
		return auto;
	}
	
	static public ArrayList<String> splineLeftTwoScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -1.0 + ";" + RobotModes.SPLINE_LEFT_CORNER);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -0.75 + ";" + RobotModes.SPLINE_LEFT_CORNER_SLOW + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT_SCALE));
		auto.add(RobotModes.WAIT + ";" + 0.25);
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.25);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.SPLINE + ";" + 0.9 + ";" + RobotModes.SPLINE_LEFT_CORNER_CUBE);
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.SPLINE_AND_INTAKE + ";" + 0.5 + ";" + RobotModes.SPLINE_LEFT_CORNER_PICKUP);
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_LEFT_CUBE_TO_SCALE);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -0.5 + ";" + RobotModes.SPLINE_LEFT_NULL_TO_SCALE + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + .5);
		auto.add(Integer.toString(RobotModes.PLACE_SCALE));
		auto.add(RobotModes.WAIT + ";" + .5);
		auto.add(RobotModes.ARM_INTAKE_POSITION + "");
		return auto;
	}
	static public ArrayList<String> splineLeftThreeScale(){
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
		auto.add(Integer.toString(RobotModes.OPEN));
		auto.add(RobotModes.SPLINE + ";" + 0.75 + ";" + RobotModes.SPLINE_LEFT_CORNER_TO_CUBE_FAST);
		auto.add(RobotModes.SPLINE_AND_INTAKE_STOP + ";" + 0.5 + ";" + RobotModes.SPLINE_LEFT_CUBE_PICKUP_FAST);
		auto.add(Integer.toString(RobotModes.ARM_100));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -.8 + ";" + RobotModes.SPLINE_LEFT_CUBE_TO_SCALE_FAST + ";" + RobotModes.POSITION_SWITCH);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.PLACE_SCALE + "");
		auto.add(RobotModes.ARM_INTAKE_POSITION + "");
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 0.75 + ";" + RobotModes.SPLINE_LEFT_TO_THIRD_CUBE + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.SPLINE_AND_INTAKE_STOP + ";" + 0.5 + ";" + RobotModes.SPLINE_LEFT_THIRD_CUBE_PICKUP);
		auto.add(RobotModes.ARM_100 + "");
		auto.add(RobotModes.SPLINE + ";" + -0.8 + ";" + RobotModes.SPLINE_LEFT_THIRD_CUBE_TO_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.OVER_BACK + "");
		auto.add(RobotModes.OVER_BACK + "");
		auto.add(RobotModes.PLACE_SCALE + "");
		return auto;
	}
	public static ArrayList<String> leftToRightScaleCrossover(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.ARM_100 + "");
		auto.add(RobotModes.WAIT_ENTERED + "");
		auto.add(RobotModes.SPLINE + ";" + -0.9 + ";" + RobotModes.SPLINE_FRONT_OF_SWITCH);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + -0.75 + ";" + RobotModes.SPLINE_FRONT_OF_SWITCH_SLOW);
		auto.add(RobotModes.OVER_BACK + "");
		auto.add(RobotModes.WAIT + ";" + 0.3);
		auto.add(RobotModes.PLACE_SCALE + "");
		return auto;
	}
}