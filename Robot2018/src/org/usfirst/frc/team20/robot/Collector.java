package org.usfirst.frc.team20.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Collector {
	Objects ob;

	public Collector(Objects o){
		ob = o;
	}
	
	public void intake(){
		ob.collector.set(ControlMode.PercentOutput, 1.0);
	}

	public void outtake(){
		ob.collector.set(ControlMode.PercentOutput, -1.0);
	}
	
	public void stopRollers(){
		ob.collector.set(ControlMode.PercentOutput, 0.0);
	}
	
	public void close(){
		ob.grabber.set(Value.kReverse);
	}

	public void open(){
		ob.grabber.set(Value.kForward);
	}
	
//	public void arm45(){
//		ob.piston45.set(Value.kForward);
//		ob.piston180.set(Value.kReverse);
//	}
//	
//	public void arm100(){
//		ob.piston45.set(Value.kReverse);
//		ob.piston180.set(Value.kForward);
//	}
//
//	public void arm180(){
//		ob.piston45.set(Value.kForward);
//		ob.piston180.set(Value.kForward);
//	}
}