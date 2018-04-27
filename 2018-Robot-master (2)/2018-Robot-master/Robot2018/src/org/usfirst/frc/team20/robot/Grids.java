package org.usfirst.frc.team20.robot;

public class Grids {
	static final double WIDTH = 33.5/2, LENGTH = 38.5; //2018 (33.5/2, 38.5)
	static RobotGrid centerToLeftSwitch, centerToRightSwitch,
	centerToRightScale, centerToLeftScale, leftToLeftScale,
	leftScaleToCube, rightScaleToCube, rightScaleDeceleration, leftScaleDeceleration, rightToRightScale,
	leftToRightScale, rightToLeftScale, rightScaleToScale, leftScaleToScale, leftScaleSide, leftScaleDecelerationBackwards,
	rightSwitchBackUp, leftSwitchBackUp, switchToCube, leftToLeftCorner,leftToLeftCornerSlow,leftCornerCube,
	leftCubePickup,leftCubeToScale, leftNullToScale, switchBackupCube, testS, leftCubeToCornerScale,leftCornerToCubeFast,
	leftCubePickupFast,leftCubeToScaleFast, leftScaleToThirdCube, leftScaleThirdCubePickup, leftThirdCubeToScale,
	frontOfSwitch, frontOfSwitchSlow;
	
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
		switchBackupCube = switchBackupCube();
		leftToLeftCorner = leftToLeftCorner();
		leftToLeftCornerSlow = leftCornerDecelerate();
		leftCornerCube = leftCornerToCube();
		leftCubePickup = leftCornerPickUp();
		leftCubeToScale = leftCubeToScale();
		leftNullToScale = leftNullToScale();
		testS = testS();
		leftCubeToCornerScale = leftCubeToCornerScale();
		leftCornerToCubeFast = leftCornerToCubeFast();
		leftCubePickupFast = leftCubePickupFast();
		leftCubeToScaleFast = leftCubeToScaleFast();
		leftScaleToThirdCube = leftScaleToThirdCube();
		leftScaleThirdCubePickup = leftScaleThirdCubePickup();
		leftThirdCubeToScale = leftThirdCubeToScale();
		frontOfSwitch = frontOfSwitch();
		frontOfSwitchSlow = frontOfSwitchSlow();
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
			case RobotModes.SPLINE_LEFT_CORNER:
				return leftToLeftCorner;
			case RobotModes.SPLINE_LEFT_CORNER_SLOW:
				return leftToLeftCornerSlow;
			case RobotModes.SPLINE_LEFT_CORNER_CUBE:
				return leftCornerCube;
			case RobotModes.SPLINE_LEFT_CORNER_PICKUP:
				return leftCubePickup;
			case RobotModes.SPLINE_LEFT_CUBE_TO_SCALE:
				return leftCubeToScale;
			case RobotModes.SPLINE_LEFT_NULL_TO_SCALE:
				return leftNullToScale;
			case RobotModes.SPLINE_SWITCH_CUBE_BACKUP:
				return switchBackupCube;
			case RobotModes.TEST_S:
				return testS;
			case RobotModes.SPLINE_LEFT_CUBE_TO_CORNER_SCALE:
				return leftCubeToCornerScale;
			case RobotModes.SPLINE_LEFT_CORNER_TO_CUBE_FAST:
				return leftCornerToCubeFast;
			case RobotModes.SPLINE_LEFT_CUBE_PICKUP_FAST:
				return leftCubePickupFast;
			case RobotModes.SPLINE_LEFT_CUBE_TO_SCALE_FAST:
				return leftCubeToScaleFast;
			case RobotModes.SPLINE_LEFT_TO_THIRD_CUBE:
				return leftScaleToThirdCube;
			case RobotModes.SPLINE_LEFT_THIRD_CUBE_PICKUP:
				return leftScaleThirdCubePickup;
			case RobotModes.SPLINE_LEFT_THIRD_CUBE_TO_SCALE:
				return leftThirdCubeToScale;
			case RobotModes.SPLINE_FRONT_OF_SWITCH:
				return frontOfSwitch;
			case RobotModes.SPLINE_FRONT_OF_SWITCH_SLOW:
				return frontOfSwitchSlow;
		}
		return null;
	}

	private RobotGrid centerToLeftSwitch(){
		RobotGrid grid = new RobotGrid(0, 150+WIDTH, 0, 2);
		grid.addPoint(35, 134.5, 90);
		grid.addPoint(130-LENGTH, 91+WIDTH, 0);
		return grid;
	}
	private RobotGrid leftSwitchBackUp(){
		RobotGrid grid = new RobotGrid(140-LENGTH, 91+WIDTH, 180, 2);
		grid.addPoint(55.0, 125.25, 90);
		grid.addPoint(30.0, 180.0, 180);
		return grid;
	}
	private RobotGrid switchToCube(){
		RobotGrid grid = new RobotGrid(30.0, 160.0, 0, 2);
		grid.addLinearPoint(98.0, 160.0, 0);
		return grid;
	}
	private RobotGrid switchBackupCube(){
		RobotGrid grid = new RobotGrid(98.0, 160.0, 180, 2);
		grid.addPoint(45.0, 160.0, 0);
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
		grid.addPoint(65.0, 194.25, 90);
		grid.addPoint(30.0, 160.0, 180);
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
	private RobotGrid leftToRightScale(){
		RobotGrid grid = new RobotGrid(-LENGTH, -28.813-WIDTH, 180, 2);
		grid.addPoint(-260, -64, 90);
		grid.addLinearPoint(-260, -220, 90);
		grid.addPoint(-300, -265, 180);
		return grid;
	}
	private RobotGrid rightScaleToCube(){
		RobotGrid grid = new RobotGrid(-300.0, -265.0, 0.0, 2);
		grid.addLinearPoint(-225, -265, 0.0);
		return grid;
	}
	private RobotGrid rightScaleToScale(){
		RobotGrid grid = new RobotGrid(-225, -265, 180, 2);
		grid.addLinearPoint(-250, -265, 180);
		return grid;
	}
	private RobotGrid rightToLeftScale(){
		RobotGrid grid = new RobotGrid(-LENGTH, -296+WIDTH, 180, 2);
		grid.addPoint(-255, -273, 90);
		grid.addLinearPoint(-255, -64, 90);
		grid.addPoint(-285, -90, 180);
		return grid;
	}
	private RobotGrid leftToLeftCorner(){
		RobotGrid grid = new RobotGrid(-LENGTH,-(30+WIDTH), 180, 2);
		grid.addLinearPoint(-185, -(30+WIDTH), 180);
		return grid;
	}
	private RobotGrid  leftCornerDecelerate(){
		RobotGrid grid = new RobotGrid(-185, -(30+WIDTH), 180, 2);
		grid.addPoint(-248, -70, 30);
		return grid;
	}
	private RobotGrid leftCornerToCube(){
		RobotGrid grid = new RobotGrid(-265, -60.5, 30, 2);
		grid.sCurve(-(162 + WIDTH), -60, -239.493, -40, -(162+WIDTH), -40);
		return grid;
	}
	private RobotGrid leftCornerPickUp(){
		RobotGrid grid =  new RobotGrid (-(162 + WIDTH), -60, -90, 2);
		grid.addLinearPoint(-(162 + WIDTH), -100, -90);
		return grid;
	}
	private RobotGrid leftCubeToScale(){
		RobotGrid grid = new RobotGrid (-211, -53.25, -90, 2);
		grid.addPoint(-270, -10, 180);
		grid.addPoint(-325, -20, 90);
		return grid;
	}
	private RobotGrid leftNullToScale(){
		RobotGrid grid = new RobotGrid(-320, -20, -90, 2);
		grid.addLinearPoint(-320, -50, -90);
		return grid;
	}
	private RobotGrid testS(){
		RobotGrid grid = new RobotGrid(LENGTH, 150+WIDTH, 0, 2);
		grid.sCurve(130, 85+WIDTH, 70, 150, 70, 85+WIDTH);
   	 //1, 2 = x, y ending point   3,4  = 1st reference point   5, 6 = 2nd reference point
		return grid;
	}
	private RobotGrid leftCubeToCornerScale(){
		RobotGrid grid =new RobotGrid(-(196+WIDTH),-100,90,2);
		grid.sCurve(-300,-85,-(195+WIDTH),-60,-256.699,-60);
		return grid;
	}	
	private RobotGrid leftCornerToCubeFast(){
		RobotGrid grid = new RobotGrid(-275,-60.5,30,2);
		grid.addPoint(-212.464, -90, -30, -269.78, -57.486);
		return grid;
	}
	private RobotGrid leftCubePickupFast(){
		RobotGrid grid = new RobotGrid(0,0,-30,0);
		grid.addRelativePoint(7, -30);
		return grid;
	}
	private RobotGrid leftCubeToScaleFast(){
		RobotGrid grid = new RobotGrid (-218,-61,150,2);
		grid.addPoint(-270, -75, -150,-231.876,-52.989);
		return grid;
	}
	private RobotGrid leftScaleToThirdCube(){
		RobotGrid grid = new RobotGrid(-275, -60.5, 0, 2);
		grid.addPoint(-(196+WIDTH), -110, -90);
		return grid;
	}
	private RobotGrid leftScaleThirdCubePickup(){
		RobotGrid grid = new RobotGrid(-(196+WIDTH), -110, -90, 2);
		grid.addLinearPoint(-(196+WIDTH), -115, -90);
		return grid;
	}
	private RobotGrid leftThirdCubeToScale(){
		RobotGrid grid = new RobotGrid(-(196+WIDTH), -115, -90, 2);
		grid.addPoint(-270, -85, 0);
		return grid;
	}
	private RobotGrid frontOfSwitch(){
		RobotGrid grid = new RobotGrid(-LENGTH,-(30+WIDTH),180,2);
		grid.sCurve(-140, -280, -45, -(30+WIDTH), -45, -280);
		return grid;
	}
	private RobotGrid frontOfSwitchSlow(){
		RobotGrid grid = new RobotGrid(-140, -280, 180, 2);
		grid.addPoint(-(300+WIDTH), -254, -90);
		return grid;
	}
}