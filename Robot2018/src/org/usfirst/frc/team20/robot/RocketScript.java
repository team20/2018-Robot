package org.usfirst.frc.team20.robot;

import java.util.ArrayList;

public class RocketScript {
	static public ArrayList<String> flap(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.MOVE_ELEVATOR + ";" + "3.0");
		return auto;
	}	
	static public ArrayList<String> drive(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.ENCODER_DRIVE + ";" + "0.75" + ";" + "30.0" + ";" + "0.0");
		return auto;
	}
	static public ArrayList<String> flapAndDrive(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.DRIVE_AND_ELEVATOR + ";" + "8.0" + ";" + "0.75" + ";" + "30.0" + ";" + "0.0");
		return auto;
	}
	static public ArrayList<String> splineTest(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + "0.75"+ ";" + RobotModes.SPLINE_TEST);
		return auto;
	}
	static public ArrayList<String> splineCenterToLeftSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + "0.75"+ ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH);
//		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
//		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	static public ArrayList<String> splineCenterToRightSwitch(){
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + "0.75"+ ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH);
//		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
//		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	static public ArrayList<String> splineCenterToLeftSwitchToLeftScale(){ //TODO move elevator down while doing the spline
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + "0.75"+ ";" + RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH_SIDE);
//		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
//		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
	static public ArrayList<String> splineCenterToRightSwitchToRightScale(){ //TODO move elevator down while doing the spline
		ArrayList<String> auto = new ArrayList<>();
		auto.add(RobotModes.SPLINE + ";" + "0.75"+ ";" + RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH_SIDE);
//		auto.add(RobotModes.MOVE_ELEVATOR + ";" + RobotModes.POSITION_SWITCH);
//		auto.add(Integer.toString(RobotModes.PLACE));
		return auto;
	}
}
