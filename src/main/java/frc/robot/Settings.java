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
  private static final String JOYSTICK_DEADZONE = "Joystick Deadzone";
  private static final String CORAL_SPEED = "Coral Speed";
  private static final String ELEVATOR_HIGH_SPEED = "Elevator High Speed";
  private static final String ELEVATOR_LOW_SPEED = "Elevator Low Speed";
  private static final String ELEVATOR_HOLD_SPEED = "Elevator Hold Speed";
  private static final String ELEVATOR_SLOW_ZONE = "Elevator Slow Zone";
  private static final String ELEVATOR_TARGET_ZONE = "Elevator Target Zone";
  private static final String ELEVATOR_LEVEL1_SETPOINT = "Level 1 Setpoint";
  private static final String ELEVATOR_LEVEL2_SETPOINT = "Level 2 Setpoint";
  private static final String ELEVATOR_LEVEL3_SETPOINT = "Level 3 Setpoint";
  private static final String ELEVATOR_LEVEL4_SETPOINT = "Level 4 Setpoint";
  
  //default values
  private static final double ALGAE_DETECT_DEFAULT = 0.2;
  private static final double ALGAE_MOTOR_DEFAULT = 0.50;
  private static final double ALGAE_HOLD_DEFAULT = 0.05;
  private static final int ALGAE_DELAY_DEFAULT = 1;
  private static final int ENDGAME_DEFAULT = 30;
  private static final double DOOR_HIGH_DEFAULT = 0.4;
  private static final double DOOR_LOW_DEFAULT = 0.075;
  private static final double DOOR_SLOW_ZONE_DEFAULT = 1500;
  private static final double DOOR_TARGET_DEFAULT = 250;
  private static final double DOOR_CLOSED_DEFAULT = 12000;
  private static final double ELEVATOR_MANUAL_DEFAULT = 0.4;
  private static final double JOYSTICK_DEADZONE_DEFAULT = 0.2;
  private static final double CORAL_SPEED_DEFAULT = 0.3;
  private static final double ELEVATOR_HIGH_SPEED_DEFAULT = 0.5;
  private static final double ELEVATOR_LOW_SPEED_DEFAULT = 0.075;
  private static final double ELEVATOR_HOLD_SPEED_DEFAULT = 0.2;
  private static final double ELEVATOR_SLOW_ZONE_DEFAULT = 10;
  private static final double ELEVATOR_TARGET_ZONE_DEFAULT = 0.5;
  private static final double ELEVATOR_LEVEL1_SETPOINT_DEFAULT = 10;
  private static final double ELEVATOR_LEVEL2_SETPOINT_DEFAULT = 26;
  private static final double ELEVATOR_LEVEL3_SETPOINT_DEFAULT = 46;
  private static final double ELEVATOR_LEVEL4_SETPOINT_DEFAULT = 75;

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
      if (!Preferences.containsKey(JOYSTICK_DEADZONE)) {
        Preferences.initDouble(JOYSTICK_DEADZONE, JOYSTICK_DEADZONE_DEFAULT);
      }
      if (!Preferences.containsKey(CORAL_SPEED)) {
        Preferences.initDouble(CORAL_SPEED, CORAL_SPEED_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_HIGH_SPEED)) {
        Preferences.initDouble(ELEVATOR_HIGH_SPEED, ELEVATOR_HIGH_SPEED_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_LOW_SPEED)) {
        Preferences.initDouble(ELEVATOR_LOW_SPEED, ELEVATOR_LOW_SPEED_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_HOLD_SPEED)) {
        Preferences.initDouble(ELEVATOR_HOLD_SPEED, ELEVATOR_HOLD_SPEED_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_SLOW_ZONE)) {
        Preferences.initDouble(ELEVATOR_SLOW_ZONE, ELEVATOR_SLOW_ZONE_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_TARGET_ZONE)) {
        Preferences.initDouble(ELEVATOR_TARGET_ZONE, ELEVATOR_TARGET_ZONE_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_LEVEL1_SETPOINT)) {
        Preferences.initDouble(ALGAE_DETECT_VOLT, ALGAE_DELAY_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_LEVEL1_SETPOINT)) {
        Preferences.initDouble(ELEVATOR_LEVEL1_SETPOINT, ELEVATOR_LEVEL1_SETPOINT_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_LEVEL2_SETPOINT)) {
        Preferences.initDouble(ELEVATOR_LEVEL2_SETPOINT, ELEVATOR_LEVEL2_SETPOINT_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_LEVEL3_SETPOINT)) {
        Preferences.initDouble(ELEVATOR_LEVEL3_SETPOINT, ELEVATOR_LEVEL3_SETPOINT_DEFAULT);
      }
      if (!Preferences.containsKey(ELEVATOR_LEVEL4_SETPOINT)) {
        Preferences.initDouble(ELEVATOR_LEVEL4_SETPOINT, ELEVATOR_LEVEL4_SETPOINT_DEFAULT);
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
      Preferences.setDouble(JOYSTICK_DEADZONE, JOYSTICK_DEADZONE_DEFAULT);
      Preferences.setDouble(CORAL_SPEED, CORAL_SPEED_DEFAULT);
      Preferences.setDouble(ELEVATOR_HIGH_SPEED, ELEVATOR_HIGH_SPEED_DEFAULT);
      Preferences.setDouble(ELEVATOR_LOW_SPEED, ELEVATOR_LOW_SPEED_DEFAULT);
      Preferences.setDouble(ELEVATOR_HOLD_SPEED, ELEVATOR_HOLD_SPEED_DEFAULT);
      Preferences.setDouble(ELEVATOR_SLOW_ZONE, ELEVATOR_SLOW_ZONE_DEFAULT);
      Preferences.setDouble(ELEVATOR_TARGET_ZONE, ELEVATOR_TARGET_ZONE_DEFAULT);
      Preferences.setDouble(ELEVATOR_LEVEL1_SETPOINT, ELEVATOR_LEVEL2_SETPOINT_DEFAULT);
      Preferences.setDouble(ELEVATOR_LEVEL2_SETPOINT, ELEVATOR_LEVEL2_SETPOINT_DEFAULT);
      Preferences.setDouble(ELEVATOR_LEVEL3_SETPOINT, ELEVATOR_LEVEL3_SETPOINT_DEFAULT);
      Preferences.setDouble(ELEVATOR_LEVEL4_SETPOINT, ELEVATOR_LEVEL4_SETPOINT_DEFAULT);
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
      return Preferences.getDouble(ELEVATOR_MANUAL_SPEED, ELEVATOR_MANUAL_DEFAULT);
    }

    public static double getJoystickDeadzone() {
      return Preferences.getDouble(JOYSTICK_DEADZONE, JOYSTICK_DEADZONE_DEFAULT);
    }

    public static double getCoralSpeed() {
      return Preferences.getDouble(CORAL_SPEED, CORAL_SPEED_DEFAULT);
    }

    public static double getElevatorHighSpeed() {
      return Preferences.getDouble(ELEVATOR_HIGH_SPEED, ELEVATOR_HIGH_SPEED_DEFAULT);
    }

    public static double getElevatorLowSpeed() {
      return Preferences.getDouble(ELEVATOR_LOW_SPEED, ELEVATOR_LOW_SPEED_DEFAULT);
    }
    
    public static double getElevatorHoldSpeed() {
      return Preferences.getDouble(ELEVATOR_HOLD_SPEED, ELEVATOR_HOLD_SPEED_DEFAULT);
    }

    public static double getElevatorSlowZone() {
      return Preferences.getDouble(ELEVATOR_SLOW_ZONE, ELEVATOR_SLOW_ZONE_DEFAULT);
    }

    public static double getElevatorTargetZone() {
      return Preferences.getDouble(ELEVATOR_TARGET_ZONE, ELEVATOR_TARGET_ZONE_DEFAULT);
    }
    
    public static double getElevatorSetpoint(int level) {
      switch(level) {
        case 1:
          return Preferences.getDouble(ELEVATOR_LEVEL1_SETPOINT, ELEVATOR_LEVEL1_SETPOINT_DEFAULT);
        case 2:
          return Preferences.getDouble(ELEVATOR_LEVEL2_SETPOINT, ELEVATOR_LEVEL2_SETPOINT_DEFAULT);
        case 3:
          return Preferences.getDouble(ELEVATOR_LEVEL3_SETPOINT, ELEVATOR_LEVEL3_SETPOINT_DEFAULT);
        case 4: 
          return Preferences.getDouble(ELEVATOR_LEVEL4_SETPOINT, ELEVATOR_LEVEL4_SETPOINT_DEFAULT);
        default:
          return 0;
      }
    }

}
