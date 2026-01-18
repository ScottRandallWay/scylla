package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Dashboard;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.LimelightResults;
import frc.robot.LimelightHelpers.LimelightTarget_Fiducial;
import frc.robot.LimelightHelpers.PoseEstimate;
import frc.robot.LimelightHelpers.RawFiducial;

public class CameraSubsystem extends SubsystemBase {

  private GenericEntry tagIdEntry;
  private GenericEntry tagTvEntry;
  private GenericEntry tagDistanceEntry;
  private GenericEntry tagYawEntry;
  private GenericEntry tagTxEntry;
  private GenericEntry tag2IdEntry;
  private GenericEntry tag2DistanceEntry;
  private GenericEntry tag2YawEntry;
  private GenericEntry tag2TxEntry;
  private final String LIMELIGHT_NAME = "limelight-twelve";
  private final NetworkTable table;

  public CameraSubsystem() {
    table = NetworkTableInstance.getDefault().getTable(LIMELIGHT_NAME);
    tagIdEntry = Dashboard.getTagEntry();
    tagTvEntry = Dashboard.getHasTargetEntry();
    tagTxEntry = Dashboard.getTagTxEntry();
    tagDistanceEntry = Dashboard.getTargetDistance();
    tagYawEntry = Dashboard.getYawEntry();
    tag2IdEntry = Dashboard.getTag2Entry();
    tag2TxEntry = Dashboard.getTag2TxEntry();
    tag2DistanceEntry = Dashboard.getTarget2Distance();
    tag2YawEntry = Dashboard.getYaw2Entry();
  }

  public double getId() {
    double id = table.getEntry("tid").getDouble(0.0);
    tagIdEntry.setDouble(id);
    return id;    
  }

  public double[] getIds() {
    PoseEstimate estimate = LimelightHelpers.getBotPoseEstimate_wpiBlue(LIMELIGHT_NAME);
    double[] ids = new double[estimate.rawFiducials.length];
    for(int i = 0; i < estimate.rawFiducials.length; i++) {
      ids[i] = estimate.rawFiducials[i].id;
    }
    if(ids.length > 0)
    {
      tagIdEntry.setDouble(ids[0]);
    }
    if(ids.length > 1)
    {
      tag2IdEntry.setDouble(ids[1]);
    }
    return ids;    
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

  public double[] getHorizontalOffsets() {
    PoseEstimate estimate = LimelightHelpers.getBotPoseEstimate_wpiBlue(LIMELIGHT_NAME);
    double[] degrees = new double[estimate.rawFiducials.length];
    for(int i = 0; i < estimate.rawFiducials.length; i++) {
      degrees[i] = estimate.rawFiducials[i].txnc;
    }   
    if(degrees.length > 0)
    {
      tagTxEntry.setDouble(degrees[0]);
    }
    if(degrees.length > 1)
    {
      tag2TxEntry.setDouble(degrees[1]);
    }
    return degrees;
  }

  public double getYaw() {
    Pose3d pose = LimelightHelpers.getBotPose3d(LIMELIGHT_NAME);
    Rotation3d rotation = pose.getRotation();
    double yaw = rotation.getZ();
    // yaw = Math.toDegrees(yaw);
    // yaw += 122.5;
    tagYawEntry.setDouble(yaw);
    return yaw;
  }

  public double[] getYawValues() {
    LimelightResults results = LimelightHelpers.getLatestResults(LIMELIGHT_NAME);
    double yaw[] = new double[results.targets_Fiducials.length];
    for (int i = 0; i < yaw.length; i++) {
      LimelightTarget_Fiducial tag = results.targets_Fiducials[i];
      Pose3d pose = tag.getCameraPose_TargetSpace();
      Rotation3d rotation = pose.getRotation();
      double radians = rotation.getZ();
      System.out.println("pose: " + i + " " + radians);
      yaw[i] = radians;
      //yaw[i] = Math.toDegrees(radians);
      //System.out.println("degrees: " + i + " " + yaw[i]);
    }
    if (yaw.length > 0) {
      tagYawEntry.setDouble(yaw[0]);
    } 
    if (yaw.length > 1) {
      tag2YawEntry.setDouble(yaw[1]);
    }

    return yaw;
  }

  public double getDistance(int index) {
    PoseEstimate estimate = LimelightHelpers.getBotPoseEstimate_wpiBlue(LIMELIGHT_NAME);
    double distance = 0;
    if (estimate.rawFiducials.length > (index - 1)) {
       distance = estimate.rawFiducials[index].distToCamera;
       distance *= 39.3701;
    } 
    tagDistanceEntry.setDouble(distance);
    return distance;
  }

  public double[] getDistances() {
    PoseEstimate estimate = LimelightHelpers.getBotPoseEstimate_wpiBlue(LIMELIGHT_NAME);
    double[] distances = new double[estimate.rawFiducials.length];
    for(int i = 0; i < estimate.rawFiducials.length; i++) {
      double distance = estimate.rawFiducials[i].distToCamera * 39.3701;
      distances[i] = distance;
    }
    if (distances.length > 0) {
      tagDistanceEntry.setDouble(distances[0]);
    } 
    if (distances.length > 1) {
      tag2DistanceEntry.setDouble(distances[1]);
    }
    return distances;    
  }
  
}
