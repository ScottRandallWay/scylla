package frc.robot;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public final class Dashboard {

  public static final String DRIVE_TAB = "Scylla";
  public static final String SETTINGS_TAB = "Preferences";
  
  public static GenericEntry getElevatorEntry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Elevator Position", 0)
      .withSize(1,1)
      .withPosition(0, 0)
      .getEntry();
  }

  public static GenericEntry getAlgaeEntry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Algae Sensor", 0)
      .withSize(1, 1)
      .withPosition(0, 1)
      .getEntry();
  }

  public static GenericEntry getDoorEntry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Door Position", 0)
      .withSize(1, 1)
      .withPosition(0, 2)
      .getEntry();
  }

}
