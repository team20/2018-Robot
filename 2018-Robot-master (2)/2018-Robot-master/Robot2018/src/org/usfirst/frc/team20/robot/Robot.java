/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team20.robot;

import java.util.ArrayList;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
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
public class Robot extends IterativeRobot implements PIDOutput{
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */  
	//Classes
	Zenith ob;
	DriveTrain drive;
	Collector collector;
	Elevator elevator;
	Climber climb;
	
	//Arduino
	Arduino arduino;
	Alliance alliance;

	//Controllers
	DriverControls driverJoy;
	OperatorControls operatorJoy;
	
	//Autonomous Variables
	PIDController headingPID;
	AHRS gyro = new AHRS(SerialPort.Port.kMXP);

//	EncoderGyro gyro;
	ArrayList<String> script = new ArrayList<>();
	Grids grid;
	DriverVision elevCam, cam1;
	int rocketScriptCurrentCount = 0, rocketScriptSize = 0, startingENCClicks = 0,
			autoModeSubStep = 0, startingENCClicksLeft = 0, startingENCClicksRight = 0;
	double rotateToAngleRate, currentRotationRate, startTime, waitTime;
	double kP = 0.0, kI = 0.0, kD = 0.0;
	double nominalVoltage = Constants.NOMINAL_VOLTAGE;
	boolean resetGyro = false, setStartTime = false, waitStartTime = false, gotStartingENCClicks = false, resetGyroTurn = false, done = false,
			gyroReset = false, elevatorDone = false, driveDone = false, splineDone = false, elevatorSet = false, autoSelected = false,
			collectorSet = false, intakeMode = false, inNullZone = false, prevValue = true;

	//Spline
	RobotGrid path;
	double startingDistance;
	
	//Blackbox
//	Logger logger;
	boolean socket = false, beenEnabled = false;
	
	@Override
	public void robotInit() {
		ob = new Zenith();
		drive = new DriveTrain(ob);
		collector = new Collector(ob);
		elevator = new Elevator(ob);
		climb = new Climber(ob);
		driverJoy = new DriverControls(drive, collector, ob, climb);
		operatorJoy = new OperatorControls(collector, elevator, ob);
		
		headingPID = new PIDController(kP, kI, kD, gyro, this);
		headingPID.setInputRange(-180, 180);
		headingPID.setContinuous();
		headingPID.setOutputRange(-1.0, 1.0);
//		gyro = new EncoderGyro(ob, 32.5);
		grid = new Grids();
		arduino = new Arduino(1, ob);
		ob.elevatorMaster.setSelectedSensorPosition(0, 0, 1000);

		try{
			elevCam = new DriverVision("Elevator Cam", 0);
			elevCam.startUSBCamera();
//			cam1 = new DriverVision("cam1", 1);
//			cam1.startUSBCamera();
		} catch(Exception e){
			
		} finally {
			System.out.println("Camera Isn't Working!!!");
		}
		
		Compressor c = new Compressor(14);
		c.setClosedLoopControl(true);

//		logger = new Logger();
//		logger.register(ob);
//		logger.startSocket();
		socket = true;
		alliance = DriverStation.getInstance().getAlliance();
		byte[] send = new byte[1];
		send[0] = 30;
		arduino.write(send);
	}

	public void disabledInit(){
		if(beenEnabled){
			arduino.lights(alliance, false, false, false, true, false);
			try {
//				logger.sendLog(path.toCode());
//				logger.closeSocket(); socket = false;
				System.out.println(" socket did the thingy -----------------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		beenEnabled = false;
		System.out.println("_____________________DISABLE INIT RAN WOOOO__________________________");
	}
	@Override
	public void autonomousInit() {
		ob.elevatorMaster.setSelectedSensorPosition(0, 0, 1000);
		//logger stuff!
		
//		if(!socket){
//			logger.startSocket(); socket = true;
//		}
		beenEnabled = true;		

		//Reset all variables for the start of auto
		gyro.reset();
		autoModeSubStep = 0; startingENCClicksLeft = 0; startingENCClicksRight = 0;
		resetGyro = false; setStartTime = false; waitStartTime = false; gotStartingENCClicks = false; resetGyroTurn = false; done = false;
		gyroReset = false; elevatorDone = false; driveDone = false; splineDone = false; elevatorSet = false;
//		collector.armIntakePosition();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		if(!ob.nullZone.get() && ob.nullZone.get() != prevValue){
			if(inNullZone){
				System.out.println("NOOOOOOPPPPPPEEEEEEEE");
				inNullZone = false;
			} else {
				System.out.println("WE'RE IN THE NULL ZONE FOLKS");
				inNullZone = true;
			}
			prevValue = ob.nullZone.get();
		}
		elevator.limitPosition();
		//Diagnostic LEDs
		if(driverJoy.leds){
			arduino.lights(alliance, ob.cube, driverJoy.climbing, ob.currentLimit(), false, elevator.elevatorMoving());
		} else {
			arduino.lights(null, false, false, false, true, false);
		}
		//Black Box
//		logger.log();
		ob.updateGyroAngle(gyro.getYaw());
		//Auto Selection
		if(!gyroReset){
			gyro.reset();
			gyroReset = true;
		}
		if(!autoSelected){
			//Switch and Scale are on the left or right?
			String field = DriverStation.getInstance().getGameSpecificMessage();
			if(field == null){
				field = "";
			}
			int retries = 100;
			while(field.length() < 2 && retries > 0){
				try{
					field = DriverStation.getInstance().getGameSpecificMessage();
					if(field == null){
						field = "";
					}
				} catch (Exception e){
					
				}
				retries --;
			}
			if(field.length() > 2){
				SmartDashboard.putString("DB/String 0", field);
				String auto = "";
				boolean scalePriority = SmartDashboard.getBoolean("DB/Button 0", false);
				boolean highOnly = SmartDashboard.getBoolean("DB/Button 1", false);
				boolean test = SmartDashboard.getBoolean("DB/Button 3", false);
				double position = SmartDashboard.getNumber("DB/Slider 0", 0.0);
				waitTime = 2*SmartDashboard.getNumber("DB/Slider 1", 0.0);
				//TVR Decision Tree
				switch(field.substring(0, 2)){
				case "LL":
					if(position == 2.5){
						if(scalePriority){
							script.addAll(RocketScript.splineCenterToLeftScale());
							auto = "centerToLeftScale";
						} else {
							script.addAll(RocketScript.splineCenterToLeftSwitch());
							auto = "centerToLeftSwitch";
						}
					} else if (position == 0){
						if(test){
							script.addAll(RocketScript.austinsBullshit());
							auto = "austin";
						}else
						if(highOnly){
							script.addAll(RocketScript.splineLeftToLeftScaleSide());
							auto = "leftToLeftScaleSide";
						} else {
							script.addAll(RocketScript.splineLeftTwoScale());
							auto = "leftTwoScale";
						}
					} else if (position == 5){
						script.addAll(RocketScript.splineRightToLeftScale());
						auto = "rightToLeftScale";
					} else {
						script.addAll(RocketScript.cross());
						auto = "cross";
					}
					break;
				case "RR":
					if(position == 2.5){
						if(scalePriority){
							script.addAll(RocketScript.splineCenterToRightScale());
							auto = "centerToRightScale";
						} else {
							script.addAll(RocketScript.splineCenterToRightSwitch());
							auto = "centerToRightSwitch";
						}
					} else if (position == 5){
						if(highOnly){
							script.addAll(RocketScript.splineRightToRightScale());
							auto = "rightToRightScale";
						} else {
							script.addAll(RocketScript.splineRightTwoCube());
							auto = "rightSwitchAndScale";
						}
					} else if (position == 0){
						script.addAll(RocketScript.splineLeftToRightScale());
						auto = "leftToRightScale";
					} else {
						script.addAll(RocketScript.cross());
						auto = "cross";
					}
					break;					
				case "LR":
					if(position == 2.5){
						if(scalePriority){
							script.addAll(RocketScript.splineCenterToRightScale());
							auto = "centerToRightScale";
						} else {
							script.addAll(RocketScript.splineCenterToLeftSwitch());
							auto = "centerToLeftSwitch";
						}
					} else if (position == 0){
						script.addAll(RocketScript.splineLeftToRightScale());
						auto = "leftToRightScale";
					} else if (position == 5){
						script.addAll(RocketScript.splineRightToRightScale());
						auto = "rightToRightScale";
					} else {
						script.addAll(RocketScript.cross());
						auto = "cross";
					}
					break;
				case "RL":
					if(position == 2.5){
						if(scalePriority){
							script.addAll(RocketScript.splineCenterToLeftScale());
							auto = "centerToLeftScale";
						} else {
							script.addAll(RocketScript.splineCenterToRightSwitch());
							auto = "centerToRightSwitch";
						}
					} else if (position == 0){
						if(highOnly){
							script.addAll(RocketScript.splineLeftToLeftScaleSide());
							auto = "leftToLeftScaleSide";
						} else {
							script.addAll(RocketScript.splineLeftTwoScale());
							auto = "leftTwoScale";
						}
					} else if (position == 5){
						script.addAll(RocketScript.splineRightToLeftScale());
						auto = "rightToLeftScale";
					} else {
						script.addAll(RocketScript.cross());
						auto = "cross";
					}
					break;
				}
				SmartDashboard.putString("DB/String 1", auto);
				rocketScriptSize = script.size();
				autoSelected = true;
			}
	}
		//Scripting
		if (rocketScriptCurrentCount < rocketScriptSize) {
			String[] values = script.get(rocketScriptCurrentCount).split(";");
			if (Integer.parseInt(values[0]) == RobotModes.SPLINE_AND_ELEVATOR) {
				if(!splineDone){
					if(spline(Double.parseDouble(values[1]), grid.getGrid(Integer.parseInt(values[2])))){
						System.out.println("*******SPLINE IS DONE************" + Timer.getMatchTime());
						splineDone = true;
					}
				}
				if(!elevatorSet){
					elevator.getAutoPosition(Integer.parseInt(values[3]));
					elevatorSet = true;
					startTime = Timer.getFPGATimestamp();
				}
				if(Timer.getFPGATimestamp() - startTime > 0.5){
					elevatorDone = true;
				}
				if(splineDone && elevatorDone){
					ob.elevatorMaster.set(ControlMode.PercentOutput, 0.0);
					splineDone = false;
					elevatorDone = false;
					elevatorSet = false;
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.ARM_INTAKE_POSITION){
				collector.armIntakePosition();
				rocketScriptCurrentCount++;
			}
			if(Integer.parseInt(values[0]) == RobotModes.DROP){
				collector.open();
				rocketScriptCurrentCount++;
			}
			if(Integer.parseInt(values[0]) == RobotModes.ARM_100){
				collector.arm100();
				rocketScriptCurrentCount++;
			}
			if(Integer.parseInt(values[0]) == RobotModes.SLOW_SPIT){
				if(inNullZone){
					collector.outtakeSlow();
					drive.stopDrive();
					rocketScriptCurrentCount++;
				} else {
					drive.drive(-0.3, 0.0, 0.0);
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.OVER_BACK){
				System.out.println("GOING OVER THE BACK YAYYYYYYYYY**************************************************");
				if (!waitStartTime) {
					startTime = Timer.getFPGATimestamp();
					waitStartTime = true;
					collector.arm180();
				}
				if (Timer.getFPGATimestamp() - startTime > 0.25) {
					waitStartTime = false;
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.SPLINE) {
				if(spline(Double.parseDouble(values[1]), grid.getGrid(Integer.parseInt(values[2])))){
					System.out.println("*******SPLINE IS DONE************" + Timer.getMatchTime());
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.SPLINE_AND_INTAKE){
				if(spline(Double.parseDouble(values[1]), grid.getGrid(Integer.parseInt(values[2])))){
					System.out.println("*******SPLINE IS DONE************" + Timer.getMatchTime());
					collector.stopRollers();
					collector.close();
					System.out.println("Rollers Stopped");
					collectorSet = false;
					setStartTime = false;
					rocketScriptCurrentCount++;
				}
				if(!collectorSet){
					collector.intake(0.5);
					collectorSet = true;
				}
				if (!ob.cubeSensor.get() && !waitStartTime) {
					startTime = Timer.getFPGATimestamp();
					collector.intake(1.0);
					collector.close();
					waitStartTime = true;
				}
				if (waitStartTime && Timer.getFPGATimestamp() - startTime > 0.3) {
					collector.stopRollers();
					drive.stopDrive();
					collectorSet = false;
					setStartTime = false;
					System.out.println("Rollers Stopped");
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.ENCODER_DRIVE) {
				if (encoderDrive(Double.parseDouble(values[1]),
						Double.parseDouble(values[2]) - Constants.STOPPING_INCHES, Double.parseDouble(values[3]))) {
					gotStartingENCClicks = false;
					gyro.reset();
					if(Boolean.parseBoolean(values[4]) == false){
						rocketScriptCurrentCount++;						
					} else {
						if(!ob.cubeSensor.get()){
							rocketScriptCurrentCount++;
						}
					}
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.MOVE_ELEVATOR){
				if(!elevatorSet){
					elevator.getAutoPosition(Integer.parseInt(values[1]));
					elevatorSet = true;
					startTime = Timer.getFPGATimestamp();
				}
//				if(!elevator.elevatorMoving()){
				if(Timer.getFPGATimestamp() - startTime > 0.5){
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^WOW ITS INCREMENTING^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					rocketScriptCurrentCount++;
					elevatorSet = false;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.TURN) {
				System.out.println("******************************** " + gyro.getYaw());
				if (turn(Double.parseDouble(values[1]))) {
					setStartTime = false;
					resetGyroTurn = false;
					rocketScriptCurrentCount++;
				}
			}
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
			if (Integer.parseInt(values[0]) == RobotModes.TIME_DRIVE) {
				if (timeDrive(Double.parseDouble(values[1]), Double.parseDouble(values[2]), false)) {
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.TIME_DRIVE_TO_SENSOR){
				if (timeDrive(Double.parseDouble(values[1]), Double.parseDouble(values[2]), true)) {
					rocketScriptCurrentCount++;
				}
				if(!ob.nullZone.get()){
					drive.stopDrive();
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.TIME_TURN){
				if (timeTurn(Double.parseDouble(values[1]), Double.parseDouble(values[2]), Boolean.parseBoolean(values[3]))){
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.LOW_GEAR) {
				drive.shiftLow();
				rocketScriptCurrentCount++;
			}
			if (Integer.parseInt(values[0]) == RobotModes.HIGH_GEAR) {
				drive.shiftHigh();
				rocketScriptCurrentCount++;
			}
			if(Integer.parseInt(values[0]) == RobotModes.PLACE_SCALE){
				System.out.println("************Placing****************");
				if(!inNullZone){
					System.out.println("************CREEPING");
					arcadeDrive(0.3, 0.0);
				}
				if (!waitStartTime && inNullZone) {
					startTime = Timer.getFPGATimestamp();
					waitStartTime = true;
					drive.stopDrive();
					collector.armIntakePosition();
					collector.outtake();
				}
				if (inNullZone && waitStartTime && Timer.getFPGATimestamp() - startTime > 0.3) {
					collector.open();
					collector.stopRollers();
					waitStartTime = false;
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.PLACE){
				collector.outtake();
				rocketScriptCurrentCount++;
			}
			if(Integer.parseInt(values[0]) == RobotModes.OPEN){
				collector.open();
				rocketScriptCurrentCount++;
			}
  			if(Integer.parseInt(values[0]) == RobotModes.INTAKE){
  				if(!collectorSet){
  	  				collector.intake(0.5);
  	  				collectorSet = true;
  				}
				if (!ob.cubeSensor.get() && !waitStartTime) {
					startTime = Timer.getFPGATimestamp();
					waitStartTime = true;
					collector.intake(1.0);
					collector.close();
				}
				if (waitStartTime && Timer.getFPGATimestamp() - startTime > 0.3) {
					collector.stopRollers();
					waitStartTime = false;
					collectorSet = false;
					rocketScriptCurrentCount++;
				}
			}
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopInit(){
		path = new RobotGrid(0,0,0,0);
		startingDistance = (((-ob.driveMasterLeft.getSelectedSensorPosition(0) - startingENCClicksLeft) + (ob.driveMasterRight.getSelectedSensorPosition(0) - startingENCClicksRight))/Constants.TICKS_PER_INCH)/2;
		drive.shiftHigh();
//		if(!socket){
//			logger.startSocket(); socket = true;
//		}
		beenEnabled = true;
	}

	@Override
	public void teleopPeriodic() {
		System.out.println("Drivetrain Current: " + ob.driveMasterRight.getOutputCurrent());
		if(driverJoy.leds){
			if(Timer.getMatchTime() < 40.0 && Timer.getMatchTime() > 0){
				arduino.lights(alliance, ob.cube, true, false, false, false);
			} else {
				arduino.lights(alliance, ob.cube, driverJoy.climbing, ob.currentLimit(), false, elevator.elevatorMoving());
			}
		} else {
			arduino.lights(null, false, false, false, true, false);
		}
//		logger.log();
		try{
			ob.updateGyroAngle(gyro.getYaw());
		} catch (Exception e){
		} finally {
		}
		System.out.println(elevator);
		driverJoy.driverControlsPS4();
		operatorJoy.operatorControlsPS4();
		if(!operatorJoy.override){
			elevator.incrementElevator();			
		}
		//elevator.adaptiveTune();
// 		double robotDistance = Math.abs(((((ob.driveMasterLeft.getSelectedSensorPosition(0) - startingENCClicksLeft) + (ob.driveMasterRight.getSelectedSensorPosition(0) - startingENCClicksRight))/Constants.TICKS_PER_INCH)/2)-startingDistance);
// 		path.addRelativePoint(robotDistance, gyro.getYaw());
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
		//Light Testing
//		boolean blue = false;
//		if(ob.driverJoy4.getLeftBumperButton()) {
//			blue = true;
//		}
//		if(blue){
//			arduino.lights(Alliance.Blue, ob.driverJoy4.getXButton(), ob.driverJoy4.getSquareButton(), ob.driverJoy4.getTriButton(), false, ob.driverJoy4.getRightYAxis() > .1);			
//		}
		//Encoder/Sensor Testing
//		System.out.println("NavX Yaw: " + gyro.getYaw());
		System.out.println("Left: " + ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX));	
		System.out.println("		Right: " + ob.driveMasterRight.getSelectedSensorPosition(Constants.PIDIDX));	
//		System.out.println("Encoder Gyro Angle: " + gy.updateAngle(ob.driveMasterLeft.getSelectedSensorPosition(0), ob.driveMasterRight.getSelectedSensorPosition(0)));
//				ob.driveMasterRight.getSelectedSensorPosition(0)));
//		System.out.println("                     Elevator: " + ob.elevatorMaster.getSelectedSensorPosition(Constants.PIDIDX));			
//		System.out.println("Raw Angle:                       " + gyro.getYaw());		
//		System.out.println("LINE SENSOR: " + ob.nullZone.get());
	}
	
	/**
	 * turns the robot to an angle
	 * @param angleToDrive: angle the robot need to turn (> 0 = right, < 0 = left)
	 * @return true if the angle is complete
	 */
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
			ob.updateGyroSetpoint(gyro.getYaw() - angleToDrive);
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
	
	/**
	 * turns to an angle using a PID loop
	 * @param tolerance: tolerance in degrees
	 * @param heading: the yaw to turn to
	 * @param speed: the (forwards) speed to move while turning
	 * @return true if the turn is done
	*/
	public boolean pidTurn(double heading, double speed) {
		headingPID.setSetpoint(heading);
		headingPID.setAbsoluteTolerance(Constants.TURNING_DEADBAND);
		headingPID.enable();
		arcadeDrive(speed, rotateToAngleRate);
		if (headingPID.onTarget()) {
			headingPID.disable();
			arcadeDrive(0, 0);
			return true;
		}
		return false;
	}
	
	/**
	 * turns to an angle using a PID loop
	 * @param tolerance: tolerance in degrees
	 * @param heading: the yaw to turn to
	 * @return true if the turn is done
	*/
	public boolean pidTurn(double heading) {	
		return pidTurn(heading, 0);
	}

	/**
	 * drives for a certain amount of time
	 * @param speed: speed the robot moves at (-1.0 to 1.0)
	 * @param howMuchTime: amount of time to drive
	 * @param withGyro: use the gyroscope to drive?
	 * @return true if the time drive is done
	 */
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
					ob.updateGyroSetpoint(gyro.getYaw());
				} else {
					arcadeDrive(speed, 0);
				}
			}
		} else {
			arcadeDrive(0.0, 0);
			setStartTime = false;
			return true;
		}
		return false;
	}
	
	/**
	 * turns the robot for a specific number of seconds
	 * @param speed: speed of the moving wheel
	 * @param time: amount of time to turn for
	 * @param left: is the robot turning left?
	 * @return: true when the robot is done turning
	 */
	public boolean timeTurn(double speed, double time, boolean left){
		if(!setStartTime){
			startTime = Timer.getFPGATimestamp();
			setStartTime = true;
		}
		if (Timer.getFPGATimestamp() - startTime < time) {
			drive.turn(speed, left);
		} else {
			arcadeDrive(0.0, 0);
			setStartTime = false;
			return true;
		}
		return false;
	}

	/**
	 * drive a certain number of inches using encoders
	 * @param speed: speed of robot travel (-1.0 to 1.0)
	 * @param inches: number of inches to drive
	 * @param angleToDrive: angle to turn (> 0 = right, < 0 = left)
	 * @return true if the encoder drive is done
	 */
	public boolean encoderDrive(double speed, double inches, double angleToDrive) {
		System.out.println("Encoder Position: " + ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX));
		if (gotStartingENCClicks == false) {
			gyro.reset();
			gotStartingENCClicks = true;
			startingENCClicks = ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX);
			System.out.println("Start ENC click value = " + startingENCClicks);
			ob.updateLeftSetpoint(inches*Constants.TICKS_PER_INCH);
			ob.updateRightSetpoint(inches*Constants.TICKS_PER_INCH);
		}
		if (Math.abs((double) (ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX) - startingENCClicks)) > Math
				.abs(inches * Constants.TICKS_PER_INCH)) {
			ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.00);
			ob.driveMasterRight.set(ControlMode.PercentOutput, 0.00);
			System.out.println("Final NavX Angle: " + gyro.getYaw());
			System.out.println("Enc value after speed 0 " + ob.driveMasterLeft.getSelectedSensorPosition(Constants.PIDIDX));
			return true;
		} else {
			ob.updateGyroSetpoint(gyro.getYaw() - angleToDrive);
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

	/**
	 * runs a spline drive (curves while driving)
	 * @param speed: speed of travel (-1.0 to 1.0)
	 * @param spline: the path to be followed
	 * @return true if the spline is completed
	 */
	public boolean spline(double speed, RobotGrid spline) { //TODO negate left encoder comp bot!!!!!
		if (gotStartingENCClicks == false) {
			gotStartingENCClicks = true;
			startingENCClicksLeft = ob.driveMasterLeft.getSelectedSensorPosition(0);
			startingENCClicksRight = -ob.driveMasterRight.getSelectedSensorPosition(0);
		}
		double robotDistance = Math.abs((((ob.driveMasterLeft.getSelectedSensorPosition(0) - startingENCClicksLeft) + (-ob.driveMasterRight.getSelectedSensorPosition(0) - startingENCClicksRight))/Constants.TICKS_PER_INCH)/2);
		speed = spline.speedMultiplier(robotDistance, gyro.getYaw(), speed);			
		System.out.println("Speed: " + speed);
		System.out.println("**************************************************RobotDistance: " + robotDistance);		
		if (spline.getDistance() <= robotDistance) {
			ob.driveMasterLeft.set(ControlMode.PercentOutput, 0.00);
			ob.driveMasterRight.set(ControlMode.PercentOutput, 0.00);
			System.out.println("Final NavX Angle: " + gyro.getYaw());
			System.out.println("Enc value after speed 0 " + ob.driveMasterLeft.getSelectedSensorPosition(0));
			gotStartingENCClicks = false;
			ob.updateLeftSetpoint(ob.driveMasterLeft.getSelectedSensorPosition(0));
			ob.updateRightSetpoint(ob.driveMasterRight.getSelectedSensorPosition(0));
			return true;
		} else {
			double angleToDrive;
			if (speed > 0)
				angleToDrive = (spline.getAngle(robotDistance));
			else
				angleToDrive = (spline.getReverseAngle(robotDistance));
			ob.updateGyroSetpoint(gyro.getYaw() - angleToDrive);
			if (spline.getDistance() > 0) {
				if (spline.getDistance() > robotDistance);
				{
				System.out.println("angle = " + gyro.getYaw());
				System.out.println("Target Angle = " + spline.getReverseAngle(robotDistance));				
				if(angleToDrive < -90 && gyro.getYaw() > 90){
					double temp = -180 - angleToDrive;
					temp += -(180 - gyro.getYaw());
					if(speed >= 0){
						arcadeDrive(speed, temp /360*Constants.SPLINE_FACTOR);			
					} else {
						arcadeDrive(speed, -temp /360*Constants.SPLINE_FACTOR);
					}
				} else if (angleToDrive > 90 && gyro.getYaw() < -90){
					double temp = 180 - angleToDrive;
					temp += (180 + gyro.getYaw());
					if(speed >= 0){
						arcadeDrive(speed, temp /360*Constants.SPLINE_FACTOR);						
					} else {
						arcadeDrive(speed, -temp /360*Constants.SPLINE_FACTOR);
					}
				} else {
					if(speed >= 0){
						arcadeDrive(speed, ((gyro.getYaw() - angleToDrive) /360*Constants.SPLINE_FACTOR));
					} else {
						arcadeDrive(speed, ((gyro.getYaw() - angleToDrive) /360*Constants.SPLINE_FACTOR));
					}
				}
			}
		} else {
			arcadeDrive(-speed, ((gyro.getYaw() - angleToDrive) /360*Constants.SPLINE_FACTOR));
		}
	}
	return false;
	}

	/**
	 * sets the robot to move
	 * @param moveValue: forward speed (-1.0 to 1.0)
	 * @param rotateValue: speed of rotation (based off of gyroscope heading)
	 */
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
        ob.updateLeftSide(-leftMotorSpeed);
        ob.updateRightSide(rightMotorSpeed);
	}
	private void arcadeDrive(double move, double turn, boolean flag) {
		 // local variables to hold the computed PWM values for the motors
       double left = 0;
       double right = 0;
           // square the inputs (while preserving the sign) to increase fine control while permitting full power
       if (move > 0.0) {
           left = move - turn;
           right = move + turn;
       } else {
    	   left = move + turn;
    	   right = move - turn;
       }
       if(right > 1){
    	   left -= right - 1;
    	   right = 1;
       } else if(left > 1){
    	   right -= left - 1;
    	   left = 1;
       } else if (right < -1){
    	   left -= right + 1;
    	   right = -1;
       } else if (left < -1){
    	   right -= left + 1;
    	   left = -1;
       }
       ob.driveMasterLeft.set(ControlMode.PercentOutput, -left);
       ob.driveMasterRight.set(ControlMode.PercentOutput, right);
       ob.updateLeftSide(-left);
       ob.updateRightSide(right);
	}
	/**
	 * makes sure the speed is within range (-1.0 to 1.0)
	 * @param num: speed to be checked
	 * @return the speed from a range of -1.0 to 1.0
	 */
	public double limit(double num){
		if(num > 1.0){
			num = 1.0;
		} else if(num < -1.0){
			num = -1.0;
		}
		return num;
	}
	/**
	 * turns the robot to an angle
	 * @param rotateValue: rotational speed (based off of gyroscope heading)
	 */
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
        ob.updateLeftSide(-leftMotorSpeed);
        ob.updateRightSide(rightMotorSpeed);
	}

	/**
	 * implemented method - sets the rotate to angle rate using a PID
	 */
	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;		
	}
}