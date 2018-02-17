package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climber {
	
	Zenith ob;
	
	public Climber(Zenith o){
		ob = o;
	}
	
	/**
	 * runs the climber in order to extend it to the rung
	 */
	public void extend(){
		ob.climberMaster.set(ControlMode.PercentOutput, -1.0);
	}

	/**
	 * runs the climber motors in order to climb
	 */
	public void climb(){
		ob.climberMaster.set(ControlMode.PercentOutput, 1.0); 
	}
	
	/**
	 * stops the climber motors
	 */
	public void stop(){
		ob.climberMaster.set(ControlMode.PercentOutput, 0.0);
	}
	/**
	 * releases the pin of the climber, allowing it to pull the robot up
	 */
	public void releasePin(){
//		ob.climberSpringPin.set(Value.kReverse);
	}
}
