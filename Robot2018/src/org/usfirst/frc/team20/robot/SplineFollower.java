package org.usfirst.frc.team20.robot;

import org.usfirst.frc.team20.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;

public class SplineFollower {
	private EncoderGyro change;
	private double maxVelocity;
	private boolean gotStartingPosition;
	private double startingPositionLeft;
	private double startingPositionRight;
	private Timer time;
	Objects ob;
	public SplineFollower (Objects o, EncoderGyro change, double maxVelocity){
		this.change = change;
		this.maxVelocity = maxVelocity;
		gotStartingPosition = false;
		startingPositionRight = 0;
		startingPositionLeft = 0;
		ob = o;
	}
	public boolean spline(double targetVelocity, RobotGrid spline, double robotAngle){
		if (!gotStartingPosition){
			startingPositionRight = ob.driveMasterRight.getSelectedSensorPosition(0);
			startingPositionLeft = ob.driveMasterLeft.getSelectedSensorPosition(0);
			gotStartingPosition = true;
			time = new Timer();
		}
		double robotDistance = Math.abs((((ob.driveMasterLeft.getSelectedSensorPosition(0) - startingPositionLeft) + (ob.driveMasterRight.getSelectedSensorPosition(0) - startingPositionRight))/Constants.TICKS_PER_INCH)/2);
		if(robotDistance>spline.getDistance()){
			ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.00);
			ob.driveMasterRight.set(ControlMode.PercentOutput, 0.00);
			System.out.println("Final NavX Angle: " + robotAngle);
			System.out.println("Enc value after speed 0 " +robotDistance);
			System.out.println("took " + time.get() + " seconds");
			//System.out.println(spline.toString());
			gotStartingPosition = false;
			return true;
		}
		//Acceleration and deceleration
		if (time.get() <1){
			targetVelocity *=time.get();
		}else if ((spline.getDistance()-robotDistance)<20){
			targetVelocity *= (spline.getDistance()-robotDistance)/20;
		}
		//handling for trying to go faster then robot accepts
		if(spline.getLeftIPS(robotDistance,robotAngle,targetVelocity,change) > maxVelocity){
			targetVelocity += (spline.getLeftIPS(robotDistance,robotAngle,targetVelocity,change)-maxVelocity);
		}else if(spline.getRightIPS(robotDistance,robotAngle,targetVelocity,change) > maxVelocity){
			targetVelocity += (spline.getRightIPS(robotDistance,robotAngle,targetVelocity,change)-maxVelocity);
		}else if(spline.getRightIPS(robotDistance,robotAngle,targetVelocity,change)< -maxVelocity){
			targetVelocity -= (spline.getRightIPS(robotDistance,robotAngle,targetVelocity,change)-maxVelocity);
		}else if(spline.getLeftIPS(robotDistance,robotAngle,targetVelocity,change) < -maxVelocity){
			targetVelocity -= (spline.getLeftIPS(robotDistance,robotAngle,targetVelocity,change)-maxVelocity);
		}
		System.out.println("***target velocity left is " + spline.getLeftIPS(robotDistance,robotAngle,targetVelocity,change));
		System.out.println("---current velocity left is " + tickToInches(ob.driveMasterLeft.getSelectedSensorVelocity(0)));
		System.out.println("***target velocity right is " + spline.getRightIPS(robotDistance,robotAngle,targetVelocity,change));
		System.out.println("---current velocity right is " + tickToInches(ob.driveMasterRight.getSelectedSensorVelocity(0)));
		ob.driveMasterLeft.set(ControlMode.Velocity,inToTick(spline.getLeftIPS(robotDistance,robotAngle,targetVelocity,change)));
		ob.driveMasterRight.set(ControlMode.Velocity,inToTick(spline.getRightIPS(robotDistance,robotAngle,targetVelocity,change)));
		return false;
	}
	
	private int inToTick(double in){
		return (int)(in/10*Constants.TICKS_PER_INCH);
	}
	private double tickToInches(int tick){
		return (tick*10/Constants.TICKS_PER_INCH);
	}
	
}