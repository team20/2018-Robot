package org.usfirst.frc.team20.robot;

public class EncoderGyro {
	private double angle;
	private double radius;
	private double startingDistanceRight;
	private double startingDistanceLeft;
	Zenith ob;
	
	public EncoderGyro(Zenith o, double width){
		ob = o;
		radius = width/2;
		startingDistanceLeft = ob.driveMasterLeft.getSelectedSensorPosition(0)/Constants.TICKS_PER_INCH;
		startingDistanceRight = ob.driveMasterRight.getSelectedSensorPosition(0)/Constants.TICKS_PER_INCH;
	}
	
	/**
	 * calculates the angle of the robot
	 * @param leftDistance: encoder count on the left side
	 * @param rightDistance: encoder count on the right side
	 * @return returns the current angle of the robot
	 */
	public double updateAngle(double leftDistance, double rightDistance){
		double wheelArc = ((leftDistance/Constants.TICKS_PER_INCH - startingDistanceLeft) - (rightDistance/Constants.TICKS_PER_INCH - startingDistanceRight)) / 2;
		double radians = wheelArc/radius;
		angle = Math.toDegrees(radians);
		angle %= 360;
		if (angle > 180)
			angle = (angle%180)-180;
		if (angle < -180)
			angle = (angle%180)+180;
		return angle;
	}
	
	/**
	 * calculates distance traveled by the robot
	 * @param angle: the current angle of the robot
	 * @return distance traveled 
	 */
	public double angleToDistance(double angle){
		double radians = Math.toRadians(angle);
		return radians*radius;
	}

	/**
	 * @return angle of the robot
	 */
	public double getAngle(){
		return angle;
	}
	
	/**
	 * @return radius of the robot
	 */
	public double getRadius(){
		return radius;
	}
}