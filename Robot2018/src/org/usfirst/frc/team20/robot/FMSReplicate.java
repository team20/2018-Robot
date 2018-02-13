package org.usfirst.frc.team20.robot;

import java.util.Random;

public class FMSReplicate {

	/**
	 * @return placement of the switch and scale (starts with your switch)
	 */
	public static String getGameSpecificMessage() {
		Random rand = new Random();
		double patch = rand.nextDouble();
		if (patch < .125) {
			return "LLL";
		} else if (patch < .25) {
			return "LLR";
		} else if (patch < .325) {
			return "LRL";
		} else if (patch < .5) {
			return "LRR";
		} else if (patch < .625) {
			return "RLL";
		} else if (patch < .75) {
			return "RLR";
		} else if (patch < .825) {
			return "RRL";
		} else {
			return "RRR";
		}

	}
}