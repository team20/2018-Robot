package org.usfirst.frc.team20.robot;

import java.util.ArrayList;

public class RocketScript {
	static public ArrayList<String> crossAutoLine(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.ENCODER_DRIVE + ";" + 1.0 + ";" + 120);
		return auto;
	}	

	static public ArrayList<String> splineCenterToLeftSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0"+ ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToRightSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0"+ ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH + ";" + RobotModes.POSITION_SWITCH);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	
	static public ArrayList<String> splineCenterToRightScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0" + ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SCALE + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}

	static public ArrayList<String> splineCenterToLeftScale(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0" + ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SCALE + ";" + RobotModes.POSITION_SCALE);
		auto.add(Integer.toString(RobotModes.PLACE));
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
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "-0.6" + ";" + RobotModes.SPLINE_BACKUP_FROM_RIGHT_SWITCH + ";" + RobotModes.POSITION_INTAKE);
		auto.add(RobotModes.SPLINE + ";" + "1.0" + ";" + RobotModes.SPLINE_RIGHT_SWITCH_TO_CUBE);		
		auto.add(Integer.toString(RobotModes.INTAKE));
		auto.add(RobotModes.SPLINE + ";" + "-0.75" + ";" + RobotModes.SPLINE_RIGHT_SWITCH_BACKUP_2);		
		auto.add(RobotModes.SPLINE_AND_ELEVATOR + ";" + "1.0" + ";" + RobotModes.SPLINE_RIGHT_SWITCH_TO_SCALE + ";" + RobotModes.POSITION_SCALE);		
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
