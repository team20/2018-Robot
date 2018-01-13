package org.usfirst.frc.team20.robot;

public class Grids {
	static RobotGrid test, centerToLeftSwitch, centerToRightSwitch;
	public Grids(){
		test = test();
		centerToLeftSwitch = centerToLeftSwitch();
		centerToLeftSwitch = centerToRightSwitch();
	}
	public RobotGrid getGrid(int grid){
		switch(grid){
			case RobotModes.SPLINE_TEST: 
				return test;
			case RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH:
				return centerToLeftSwitch;
			case RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH:
				return centerToRightSwitch;
		}
		return null;
	}
	
	private RobotGrid test(){
		RobotGrid grid = new RobotGrid(28, 277.549, 0, 1);
		grid.addPoint(124.870, 204.725, 300);
		return grid;
	}
	
	private RobotGrid centerToLeftSwitch(){
		RobotGrid grid = new RobotGrid(0, 0, 0, 1);
		
		return grid;
	}

	private RobotGrid centerToRightSwitch(){
		RobotGrid grid = new RobotGrid(0, 0, 0, 1);
		
		return grid;
	}
}
