package frc.robot;

import frc.robot.Constants.PowerConstants;

public final class Util {

  // prevents range exceptions on power values
  public static double enforcePowerRange(double power) {
    if (power > PowerConstants.MAX_POWER) 
      power = PowerConstants.MAX_POWER;
    else if (power < PowerConstants.MIN_POWER) 
      power = PowerConstants.MIN_POWER;
    return power;
  }
  
}
