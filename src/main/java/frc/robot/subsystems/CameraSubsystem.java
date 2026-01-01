package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Value;

import org.opencv.dnn.Net;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Dashboard;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.PoseEstimate;

public class CameraSubsystem extends SubsystemBase {

  private GenericEntry tagIdEntry;
  private GenericEntry tagTvEntry;
  private GenericEntry tagDistanceEntry;
  private GenericEntry tagYawEntry;
  private GenericEntry tagTxEntry;
  private final String LIMELIGHT_NAME = "limelight-twelve";
  private final NetworkTable table;

  public CameraSubsystem() {
    table = NetworkTableInstance.getDefault().getTable(LIMELIGHT_NAME);
    tagIdEntry = Dashboard.getTagEntry();
    tagTvEntry = Dashboard.getHasTargetEntry();
    tagTxEntry = Dashboard.getTagTxEntry();
    tagDistanceEntry = Dashboard.getTargetDistance();
    tagYawEntry = Dashboard.getYawEntry();
  }

  public double getId() {
    double id = table.getEntry("tid").getDouble(0.0);
    tagIdEntry.setDouble(id);
    return id;    
  }

  public boolean hasTarget() {
    boolean hasTarget = table.getEntry("tv").getDouble(0.0) == 1.0;
    tagTvEntry.setBoolean(hasTarget);
    return hasTarget;
  }
  
  public double getHorizontalOffset() {
    double degrees = table.getEntry("tx").getDouble(0.0);
    tagTxEntry.setDouble(degrees);
    return degrees;
  }

  public double getYaw() {
    Pose3d pose = LimelightHelpers.getBotPose3d(LIMELIGHT_NAME);
    Rotation3d rotation = pose.getRotation();
    double yaw = rotation.getZ();
    yaw = Math.toDegrees(yaw);
    tagYawEntry.setDouble(yaw);
    return yaw;
  }

  public double getDistance() {
    PoseEstimate estimate = LimelightHelpers.getBotPoseEstimate_wpiBlue(LIMELIGHT_NAME);
    double distance = 0;
    if (estimate.rawFiducials.length > 0) {
       distance = estimate.rawFiducials[0].distToCamera;
       distance *= 39.3701;
    } 
    tagDistanceEntry.setDouble(distance);
    return distance;
  }
    
  
}
