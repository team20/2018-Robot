package org.usfirst.frc.team20.robot;

public class Grids {
	static final double WIDTH = 16, LENGTH = 28; //2017 (16, 28), 2018 (33.5/2, 38.5)
	static RobotGrid centerToLeftSwitch, centerToRightSwitch, centerToLeftSwitchSide, centerToRightSwitchSide,
	centerToRightScale, centerToLeftScale, backupFromRightSwitch, rightSwitchToCube, rightSwitchBackup2, rightSwitchToScale,
	backupFromLeftSwitch, leftSwitchToCube, leftSwitchBackup2, leftSwitchToScale, leftToLeftSwitch, leftToRightSwitch;
	public Grids(){
		centerToLeftSwitch = centerToLeftSwitch();
		centerToRightSwitch = centerToRightSwitch();
		centerToLeftSwitchSide = centerToLeftSwitchSide();
		centerToRightSwitchSide = centerToRightSwitchSide();
		centerToRightScale = centerToRightScale();
		centerToLeftScale = centerToLeftScale();
		backupFromRightSwitch =backupFromRightSwitch();
		rightSwitchToCube = rightSwitchToCube();
		rightSwitchBackup2 = rightSwitchBackup2();
		rightSwitchToScale = rightSwitchToScale();
		backupFromLeftSwitch = backupFromLeftSwitch();
		leftSwitchToCube = leftSwitchToCube();
		leftSwitchBackup2 = leftSwitchBackup2();
		leftSwitchToScale = leftSwitchToScale();
		leftToLeftSwitch = leftToLeftSwitch();
		leftToRightSwitch = leftToRightSwitch();
	}
	public RobotGrid getGrid(int gridPath){
		switch(gridPath){
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
			case RobotModes.SPLINE_BACKUP_FROM_RIGHT_SWITCH:
				return backupFromRightSwitch;
			case RobotModes.SPLINE_RIGHT_SWITCH_TO_CUBE:
				return rightSwitchToCube;
			case RobotModes.SPLINE_RIGHT_SWITCH_BACKUP_2:
				return rightSwitchBackup2;
			case RobotModes.SPLINE_RIGHT_SWITCH_TO_SCALE:
				return rightSwitchToScale;
			case RobotModes.SPLINE_BACKUP_FROM_LEFT_SWITCH:
				return backupFromLeftSwitch;
			case RobotModes.SPLINE_LEFT_SWITCH_TO_CUBE:
				return leftSwitchToCube;
			case RobotModes.SPLINE_LEFT_SWITCH_BACKUP_2:
				return leftSwitchBackup2;
			case RobotModes.SPLINE_LEFT_SWITCH_TO_SCALE:
				return leftSwitchToScale;
			case RobotModes.SPLINE_LEFT_TO_LEFT_SWITCH:
				return leftToLeftSwitch;
			case RobotModes.SPLINE_LEFT_TO_RIGHT_SWITCH:
				return leftToRightSwitch;
		}
		return null;
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
	
	private RobotGrid centerToRightSwitchSide(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.addPoint(60, 220, 90);
		grid.addPoint(120, 280, 0);
		grid.addPoint(168, 238.5, 90);
		return grid;
	}	
	private RobotGrid backupFromRightSwitch(){
		RobotGrid grid = new RobotGrid(168.0, 238.5, 90.0, 2);
		grid.addPoint(209.5, 259.25, 180);
		grid.addPoint(251, 280, 90);
		return grid;
	}
	private RobotGrid rightSwitchToCube(){
		RobotGrid grid = new RobotGrid(251, 280, 180, 2);
		grid.addPoint(208.75, 240, -135.0);
		return grid;
	}
	private RobotGrid rightSwitchBackup2(){
		RobotGrid grid = new RobotGrid(208.75, 240, -135.0 ,2);
		grid.addPoint(261, 281, 180);
		grid.addPoint(310, 300, 90);
		return grid;
	}
	private RobotGrid rightSwitchToScale(){
		RobotGrid grid = new RobotGrid(310, 300, 90, 2);
		grid.addLinearPoint(310, 275, -90);
		return grid;
	}

	private RobotGrid centerToLeftSwitchSide(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.addPoint(60, 105, 90, 60, 166.75);
		grid.addPoint(120, 45, 0, 60, 45);
		grid.addPoint(168, 85.25, 90, 168, 45);
		return grid;
	} 
	private RobotGrid backupFromLeftSwitch(){
		RobotGrid grid = new RobotGrid(168.0, 85.25, 90.0, 2);
		grid.addPoint(209.5, 64.5, 180);
		grid.addPoint(251, 43.75, 90);
		return grid;
	}
	private RobotGrid leftSwitchToCube(){
		RobotGrid grid = new RobotGrid(251, 43.75, 90, 2);
		grid.addPoint(208.75, 83.75, 135.0);
		return grid;
	}
	private RobotGrid leftSwitchBackup2(){
		RobotGrid grid = new RobotGrid(208.75, 83.75, -135.0, 2);
		grid.addPoint(261, 42.75, 180, 249.75, 42.75);
		grid.addPoint(310, 23.75, 90);
		return grid;
	}
	private RobotGrid leftSwitchToScale(){
		RobotGrid grid = new RobotGrid(310, 23.75, 90, 2);
		grid.addLinearPoint(310, 40, 90);
		return grid;
	}
	
	private RobotGrid leftToLeftSwitch(){
		RobotGrid grid = new RobotGrid(28.813+WIDTH, LENGTH, 0, 2);
		grid.addPoint(93, 73, 90);
		grid.addPoint(140, 107, 0);
		return grid;
	}

	private RobotGrid leftToRightSwitch(){
		RobotGrid grid = new RobotGrid(28.813+WIDTH, LENGTH, 0, 2);
		grid.addPoint(93, 127, 90);
		grid.addPoint(140, 214.75, 0);
		return grid;
	}
}