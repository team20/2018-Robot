package org.usfirst.frc.team20.robot;

public class Grids {
	static RobotGrid test, centerToLeftSwitch, centerToRightSwitch, centerToLeftSwitchSide, centerToRightSwitchSide;
	public Grids(){
		test = test();
		centerToLeftSwitch = centerToLeftSwitch();
		centerToRightSwitch = centerToRightSwitch();
		centerToLeftSwitchSide = centerToLeftSwitchSide();
		centerToRightSwitchSide = centerToRightSwitchSide();
	}
	public RobotGrid getGrid(int grid){
		switch(grid){
			case RobotModes.SPLINE_TEST: 
				return test;
			case RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH:
				return centerToLeftSwitch;
			case RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH:
				return centerToRightSwitch;
			case RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH_SIDE:
				return centerToLeftSwitchSide;
			case RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH_SIDE:
				return centerToRightSwitchSide;
		}
		return null;
	}
	
	private RobotGrid test(){
		RobotGrid grid = new RobotGrid(28, 277.549, 0, 1);
		grid.addPoint(124.870, 204.725, 300);
		return grid;
	}
	
	private RobotGrid centerToLeftSwitch(){
		RobotGrid grid = new RobotGrid(27.5, 0, 0, 2);
		grid.addPoint(67, -60, 90,67,0);
		grid.addPoint(120, -114.06, 0,67,-114.06);
		grid.addPoint(205, -77.6, 90, 205,-114.06);
		return grid;
	}

	private RobotGrid centerToRightSwitch(){
		RobotGrid grid = new RobotGrid(27.5, 0, 0, 2);
		grid.addPoint(67, 60, 90);
		grid.addPoint(120, 114.06, 0);
		grid.addPoint(205, 77.6, 90);
		return grid;
	}

	private RobotGrid centerToLeftSwitchSide(){
		RobotGrid grid = new RobotGrid(27.5, 0, 0, 2);
		grid.addPoint(67, -60, 90,67,0);
		grid.addPoint(120, -114.06, 0,67,-114.06);
		grid.addPoint(205, -77.6, 90, 205,-114.06);
		return grid;
	}

	private RobotGrid centerToRightSwitchSide(){
		RobotGrid grid = new RobotGrid(27.5, 0, 0, 2);
		grid.addPoint(67, 60, 90);
		grid.addPoint(120, 114.06, 0);
		grid.addPoint(205, 77.6, 90);
		return grid;
	}

	
	
	
}