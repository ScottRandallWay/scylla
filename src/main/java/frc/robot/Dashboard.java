package frc.robot;

import java.util.Map;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

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

  public static GenericEntry getHasTargetEntry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("HasTarget", false)
      .withWidget(BuiltInWidgets.kBooleanBox)
      .withProperties(Map.of("colorwhentrue", "green", "colorwhenfalse", "red"))
      .withSize(1,1)
      .withPosition(3, 0)
      .getEntry();      
  }

  public static GenericEntry getTagEntry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Tag ID", 0)
      .withSize(1, 1)
      .withPosition(4, 0)
      .getEntry(); 
  }

  public static GenericEntry getTagTxEntry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Tag Tx", 0)
      .withSize(1, 1)
      .withPosition(5, 0)
      .getEntry(); 
  }

  public static GenericEntry getTargetDistance() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("TargetDistance", 0.0)
      .withSize(1,1)
      .withPosition(6, 0)
      .getEntry();
  }

  public static GenericEntry getYawEntry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("TargetYaw", 0)
      .withSize(1,1)
      .withPosition(7,0)
      .getEntry();
  }

  public static GenericEntry getTag2Entry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Tag2 ID", 0)
      .withSize(1, 1)
      .withPosition(4, 1)
      .getEntry(); 
  }

  public static GenericEntry getTag2TxEntry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Tag2 Tx", 0)
      .withSize(1, 1)
      .withPosition(5, 1)
      .getEntry(); 
  }

  public static GenericEntry getTarget2Distance() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Target2Distance", 0.0)
      .withSize(1,1)
      .withPosition(6, 1)
      .getEntry();
  }

  public static GenericEntry getYaw2Entry() {
    return Shuffleboard.getTab(DRIVE_TAB)
      .add("Target2Yaw", 0)
      .withSize(1,1)
      .withPosition(7,1)
      .getEntry();
  }
  
}
