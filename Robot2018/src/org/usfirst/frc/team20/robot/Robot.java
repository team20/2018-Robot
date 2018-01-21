/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DriverStation;
import java.util.ArrayList;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	Collector collector;
	Elevator elevator;

	//Controllers
	DriverControls driverJoy;
	OperatorControls operatorJoy;
	
	//Autonomous Variables
	AHRS gyro = new AHRS(SerialPort.Port.kMXP); // DO NOT PUT IN ROBOT INIT
	ArrayList<String> script = new ArrayList<>();
	EncoderGyro gy;
	Grids grid;
	int rocketScriptCurrentCount = 0, rocketScriptSize = 0, startingENCClicks = 0,
			autoModeSubStep = 0, startingENCClicksLeft = 0, startingENCClicksRight = 0;
	double rotateToAngleRate, currentRotationRate, startTime, waitTime;
	double nominalVoltage = Constants.NOMINAL_VOLTAGE;
	boolean resetGyro = false, setStartTime = false, waitStartTime = false, gotStartingENCClicks = false, resetGyroTurn = false, done = false,
			gyroReset = false, elevatorDone = false, driveDone = false, splineDone = false, elevatorSet = false;

	@Override
	public void robotInit() {		
		ob = new Objects();
		drive = new DriveTrain(ob);
		collector = new Collector(ob);
		elevator = new Elevator(ob);
		
		driverJoy = new DriverControls(drive);
		operatorJoy = new OperatorControls(collector);
		
		grid = new Grids();
		gy = new EncoderGyro(ob, 31.3125);
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
		System.out.println("RAN AUTO INIT");
		//Reset all variables for the start of auto
		gyro.reset();
		autoModeSubStep = 0; startingENCClicksLeft = 0; startingENCClicksRight = 0;
		resetGyro = false; setStartTime = false; waitStartTime = false; gotStartingENCClicks = false; resetGyroTurn = false; done = false;
		gyroReset = false; elevatorDone = false; driveDone = false; splineDone = false; elevatorSet = false;

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
//		boolean button1 = SmartDashboard.getBoolean("DB/Button 0", false);
//		boolean button2 = SmartDashboard.getBoolean("DB/Button 1", false);
//		double position = SmartDashboard.getNumber("DB/Slider 0", 0.0);
		waitTime = 2*SmartDashboard.getNumber("DB/Slider 1", 0.0);
//		if(position == 0){
//			if(switchLeft){
//				
//			} else {
//				
//			}
//		} else if (position == 2.5){
//			if(button2 == false){
//				if(button1 == false){
//					
//				} else {
//					
//				}
//			} else {
//				if(button1 == false){
//						
//				} else {
//					
//				}
//			}
//		} else if (position == 5){
//			
//		}
		script.addAll(RocketScript.splineLeftToLeftSwitch());
		rocketScriptSize = script.size();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		//Scripting
		if(!gyroReset){
			gyro.reset();
			gyroReset = true;
		}
		if (rocketScriptCurrentCount < rocketScriptSize) {
			String[] values = script.get(rocketScriptCurrentCount).split(";");
			//Spline
			if (Integer.parseInt(values[0]) == RobotModes.SPLINE_AND_ELEVATOR) {
				if(!splineDone){
					if(spline(Double.parseDouble(values[1]), grid.getGrid(Integer.parseInt(values[2])))){
						System.out.println("*******SPLINE IS DONE************" + Timer.getMatchTime());
						splineDone = true;
					}					
				}
//				if(!elevatorDone){
//					if(!elevatorSet){
//						elevator.setPosition(ob.elevator.getSelectedSensorPosition(0) + Integer.parseInt(values[3]));
//						elevatorSet = true;
						elevatorDone = true; //TODO remove once we have an encoder
//					}
//					if(ob.elevator.getSelectedSensorPosition(0) > Integer.parseInt(values[3])-Constants.ELEVATOR_DEADBAND 
//							&& ob.elevator.getSelectedSensorPosition(0) < Integer.parseInt(values[3])+Constants.ELEVATOR_DEADBAND){
//						elevatorDone = true;
//						elevatorSet = false;
//					}
//					System.out.println("RUNNING ELEVATOR");
//				}
				if(splineDone && elevatorDone){
					ob.elevator.set(ControlMode.PercentOutput, 0.0);
					rocketScriptCurrentCount++;
					splineDone = false;
					elevatorDone = false;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.SPLINE) {
				if(spline(Double.parseDouble(values[1]), grid.getGrid(Integer.parseInt(values[2])))){
					System.out.println("*******SPLINE IS DONE************" + Timer.getMatchTime());
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.DRIVE_AND_ELEVATOR) {
				//TODO make this elevator code
				if(!elevatorDone){
					//TODO add elevator code
					System.out.println("RUNNING ELEVATOR");
				}
				if(!driveDone){
					System.out.println("******************Driving");
					if (encoderDrive(Double.parseDouble(values[2]),
							Double.parseDouble(values[3]) - Constants.STOPPING_INCHES, Double.parseDouble(values[4]))) {
						gotStartingENCClicks = false;
						gyro.reset();
						driveDone = true;
					}					
				}
				if(elevatorDone && driveDone){
					rocketScriptCurrentCount++;
				}
			}
			//Driving using encoders
			if (Integer.parseInt(values[0]) == RobotModes.ENCODER_DRIVE) {
				if (encoderDrive(Double.parseDouble(values[1]),
						Double.parseDouble(values[2]) - Constants.STOPPING_INCHES, Double.parseDouble(values[3]))) {
					gotStartingENCClicks = false;
					gyro.reset();
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.MOVE_ELEVATOR){
				if(!elevatorSet){
					elevator.setPosition(ob.elevator.getSelectedSensorPosition(0) + Integer.parseInt(values[1]));
					elevatorSet = true;
				}
				if(ob.elevator.getSelectedSensorPosition(0) > Integer.parseInt(values[1])-Constants.ELEVATOR_DEADBAND 
						&& ob.elevator.getSelectedSensorPosition(0) < Integer.parseInt(values[1])+Constants.ELEVATOR_DEADBAND){
					rocketScriptCurrentCount++;
					elevatorSet = false;
				}
			}
			//Turn to an angle
			if (Integer.parseInt(values[0]) == RobotModes.TURN) {
				System.out.println("******************************** " + gyro.getYaw());
				if (turn(Double.parseDouble(values[1]))) {
					setStartTime = false;
					resetGyroTurn = false;
					rocketScriptCurrentCount++;
				}
			}
			//wait for the time period on the dashboard
			if (Integer.parseInt(values[0]) == RobotModes.WAIT_ENTERED) {
				System.out.println("WAITING");
				if (!waitStartTime) {
					startTime = Timer.getFPGATimestamp();
					waitStartTime = true;
				}
				if (Timer.getFPGATimestamp() - startTime > waitTime) {
					System.out.println("******************************DONE WAITING");
					waitStartTime = false;
					rocketScriptCurrentCount++;
				}
			}
			//Wait for a time period
			if (Integer.parseInt(values[0]) == RobotModes.WAIT) {
				System.out.println("WAITING");
				if (!waitStartTime) {
					startTime = Timer.getFPGATimestamp();
					waitStartTime = true;
				}
				if (Timer.getFPGATimestamp() - startTime > Double.parseDouble(values[1])) {
					System.out.println("******************************DONE WAITING");
					waitStartTime = false;
					rocketScriptCurrentCount++;
				}
			}
			//Drive for a time period
			if (Integer.parseInt(values[0]) == RobotModes.TIME_DRIVE) {
				if (timeDrive(Double.parseDouble(values[1]), Double.parseDouble(values[2]), true)) {
					rocketScriptCurrentCount++;
				}
			}
			//Shift to low gear
			if (Integer.parseInt(values[0]) == RobotModes.LOW_GEAR) {
				drive.shiftLow();
				rocketScriptCurrentCount++;
			}
			//Shift to high gear
			if (Integer.parseInt(values[0]) == RobotModes.HIGH_GEAR) {
				drive.shiftHigh();
				rocketScriptCurrentCount++;
			}
			if(Integer.parseInt(values[0]) == RobotModes.PLACE){
				if (!waitStartTime) {
					startTime = Timer.getFPGATimestamp();
					waitStartTime = true;
					collector.outtake();
				}
				if (Timer.getFPGATimestamp() - startTime > 0.25) {
					collector.open();
					collector.stopRollers();
					waitStartTime = false;
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.INTAKE){
				if (!waitStartTime) {
					startTime = Timer.getFPGATimestamp();
					waitStartTime = true;
					collector.intake();
					collector.close();
				}
				if (Timer.getFPGATimestamp() - startTime > 0.5) {
					collector.stopRollers();
					waitStartTime = false;
					rocketScriptCurrentCount++;
				}
			}
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
	public void testInit(){
		System.out.println("*******************************Test Init Ran*************************");
		gyro.reset();
	}
	@Override
	public void testPeriodic() {
//		System.out.println("NavX Angle: " + gyro.getYaw());
//		System.out.println("Encoder Position Left: " + ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX));	
//		System.out.println("Encoder Position Right: " + ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX));	
//		System.out.println("Encoder Gyro Angle: " + gy.updateAngle(ob.driveMasterLeft.getSelectedSensorPosition(0),
//				ob.driveMasterRight.getSelectedSensorPosition(0)));
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


	public boolean timeDrive(double speed, double howMuchTime, boolean withGyro) {
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

	public boolean encoderDrive(double speed, double inches, double angleToDrive) {
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
			startingENCClicksLeft = ob.driveMasterLeft.getSelectedSensorPosition(0);
			startingENCClicksRight = ob.driveMasterRight.getSelectedSensorPosition(0);
		}
		System.out.println("****************Left: " + ob.driveMasterLeft.getSelectedSensorPosition(0));
		System.out.println("****************Right: " + ob.driveMasterRight.getSelectedSensorPosition(0));
		double robotDistance = Math.abs((((ob.driveMasterLeft.getSelectedSensorPosition(0) - startingENCClicksLeft) + (ob.driveMasterRight.getSelectedSensorPosition(0) - startingENCClicksRight))/Constants.TICKS_PER_INCH)/2);
		System.out.println("****************RobotDistance: " + robotDistance);
		System.out.println("****************Travel Distance: " + spline.getDistance());
		if(speed > 0){
			speed *= Math.abs(spline.speedMultiplier(robotDistance, gyro.getYaw()));			
		}
		if (spline.getDistance() <= robotDistance) {
			ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.00);
			ob.driveMasterRight.set(ControlMode.PercentOutput, 0.00);
			System.out.println("Final NavX Angle: " + gyro.getYaw());
			System.out.println("Enc value after speed 0 " + ob.driveMasterLeft.getSelectedSensorPosition(0));
			//System.out.println(spline.toString());
			gotStartingENCClicks = false;
			return true;
		} else {
			double angleToDrive;
			if (speed > 0)
				angleToDrive = (spline.getAngle(robotDistance));
			else
				angleToDrive = (spline.getReverseAngle(robotDistance));
			if (spline.getDistance() > 0) {
				if (spline.getDistance() > robotDistance);
				{
				System.out.println("speed = " + speed);
				System.out.println("angle = " + gyro.getYaw());
				System.out.println("Target Angle = " + spline.getAngle(robotDistance));				
					if(angleToDrive < -90 && gyro.getYaw() > 90){
						double temp = -180 - angleToDrive;
						temp += -(180 - gyro.getYaw());
						arcadeDrive(speed, temp /360*Constants.SPLINE_FACTOR);
					} else if (angleToDrive > 90 && gyro.getYaw() < -90){
						double temp = 180 - angleToDrive;
						temp += (180 + gyro.getYaw());
						arcadeDrive(speed, temp /360*Constants.SPLINE_FACTOR);					
					} else {
						arcadeDrive(speed, ((gyro.getYaw() - angleToDrive) /360*Constants.SPLINE_FACTOR));
					}
				}
			} else {
				arcadeDrive(-speed, ((gyro.getYaw() - angleToDrive) /360*Constants.SPLINE_FACTOR));
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



