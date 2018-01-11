/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;
///import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	//Classes
	Objects ob;
	DriveTrain drive;

	//Controllers
	DriverControls driverJoy;
	OperatorControls operatorJoy;
	
	//Autonomous Variables
	AHRS gyro = new AHRS(SerialPort.Port.kMXP); // DO NOT PUT IN ROBOT INIT
	int startingENCClicks, autoModeSubStep = 0, startingENCClicksLeft = 0, startingENCClicksRight = 0;
	double rotateToAngleRate, currentRotationRate, startTime;
	double nominalVoltage = Constants.NOMINAL_VOLTAGE;
	boolean resetGyro = false, setStartTime = false, waitStartTime = false, gotStartingENCClicks = false, resetGyroTurn = false, done = false;

	@Override
	public void robotInit() {
		ob = new Objects();
		drive = new DriveTrain(ob);
		driverJoy = new DriverControls(drive);
		operatorJoy = new OperatorControls();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		//Reset all variables for the start of auto
		autoModeSubStep = 0; startingENCClicksLeft = 0; startingENCClicksRight = 0;
		resetGyro = false; setStartTime = false; waitStartTime = false; gotStartingENCClicks = false; resetGyroTurn = false; done = false;

		//Switch and Scale are on the left or right?
//		String field = DriverStation.getInstance().getGameSpecificMessage();
//		boolean switchLeft = false, scaleLeft = false;
//		if(field.charAt(0) == 'L'){
//			switchLeft = true;
//		}
//		if(field.charAt(1) == 'L'){
//			scaleLeft = true;
//		}
		//Picking Which Auto Mode

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		//For Testing Purposes (without scripting)
		if(!done){
//			if(turn(90.0)){
//				drive.stopDrive();
//				done = true;
//			}
//			if(fastDriveStraight(0.5, 25, 0)){
//				drive.stopDrive();
//				done = true;
//			}			
//			if(fastDriveStraightTimer(0.5, 3.0, false)){
//				System.out.println("!!!!!!! ALL DONE !!!!!!!");
//				drive.stopDrive();
//				done = true;
//			}			
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
 		driverJoy.driverControls();
 		operatorJoy.operatorControls();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
//		System.out.println("NavX Angle: " + gyro.getYaw());
//		System.out.println("Encoder Position Left: " + ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX));	
//		System.out.println("Encoder Position Right: " + ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX));	
	}
	
	// Auto Methods
	public boolean turn(double angleToDrive) {
		double pValue = 0.2;
		if (gyro.getYaw() < angleToDrive) {
			pValue = Constants.TURN_P_RIGHT;
		} else {
			pValue = Constants.TURN_P_LEFT;
		}
		if (!resetGyroTurn) {
			gyro.reset();
			resetGyroTurn = true;
			nominalVoltage = Constants.NOMINAL_VOLTAGE;
		}
		if (Math.abs(gyro.getYaw() - angleToDrive) < Constants.TURNING_DEADBAND) {
			System.out.println("*************************************HIT TURNING DEADBAND");
			if (!setStartTime) {
				startTime = Timer.getFPGATimestamp();
				setStartTime = true;
				nominalVoltage = Constants.NOMINAL_VOLTAGE;
			}
			if (Timer.getFPGATimestamp() - startTime > 0.5) {
				System.out.println("#######################################################HIT TUNE TIME");
				nominalVoltage = 0;
				if (Math.abs(gyro.getYaw() - angleToDrive) < Constants.TURNING_DEADBAND) {
					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&done turning");
					drive.stopDrive();
					return true;
				} else {
					setStartTime = false;
				}
			}
		}
		System.out.println("					Difference: " + (gyro.getYaw() - angleToDrive));
		System.out.println("					Raw Rotate Value: " + (-((gyro.getYaw() - angleToDrive)*pValue)));
		arcadeTurn(-((gyro.getYaw() - angleToDrive) * pValue));
		return false;
	}


	public boolean fastDriveStraightTimer(double speed, double howMuchTime, boolean withGyro) {
		if (!setStartTime) {
			startTime = Timer.getFPGATimestamp();
			setStartTime = true;
			gyro.reset();
		}
		System.out.println("Time is: " + (Timer.getFPGATimestamp() - startTime));
		if (Timer.getFPGATimestamp() - startTime < howMuchTime) {
			if (Math.abs(speed) > 0.00) {
				if (withGyro){
					arcadeDrive(speed, -(gyro.getYaw() * Constants.DRIVING_P));
					System.out.println("------------------------Arcade Drive is Over");
				} else {
					arcadeDrive(speed, 0);
					System.out.println("------------------------Arcade Drive is Over");
				}
			}
		} else {
			arcadeDrive(0.0, 0);
			setStartTime = false;
			return true;
		}
		return false;
	}

	public boolean fastDriveStraight(double speed, double inches, double angleToDrive) {
		System.out.println("Encoder Position: " + ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX));
		if (gotStartingENCClicks == false) {
			gyro.reset();
			gotStartingENCClicks = true;
			startingENCClicks = ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX);
			System.out.println("Start ENC click value = " + startingENCClicks);
		}
		if (Math.abs((double) (ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicks)) > Math
				.abs(inches * Constants.TICKS_PER_INCH)) {
			ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.00);
			ob.driveMasterRight.set(ControlMode.PercentOutput, 0.00);
			System.out.println("Final NavX Angle: " + gyro.getYaw());
			System.out.println("Enc value after speed 0 " + ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX));
			return true;
		} else {
			if (inches > 0) {
				if (Math.abs((double) (ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicks)) < Math
						.abs(inches * Constants.TICKS_PER_INCH))
					arcadeDrive(speed, -((gyro.getYaw() - angleToDrive) * Constants.DRIVING_P)); // .07
			} else {
				arcadeDrive(-speed, -((gyro.getYaw() - angleToDrive) * Constants.DRIVING_P)); // .07
			}
		}
		return false;
	}

	public boolean spline(double speed, RobotGrid spline) {
		if (gotStartingENCClicks == false) {
			gotStartingENCClicks = true;
			startingENCClicksLeft = ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX);
			startingENCClicksRight = -ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX);
		}
		if (spline.getDistance() <= (((ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksLeft)/ Constants.TICKS_PER_INCH + (-ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksRight)/ Constants.TICKS_PER_INCH))/2 || ((ob.driveMasterRight.getOutputCurrent() > 30) && ((ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksLeft)/ Constants.TICKS_PER_INCH + (-ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksRight)/ Constants.TICKS_PER_INCH)/2 > .5*spline.getDistance())) {
			ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.00);
			ob.driveMasterRight.set(ControlMode.PercentOutput, 0.00);
			System.out.println("Final NavX Angle: " + gyro.getYaw());
			System.out.println("Enc value after speed 0 " + ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX));
			//System.out.println(spline.toString());
			gotStartingENCClicks = false;
			return true;
		} else {
			double angleToDrive;
			if (spline.getDistance()-(((ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksLeft)/ Constants.TICKS_PER_INCH + (-ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksRight)/ Constants.TICKS_PER_INCH)/2) <(20*speed))
				speed *= spline.getDistance()-(((ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksLeft)/ Constants.TICKS_PER_INCH + (-ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksRight)/ Constants.TICKS_PER_INCH)/2)/(20*speed);
			if (speed > 0)
				angleToDrive = (spline.getAngle(Math.abs(((ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksLeft)/ Constants.TICKS_PER_INCH + (-ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksRight)/ Constants.TICKS_PER_INCH))/2));
			else
				angleToDrive = (spline.getReverseAngle(Math.abs(((ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksLeft)/ Constants.TICKS_PER_INCH + (-ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksRight)/ Constants.TICKS_PER_INCH))/2));
			if (spline.getDistance() > 0) {
				if (spline.getDistance() > Math.abs(((ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksLeft)/ Constants.TICKS_PER_INCH + (-ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicksRight)/ Constants.TICKS_PER_INCH))/2)
				{
				System.out.println("speed = " + speed);
				System.out.println("angle = " + gyro.getYaw());
					if(angleToDrive < -90 && gyro.getYaw() > 90){
						double temp = -180 - angleToDrive;
						temp += -(180 - gyro.getYaw());
						arcadeDrive(speed, -temp /360*8);
					} else if (angleToDrive > 90 && gyro.getYaw() < -90){
						double temp = 180 - angleToDrive;
						temp += (180 + gyro.getYaw());
						arcadeDrive(speed, -temp /360*8);					
					} else {
						arcadeDrive(speed, -((gyro.getYaw() - angleToDrive) /360*8));
					}
					//System.out.println(gyro.getYaw() - angleToDrive);
				}
			} else {
				arcadeDrive(-speed, -((gyro.getYaw() - angleToDrive) /360*8));
			}
		}
		return false;
	}

	private void arcadeDrive(double moveValue, double rotateValue) {
		 // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;
        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);
            // square the inputs (while preserving the sign) to increase fine control while permitting full power
        if (moveValue >= 0.0) {
            moveValue = (moveValue * moveValue);
        } else {
            moveValue = -(moveValue * moveValue);
        }
        if (rotateValue >= 0.0) {
            rotateValue = (rotateValue * rotateValue);
        } else {
            rotateValue = -(rotateValue * rotateValue);
        }
        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        System.out.println("Left: " + -leftMotorSpeed);
        System.out.println("		Right: " + rightMotorSpeed);
        ob.driveMasterLeft.set(ControlMode.PercentOutput, -leftMotorSpeed);
        ob.driveMasterRight.set(ControlMode.PercentOutput, rightMotorSpeed);
	}

	public double limit(double num){
		if(num > 1.0){
			num = 1.0;
		} else if(num < -1.0){
			num = -1.0;
		}
		return num;
	}
	public void arcadeTurn(double rotateValue) {
		System.out.println("Rotate Value: " + rotateValue);
		double leftMotorSpeed, rightMotorSpeed;
		if (rotateValue > 1.0) {
			rotateValue = 1.0;
		} else if (rotateValue < -1.0) {
			rotateValue = -1.0;
		}
		if (rotateValue >= 0.0) {
			rotateValue = rotateValue * rotateValue;
		} else {
			rotateValue = -(rotateValue * rotateValue);
		}
		if (rotateValue > 0.0) {
			leftMotorSpeed = 0.0;
			rightMotorSpeed = rotateValue;
		} else {
			leftMotorSpeed = -rotateValue;
			rightMotorSpeed = 0.0;
		}
		if (leftMotorSpeed > 1.0) {
			leftMotorSpeed = 1.0;
		} else if (leftMotorSpeed < -1.0) {
			leftMotorSpeed = -1.0;
		} else if (Math.abs(leftMotorSpeed) < Constants.NOMINAL_VOLTAGE) {
			if (leftMotorSpeed > 0.0) {
				leftMotorSpeed = nominalVoltage;
			} else {
				leftMotorSpeed = -nominalVoltage;
			}
		}
		if (rightMotorSpeed > 1.0) {
			rightMotorSpeed = 1.0;
		} else if (rightMotorSpeed < -1.0) {
			rightMotorSpeed = -1.0;
		} else if (Math.abs(rightMotorSpeed) < Constants.NOMINAL_VOLTAGE) {
			if (rightMotorSpeed > 0.0) {
				rightMotorSpeed = nominalVoltage;
			} else {
				rightMotorSpeed = -nominalVoltage;
			}
		}
		System.out.println("LeftMotorSpeed:  " + leftMotorSpeed);
		System.out.println("		RightMotorSpeed: " + rightMotorSpeed);
		ob.driveMasterLeft.set(ControlMode.PercentOutput, leftMotorSpeed);
		ob.driveMasterRight.set(ControlMode.PercentOutput, -rightMotorSpeed);
	}
}
