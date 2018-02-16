package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climber {
	
	Zenith ob;
	
	public Climber(Zenith o){
		ob = o;
	}
	
	/**
	 * runs the climber motors in order to climb
	 */
	public void climb(){
		ob.climberMaster.set(ControlMode.PercentOutput, 1.0); 
	}
	
	/**
	 * releases the pin of the climber, allowing it to extend to full length
	 */
	public void releasePin(){
		ob.climberSpringPin.set(Value.kReverse);
	}
}
