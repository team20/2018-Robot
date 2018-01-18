package org.usfirst.frc.team20.robot;

public class Grids {
	static final double WIDTH = 33.5/2, LENGTH = 38.5; //2017 (16, 28)
	static RobotGrid test, centerToLeftSwitch, centerToRightSwitch, centerToLeftSwitchSide, centerToRightSwitchSide,
	centerToRightScale, centerToLeftScale;
	public Grids(){
		test = test();
		centerToLeftSwitch = centerToLeftSwitch();
		centerToRightSwitch = centerToRightSwitch();
		centerToLeftSwitchSide = centerToLeftSwitchSide();
		centerToRightSwitchSide = centerToRightSwitchSide();
		centerToRightScale = centerToRightScale();
		centerToLeftScale = centerToLeftScale();
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
			case RobotModes.SPLINE_CENTER_TO_RIGHT_SCALE:
				return centerToRightScale;
			case RobotModes.SPLINE_CENTER_TO_LEFT_SCALE:
				return centerToLeftScale;
		}
		return null;
	}
	
	private RobotGrid test(){
		RobotGrid grid = new RobotGrid(28, 277.549, 0, 1);
		grid.addPoint(124.870, 204.725, 300);
		return grid;
	}
	
	private RobotGrid centerToLeftSwitch(){
		RobotGrid grid = new RobotGrid(0, 150+WIDTH, 0, 2);
		grid.addPoint(50.75, 134.5, 90, 50.75, 166.75);
		grid.addPoint(140-LENGTH, 85.5+WIDTH, 0, 50.75, 102.25);
		return grid;
	}

	private RobotGrid centerToRightSwitch(){
		RobotGrid grid = new RobotGrid(0, 150+WIDTH, 0, 2);
		grid.addPoint(50.75, 194.25, 90);
		grid.addPoint(140-LENGTH, 238.5-WIDTH, 0);
		return grid;
	}

	private RobotGrid centerToLeftSwitchSide(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.addPoint(60, 105, 90, 60, 166.75);
		grid.addPoint(120, 45, 0, 60, 45);
		grid.addPoint(168, 85.25, 90, 168, 45);
		return grid;
	} 

	private RobotGrid centerToRightSwitchSide(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.addPoint(60, 220, 90);
		grid.addPoint(120, 280, 0);
		grid.addPoint(168, 238.5, 90);
		return grid;
	}	
	private RobotGrid centerToLeftScale(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.addPoint(60, 105, 90, 60, 166.75);
		grid.addPoint(120, 45, 0, 60, 45);
		grid.addPoint(300+WIDTH, 71.5, 90);
		return grid;
	} 
	private RobotGrid centerToRightScale(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.addPoint(60,  220, 90);
		grid.addPoint(120,  280,  0);
		grid.addPoint(300+WIDTH, 254, 90);
		return grid;
	}
}