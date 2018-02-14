//Author: Maxwell Hoffman
package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.CameraServer;

public class DriverVision {
	int usbPort;
	String name;
	
	public DriverVision(String name, int usbPort){
		this.name = name;
		this.usbPort = usbPort;
	}
	
 	/**
 	 * initializes the USB camera and starts streaming to the dashboard
 	 */
	public void startUSBCamera(){
		try{
			CameraServer server = CameraServer.getInstance();
			server.startAutomaticCapture(name, usbPort);
		}catch(Exception e){
			System.out.println(name + " Not Working: " + e.toString());
		}
	}
}