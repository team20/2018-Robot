package org.usfirst.frc.team20.robot;

public class Grids {
	static final double WIDTH = 33.5/2, LENGTH = 38.5; //2018 (33.5/2, 38.5)
	static RobotGrid centerToLeftSwitch, centerToRightSwitch,
	centerToRightScale, centerToLeftScale, leftToLeftSwitch, leftToRightSwitch, leftToLeftScale,
	leftScaleToCube, rightScaleToCube, rightScaleDeceleration, leftScaleDeceleration, rightToRightScale,
	leftToRightScale, rightToLeftScale, rightScaleToScale, leftScaleToScale, leftScaleSide, leftScaleDecelerationBackwards,
	rightSwitchBackUp, leftSwitchBackUp, switchToCube;

	public Grids(){
		centerToLeftSwitch = centerToLeftSwitch();
		centerToRightSwitch = centerToRightSwitch();
		centerToRightScale = centerToRightScale();
		centerToLeftScale = centerToLeftScale();
		leftToLeftScale = leftToLeftScale();
		leftScaleToCube = leftScaleToCube();
		rightScaleToCube = rightScaleToCube();
		rightScaleDeceleration = rightScaleDeceleration();
		leftScaleDeceleration = leftScaleDeceleration();
		rightToRightScale = rightToRightScale();
		leftToRightScale = leftToRightScale();
		rightToLeftScale = rightToLeftScale();
		rightScaleToScale = rightScaleToScale();
		leftScaleToScale = leftScaleToScale();
		leftScaleSide = leftToSideLeftScale();
		leftScaleDecelerationBackwards = leftScaleDecelerationBackwards();
		rightSwitchBackUp = rightSwitchBackUp();
		leftSwitchBackUp = leftSwitchBackUp();
		switchToCube = switchToCube();
	}
	
	/**
	 * @param gridPath: integer corresponding to the desired path
	 * @return corresponding RobotGrid
	 */
	public RobotGrid getGrid(int gridPath){
		switch(gridPath){
			case RobotModes.SPLINE_CENTER_TO_LEFT_SWITCH:
				return centerToLeftSwitch;
			case RobotModes.SPLINE_CENTER_TO_RIGHT_SWITCH:
				return centerToRightSwitch;
			case RobotModes.SPLINE_CENTER_TO_RIGHT_SCALE:
				return centerToRightScale;
			case RobotModes.SPLINE_CENTER_TO_LEFT_SCALE:
				return centerToLeftScale;
			case RobotModes.SPLINE_LEFT_TO_LEFT_SCALE:
				return leftToLeftScale;
			case RobotModes.SPLINE_LEFT_SCALE_TO_CUBE:
				return leftScaleToCube;
			case RobotModes.SPLINE_RIGHT_SCALE_TO_CUBE:
				return rightScaleToCube;
			case RobotModes.SPLINE_RIGHT_SCALE_DECELERATION:
				return rightScaleDeceleration;
			case RobotModes.SPLINE_LEFT_SCALE_DECELERATION:
				return leftScaleDeceleration;
			case RobotModes.SPLINE_RIGHT_TO_RIGHT_SCALE: 
				return rightToRightScale;
			case RobotModes.SPLINE_LEFT_TO_RIGHT_SCALE:
				return leftToRightScale;
			case RobotModes.SPLINE_RIGHT_TO_LEFT_SCALE:
				return rightToLeftScale;
			case RobotModes.SPLINE_RIGHT_SCALE_TO_SCALE:
				return rightScaleToScale;
			case RobotModes.SPLINE_LEFT_SCALE_TO_SCALE:
				return leftScaleToScale;
			case RobotModes.SPLINE_LEFT_TO_LEFT_SCALE_SIDE:
				return leftScaleSide;
			case RobotModes.SPLINE_LEFT_SCALE_DECELERATION_BACKWARDS:
				return leftScaleDecelerationBackwards;
			case RobotModes.SPLINE_RIGHT_SWITCH_BACK_UP:
				return rightSwitchBackUp;
			case RobotModes.SPLINE_LEFT_SWITCH_BACK_UP:
				return leftSwitchBackUp;
			case RobotModes.SPLINE_SWITCH_TO_CUBE:
				return switchToCube;
		}
		return null;
	}

	private RobotGrid centerToLeftSwitch(){
		RobotGrid grid = new RobotGrid(0, 150+WIDTH, 0, 2);
		grid.addPoint(35, 134.5, 90, 35, 166.75);
		grid.addPoint(130-LENGTH, 93+WIDTH, 0, 45, 95+WIDTH);
		return grid;
	}
	private RobotGrid leftSwitchBackUp(){
		RobotGrid grid = new RobotGrid(140-LENGTH, 93+WIDTH, 180, 2);
		grid.addPoint(55.0, 125.25, 90);
		grid.addPoint(30.0, 180.0, 180);
		return grid;
	}
	private RobotGrid centerToRightSwitch(){
		RobotGrid grid = new RobotGrid(0, 150+WIDTH, 0, 2);
		grid.addPoint(45.0, 194.25, 90);
		grid.addPoint(140-LENGTH, 238.5-WIDTH, 0);
		return grid;
	}
	private RobotGrid rightSwitchBackUp(){
		RobotGrid grid = new RobotGrid(140-LENGTH, 238.5-WIDTH, 180, 2);
		grid.addPoint(75.0, 194.25, 90);
		grid.addPoint(30.0, 160.0, 180);
		return grid;
	}
	private RobotGrid switchToCube(){
		RobotGrid grid = new RobotGrid(30.0, 160.0, 0, 2);
		grid.addLinearPoint(98.0, 160.0, 0);
		return grid;
	}
	private RobotGrid centerToLeftScale(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.addPoint(60, 105, 90);
		grid.addPoint(120, 40, 0);
		return grid;
	}
	private RobotGrid centerToRightScale(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.addPoint(60,  220, 90);
		grid.addPoint(120,  290,  0);
		return grid;
	}
	private RobotGrid leftScaleDeceleration(){
		RobotGrid grid = new RobotGrid(120, 40, 0, 2);
		grid.addPoint(295+WIDTH, 67, 90, 295+WIDTH, 35);
		return grid;
	}
	private RobotGrid rightScaleDeceleration(){
		RobotGrid grid = new RobotGrid(120, 290, 0, 2);
		grid.addPoint(295+WIDTH, 254, 90);
		return grid;
	}
	private RobotGrid leftToSideLeftScale(){
		RobotGrid grid = new RobotGrid(-LENGTH, -28.813-WIDTH, 180, 2);
		grid.addLinearPoint(-120, -28.813-WIDTH, 180);
		return grid;
	}
	private RobotGrid leftScaleDecelerationBackwards(){
		RobotGrid grid = new RobotGrid(-120, -28.8133-WIDTH, 180, 2);
		grid.addPoint(-305-WIDTH, -67, -90, -295-WIDTH, -35);
		return grid;
	}
	
	private RobotGrid leftToLeftScale(){
		RobotGrid grid = new RobotGrid(-LENGTH, -28.813-WIDTH, 180, 2);
		grid.addPoint(-230, -70, 60);
		grid.addPoint(-273, -100, 180, -247, -100);
		return grid;
	}
	private RobotGrid leftScaleToCube(){
		RobotGrid grid = new RobotGrid(-305, -80, 0, 2);
		grid.addPoint(-270, -92.5, -30, -291, -80);
		grid.addPoint(-240+LENGTH, -90, 0);
		return grid;
	}
	private RobotGrid leftScaleToScale(){
		RobotGrid grid = new RobotGrid(-240+LENGTH, -90, 180, 2);
		grid.addLinearPoint(-273, -90, 180);
		return grid;
	}

	private RobotGrid rightToRightScale(){
		RobotGrid grid = new RobotGrid(-LENGTH, -296+WIDTH, 180, 2);
		grid.addPoint(-250, -273, 90, -250, -296);
		grid.addPoint(-286, -235, 180);
		return grid;
	}
	private RobotGrid rightScaleToCube(){
		RobotGrid grid = new RobotGrid(-286, -235, 180, 2);
		grid.addPoint(-230, -235, 90);
		grid.addPoint(-225, -235, 180);
		return grid;
	}
	private RobotGrid rightScaleToScale(){
		RobotGrid grid = new RobotGrid(-225, -235, 180, 2);
		grid.addPoint(-230, -235, 90);
		grid.addPoint(-286, -235, 180);
		return grid;
	}
	private RobotGrid leftToRightScale(){
		RobotGrid grid = new RobotGrid(-LENGTH, -28.813-WIDTH, 180, 2);
		grid.addPoint(-263, -64, 90);
		grid.addLinearPoint(-263, -220, 90);
		grid.addPoint(-305, -270, 180);
		return grid;
	}
	private RobotGrid rightToLeftScale(){
		RobotGrid grid = new RobotGrid(-LENGTH, -296+WIDTH, 180, 2);
		grid.addPoint(-255, -273, 90);
		grid.addLinearPoint(-255, -64, 90);
		grid.addPoint(-285, -90, 180);
		return grid;
	}
}