package org.usfirst.frc.team20.robot;

import java.util.ArrayList;

public class RocketScript {
	/*
	 * each method returns an auto mode script in the form of an ArrayList of Strings
	 */
	
	// Dumb Autos	
	static public ArrayList<String> rightSwitch(){
		ArrayList<String> auto = new ArrayList<>();
//		auto.add(RobotModes.TIME_TURN + ";" + 0.5 + ";" + 0.5 + ";" + false);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
		auto.add(RobotModes.TIME_DRIVE + ";" + 0.5 + ";" + 3.0);
		auto.add(RobotModes.PLACE + "");
		return auto;
	}
	
	static public ArrayList<String> leftSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.TIME_TURN + ";" + 0.6 + ";" + .5 + ";" + true);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
		auto.add(RobotModes.TIME_DRIVE + ";" + 0.5 + ";" + 3.0);
		auto.add(RobotModes.PLACE + "");
		return auto;
	}

	static public ArrayList<String> cross(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.TIME_DRIVE + ";" + -0.6 + ";" + 3.0);
		return auto;
	}	
	
	// Smart Autos
	static public ArrayList<String> testSwitchLeftBackwards(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + -0.75 + ";" + RobotModes.SPLINE_TEST_BACKWARDS);
		return auto;
	}

	static public ArrayList<String> crossAutoLine(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.ENCODER_DRIVE + ";" + 0.5 + ";" + 120 + ";" + 0.0);
		return auto;
	}	

	static public ArrayList<String> splineCenterToLeftSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "0.75"+ ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToRightSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "0.75"+ ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
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
		auto.add(RobotModes.PLACE + "");
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
	static public ArrayList<String> splineCenterTwoCubeLeftV2(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + -1.0 + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SCALE_BACKWARDS);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 1.0 + ";" + RobotModes.SPLINE_LEFT_SCALE_TO_CUBE + ";" + RobotModes.POSITION_INTAKE);
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	static public ArrayList<String> splineCenterTwoCubeRightV2(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + -1.0 + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SCALE_BACKWARDS);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.OVER_BACK));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(Integer.toString(RobotModes.SLOW_SPIT));
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 1.0 + ";" + RobotModes.SPLINE_RIGHT_SCALE_TO_CUBE + ";" + RobotModes.POSITION_INTAKE);
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToLeftSwitchToLeftScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0"+ ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH_SIDE + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "-0.6" + ";" + RobotModes.SPLINE_BACKUP_FROM_LEFT_SWITCH + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.SPLINE + ";" + "1.0" + ";" + RobotModes.SPLINE_LEFT_SWITCH_TO_CUBE);		
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.SPLINE + ";" + "-0.75" + ";" + RobotModes.SPLINE_LEFT_SWITCH_BACKUP_2);		
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0" + ";" + RobotModes.SPLINE_LEFT_SWITCH_TO_SCALE + ";" + RobotModes.POSITION_SCALE);		
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToRightSwitchToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0"+ ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH_SIDE + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		auto.add(RobotModes.SPLINE + ";" + "-0.6" + ";" + RobotModes.SPLINE_BACKUP_FROM_RIGHT_SWITCH);
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0" + ";" + RobotModes.SPLINE_RIGHT_SWITCH_TO_CUBE + ";" + RobotModes.POSITION_INTAKE);		
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.SPLINE + ";" + "-0.75" + ";" + RobotModes.SPLINE_RIGHT_SWITCH_BACKUP_2);		
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0" + ";" + RobotModes.SPLINE_RIGHT_SWITCH_TO_SCALE + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.OVER_BACK + "");
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
	static public ArrayList<String> splineLeftToLeftSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 1.0 + ";" + RobotModes.SPLINE_LEFT_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}

	static public ArrayList<String> splineLeftToRightSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.ARM_INTAKE_POSITION));
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 1.0 + ";" + RobotModes.SPLINE_LEFT_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
}
