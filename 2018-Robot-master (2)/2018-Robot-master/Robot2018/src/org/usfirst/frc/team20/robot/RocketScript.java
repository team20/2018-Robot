package org.usfirst.frc.team20.robot;

import java.util.ArrayList;

public class RocketScript {
	/*
	 * each method returns an auto mode script in the form of an ArrayList of Strings
	 */
	//TODO make each move to intake position at beginning (all that need to at least)
	static public ArrayList<String> testElevator(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
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
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0"+ ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToRightSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0"+ ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}

	static public ArrayList<String> splineCenterToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE + ";" + "1.0" + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SCALE);
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
		auto.add(RobotModes.SPLINE + ";" + "1.0" + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SCALE);
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SCALE);
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.ARM_INTAKE_POSITION + "");
		auto.add(RobotModes.WAIT + ";" + 0.5);
		auto.add(RobotModes.SLOW_SPIT + "");
		return auto;
	}
	
	static public ArrayList<String> splineCenterToLeftSwitchToLeftScale(){ //TODO move elevator down while doing the spline
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
	
	static public ArrayList<String> splineCenterToRightSwitchToRightScale(){ //TODO move elevator down while doing the spline
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
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 1.0 + ";" + RobotModes.SPLINE_LEFT_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}

	static public ArrayList<String> splineLeftToRightSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(Integer.toString(RobotModes.WAIT_ENTERED));
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + 1.0 + ";" + RobotModes.SPLINE_LEFT_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
}
