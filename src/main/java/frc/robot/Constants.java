package frc.robot;

public final class Constants {
  
  public static final class DashboardKeys {
    public static final String DOOR_POSITION = "Door Position";
    public static final String ELEVATOR_POSITION = "Elevator Position";
  }
  
  public static final class JoystickChannels {
    public static final int OPERATOR_RIGHT_JOYSTICK = 0;
    public static final int OPERATOR_LEFT_JOYSTICK = 1;
    public static final int DRIVER_RIGHT_JOYSTICK = 2;
    public static final int DRIVER_LEFT_JOYSTICK = 3;
  }

  public static final class ButtonIndex {
    public static final class OperatorRight {
      public static final int CORAL_IN_BUTTON = 1;
      public static final int CORAL_OUT_BUTTON = 2;
      public static final int ALGAE_GRAB_BUTTON = 3;
      public static final int ALGAE_SET_BUTTON = 4;
      public static final int ALGAE_RAISE_BUTTON = 5;
      public static final int ALGAE_LOWER_BUTTON = 6;
      public static final int ELEVATOR_HOME = 8;
      public static final int ELEVATOR_RESET_BUTTON = 10;
    }
    public static final class OperatorLeft {
      public static final int DOOR_OPEN_BUTTON = 3;
      public static final int DOOR_CLOSE_BUTTON = 4;
      public static final int CLIMB_UP_BUTTON = 5;
      public static final int CLIMB_DOWN_BUTTON = 6;
      public static final int ELEVATOR_LEVEL2 = 8;
      public static final int ELEVATOR_LEVEL3 = 9;
    }
  }

  public static final class AnalogChannels {
    public static final int BALL_SENSOR = 1;
  }

  public static final class PnuematicChannels {
    public static final int ALGAE_GRABBER_REVERSE = 12;
    public static final int ALGAE_GRABBER_FORWARD = 13;
    public static final int CLIMBER_REVERSE = 14;
    public static final int CLIMBER_FORWARD = 15;
    public static final int PNEUMATIC_HUB_MODULE = 41;
  }

  public static final class MotorPorts {
    public static final int CORAL_MOTOR = 9;
    public static final int ALGAE_GRABBER = 10;
    public static final int CLIMBER_DOOR = 11;
    public static final int ELEVATOR_MOTOR = 12;
  }

  public static final class PowerConstants {
    public static final double MAX_POWER = 1;
    public static final double MIN_POWER = -1;
  }  
  
  public static final class TimeConstants {
    public static final int TELEOP_SECONDS = 135;
  }
}
