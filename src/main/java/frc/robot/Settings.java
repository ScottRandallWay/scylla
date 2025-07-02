package frc.robot;

import edu.wpi.first.wpilibj.Preferences;

public class Settings {

  // keys
  private static final String ALGAE_DETECT_VOLT = "Algae Detection Voltage";
  private static final String ALGAE_MOTOR_SPEED = "Algae Motor Speed";
  private static final String ALGAE_HOLD_SPEED = "Algae Hold Speed";
  private static final String ALGAE_EJECT_DELAY = "Algae Eject Seconds";

  //default values
  private static final double ALGAE_DETECT_DEFAULT = 0.2;
  private static final double ALGAE_MOTOR_DEFAULT = 0.30;
  private static final double ALGAE_HOLD_DEFAULT = 0;
  private static final int ALGAE_DELAY_DEFAULT = 1;

  public static final void Init() {
      if (!Preferences.containsKey(ALGAE_DETECT_VOLT)) {
        Preferences.initDouble(ALGAE_DETECT_VOLT, ALGAE_DETECT_DEFAULT);
      } 
      if (!Preferences.containsKey(ALGAE_MOTOR_SPEED)) {
        Preferences.initDouble(ALGAE_MOTOR_SPEED, ALGAE_MOTOR_DEFAULT);
      } 
      if (!Preferences.containsKey(ALGAE_HOLD_SPEED)) {
        Preferences.initDouble(ALGAE_HOLD_SPEED, ALGAE_HOLD_DEFAULT);
      }
      if (!Preferences.containsKey(ALGAE_EJECT_DELAY)) {
        Preferences.initInt(ALGAE_EJECT_DELAY, ALGAE_DELAY_DEFAULT);
      }
      
    }

    public static final void Reset() {
      Preferences.setDouble(ALGAE_DETECT_VOLT, ALGAE_DETECT_DEFAULT);
      Preferences.setDouble(ALGAE_MOTOR_SPEED, ALGAE_MOTOR_DEFAULT);
      Preferences.setDouble(ALGAE_HOLD_SPEED, ALGAE_HOLD_DEFAULT);
      Preferences.setDouble(ALGAE_EJECT_DELAY, ALGAE_DELAY_DEFAULT);
    }

    public static double getAlgaeDetectThreshold() {
      return Preferences.getDouble(Settings.ALGAE_DETECT_VOLT, Settings.ALGAE_DETECT_DEFAULT);
    }

    public static double getAlgaeMotorSpeed() {
      return Preferences.getDouble(ALGAE_MOTOR_SPEED, ALGAE_MOTOR_DEFAULT);
    }

    public static double getAlgaeHoldSpeed() {
      return Preferences.getDouble(ALGAE_HOLD_SPEED, ALGAE_HOLD_DEFAULT);
    }

    public static double getAlgaeEjectDelaySeconds() {
      return Preferences.getDouble(ALGAE_EJECT_DELAY, ALGAE_DELAY_DEFAULT);
    }
  
  
}
