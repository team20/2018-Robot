package org.usfirst.frc.team20.robot;

public class EncoderGyro {
	private double angle;
	private double radius;
	private double startingDistanceRight;
	private double startingDistanceLeft;
	Objects ob;
	public EncoderGyro(Objects o, double width){
		ob = o;
		radius = width/2;
		startingDistanceLeft = ob.driveMasterLeft.getSelectedSensorPosition(0)/Constants.TICKS_PER_INCH;
		startingDistanceRight = ob.driveMasterRight.getSelectedSensorPosition(0)/Constants.TICKS_PER_INCH;
	}
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
	public double angleToDistance(double angle){
		double radians = Math.toRadians(angle);
		return radians*radius;
	}
	public double getAngle(){
		return angle;
	}
	public double getRadius(){
		return radius;
	}
}