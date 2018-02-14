package org.usfirst.frc.team20.robot;

public class OperatorControls {
	Collector collector;
	Elevator elevator;
	Arduino arduino;
	Zenith ob;
	boolean override;
	
	public OperatorControls(Collector c, Elevator e, Arduino a, Zenith o){
		collector = c;
		elevator = e;
		ob = o;
		arduino = a;
		override = false;
	}
	
	/**
	 * runs all of the controls of the operator
	 */
	public void operatorControls(){
		System.out.println("Position: " + ob.elevatorMaster.getSelectedSensorPosition(0));
		System.out.println("                                      Set Position: " + elevator.getSetPosition());
		//automated
		if(ob.operatorJoy.getRightYAxis() < 0.1 && ob.operatorJoy.getRightYAxis() > -0.1){
			try{
				arduino.getSensorData();
				if(arduino.getIRSensor()){
					collector.intake();
					collector.close();
					ob.operatorJoy.vibrate();
					ob.updateCube(true);
				} else {
					collector.open();
					ob.updateCube(false);
				}			
			} catch (Exception e){
				
			} finally {
//				System.out.println("Arudino Sensor Not Working");
			}
		}
		//controlled
		if(ob.operatorJoy.getButtonA()){
			collector.intake();
			ob.operatorJoy.vibrate();
		}
		if(ob.operatorJoy.getButtonB()){
			collector.stopRollers();
		}
		if(ob.operatorJoy.getButtonY()){
			collector.outtake();
		}
		if(ob.operatorJoy.getButtonDUp()){
			elevator.setScaleHigh();
		}
		if(ob.operatorJoy.getButtonDLeft()){
			elevator.setScaleMid();
		}
		if(ob.operatorJoy.getButtonDDown()) {
			elevator.setScaleLow();
		}
		if(ob.operatorJoy.getButtonDRight()) {
			elevator.setSwitch();
		}
		if(ob.operatorJoy.getButtonBack()) {
			elevator.setIntake();
		}
		if(ob.operatorJoy.getRightYAxis() > 0.5) {
			collector.arm45();
		}		
		if(ob.operatorJoy.getRightYAxis() < -0.5) {
			collector.armIntakePosition();
		}
		if(Math.abs(ob.operatorJoy.getLeftYAxis()) > 0.1 && ob.operatorJoy.getLeftAxisButton()){
			System.out.println("Overriding");
			elevator.moveSpeed(ob.operatorJoy.getLeftYAxis());
			override = true;
		} else {
			if(override){
				elevator.moveSpeed(0.0);
				override = false;
			}
		}
		if(ob.operatorJoy.getLeftYAxis() > 0.5 && !ob.operatorJoy.getLeftAxisButton() && !override){
			elevator.upIncrement();
		}
		if(ob.operatorJoy.getLeftYAxis() < -0.5 && !ob.operatorJoy.getLeftAxisButton() && !override){
			elevator.downIncrement();
		}
		if(ob.operatorJoy.getButtonStart() && ob.flipSwitch.get()){
			collector.arm180();
		}
		if(ob.operatorJoy.getRightTriggerAxis() > 0.1){
			collector.open();
		}
		if(ob.operatorJoy.getLeftTriggerAxis() > 0.1){
			collector.close();
		}
	}	
}
