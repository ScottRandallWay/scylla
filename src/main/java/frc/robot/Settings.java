package frc.robot;

import edu.wpi.first.wpilibj.Preferences;

public class Settings {

  // keys
  private static final String ALGAE_DETECT_VOLT = "Algae Detection Voltage";
  private static final String ALGAE_MOTOR_SPEED = "Algae Motor Speed";
  private static final String ALGAE_HOLD_SPEED = "Algae Hold Speed";
  private static final String ALGAE_EJECT_DELAY = "Algae Eject Seconds";
  private static final String ENDGAME_SECONDS = "Endgame Seconds";
  private static final String DOOR_HIGH_SPEED = "Door High Speed";
  private static final String DOOR_LOW_SPEED = "Door Low Speed";
  private static final String DOOR_SLOW_ZONE = "Door Slow Zone";
  private static final String DOOR_TARGET_ZONE = "Door Target Zone";
  private static final String DOOR_CLOSED_SETPOINT = "Door Setpoint";
  private static final String ELEVATOR_MANUAL_SPEED = "Elevator Manual";

  //default values
  private static final double ALGAE_DETECT_DEFAULT = 0.2;
  private static final double ALGAE_MOTOR_DEFAULT = 0.40;
  private static final double ALGAE_HOLD_DEFAULT = 0.05;
  private static final int ALGAE_DELAY_DEFAULT = 1;
  private static final int ENDGAME_DEFAULT = 30;
  private static final double DOOR_HIGH_DEFAULT = 0.4;
  private static final double DOOR_LOW_DEFAULT = 0.075;
  private static final double DOOR_SLOW_ZONE_DEFAULT = 1500;
  private static final double DOOR_TARGET_DEFAULT = 250;
  private static final double DOOR_CLOSED_DEFAULT = 12500;
  private static final double ELEVATOR_MANUAL_DEFAULT = 0.4;

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
      if (!Preferences.containsKey(ENDGAME_SECONDS)) {
        Preferences.initInt(ENDGAME_SECONDS, ENDGAME_DEFAULT);
      }
      if (!Preferences.containsKey(DOOR_HIGH_SPEED)) {
        Preferences.initDouble(DOOR_HIGH_SPEED, DOOR_HIGH_DEFAULT);
      }
      if (!Preferences.containsKey(DOOR_LOW_SPEED)) {
        Preferences.initDouble(DOOR_LOW_SPEED, DOOR_LOW_DEFAULT);
      }
      if (!Preferences.containsKey(DOOR_SLOW_ZONE)) {
        Preferences.initDouble(DOOR_SLOW_ZONE, DOOR_SLOW_ZONE_DEFAULT);
      }
      if (!Preferences.containsKey(DOOR_TARGET_ZONE)) {
        Preferences.initDouble(DOOR_TARGET_ZONE, DOOR_TARGET_DEFAULT);
      }
      if (!Preferences.containsKey(DOOR_CLOSED_SETPOINT)) {
        Preferences.initDouble(DOOR_CLOSED_SETPOINT, DOOR_CLOSED_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_MANUAL_SPEED)) {
        Preferences.initDouble(ELEVATOR_MANUAL_SPEED, ELEVATOR_MANUAL_DEFAULT);
      } 
    } 

    public static final void Reset() {
      Preferences.setDouble(ALGAE_DETECT_VOLT, ALGAE_DETECT_DEFAULT);
      Preferences.setDouble(ALGAE_MOTOR_SPEED, ALGAE_MOTOR_DEFAULT);
      Preferences.setDouble(ALGAE_HOLD_SPEED, ALGAE_HOLD_DEFAULT);
      Preferences.setDouble(ALGAE_EJECT_DELAY, ALGAE_DELAY_DEFAULT);
      Preferences.setInt(ENDGAME_SECONDS, ENDGAME_DEFAULT);
      Preferences.setDouble(DOOR_HIGH_SPEED, DOOR_HIGH_DEFAULT);
      Preferences.setDouble(DOOR_LOW_SPEED, DOOR_LOW_DEFAULT);
      Preferences.setDouble(DOOR_SLOW_ZONE, DOOR_SLOW_ZONE_DEFAULT);
      Preferences.setDouble(DOOR_TARGET_ZONE, DOOR_TARGET_DEFAULT);
      Preferences.setDouble(DOOR_CLOSED_SETPOINT, DOOR_CLOSED_DEFAULT);
      Preferences.setDouble(ELEVATOR_MANUAL_SPEED, ELEVATOR_MANUAL_DEFAULT);
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

    public static int getEndGameSeconds() {
      return Preferences.getInt(ENDGAME_SECONDS, ENDGAME_DEFAULT);
    }

    public static double getDoorHighSpeed() {
      return Preferences.getDouble(DOOR_HIGH_SPEED, DOOR_HIGH_DEFAULT);
    }

    public static double getDoorLowSpeed() {
      return Preferences.getDouble(DOOR_LOW_SPEED, DOOR_LOW_DEFAULT);
    }

    public static double getDoorSlowZone() {
      return Preferences.getDouble(DOOR_SLOW_ZONE, DOOR_SLOW_ZONE_DEFAULT);
    }

    public static double getDoorTargetZone() {
      return Preferences.getDouble(DOOR_TARGET_ZONE, DOOR_TARGET_DEFAULT);
    }
  
    public static double getDoorClosedSetpoint() {
      return Preferences.getDouble(DOOR_CLOSED_SETPOINT, DOOR_CLOSED_DEFAULT);
    }
    public static double getElevatorManualSpeed() {
      return Preferences.getDouble(Settings.ELEVATOR_MANUAL_SPEED, Settings.ELEVATOR_MANUAL_DEFAULT);
    }
}
