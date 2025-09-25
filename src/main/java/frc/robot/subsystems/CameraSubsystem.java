package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Dashboard;
import frc.robot.LimelightHelpers;

public class CameraSubsystem extends SubsystemBase {

  private GenericEntry tagEntry;
  private GenericEntry tagSpeed;
  private GenericEntry tagAngle;
  private GenericEntry tagTx;
  private GenericEntry tagTy;
  private final String LIMELIGHT_NAME = "limelight12";

  public CameraSubsystem() {
    tagEntry = Dashboard.getTagEntry();
    tagSpeed = Dashboard.getTagSpeedEntry();
    tagAngle = Dashboard.getAlgaeEntry();
    tagTx = Dashboard.getTagTxEntry();
    tagTy = Dashboard.getTagTyEntry();
  }

  public boolean hasTarget() {
    boolean hasTarget = LimelightHelpers.getTV(LIMELIGHT_NAME);
    if (hasTarget) {
      double id = LimelightHelpers.getFiducialID(LIMELIGHT_NAME);
      tagEntry.setDouble(id);
    } else {
      tagEntry.setDouble(0);
      tagTy.setDouble(0);
      tagTx.setDouble(0);
      tagAngle.setDouble(0);
      tagSpeed.setDouble(0);
    }
    return hasTarget;
  }

  public double getTy() {
    double y = LimelightHelpers.getTY(LIMELIGHT_NAME);
    tagTy.setDouble(y);
    return y;
  }

  public double getTx() {
    double x = LimelightHelpers.getTX(LIMELIGHT_NAME);
    tagTy.setDouble(x);
    return x;
  }

  public double getSpeed(double error, double kP_range, double MaxSpeed) {
    double speed = error * kP_range;
    speed *= MaxSpeed;
    speed *= -1.0;
    tagSpeed.setDouble(speed);
    return speed;
  }

  public double getAngle(double error, double kP_aim, double MaxAngularRate)
  {
    double angle = error * kP_aim;
    angle *= MaxAngularRate;
    angle *= -1.0;
    tagAngle.setDouble(angle);
    return angle;
  }

}
