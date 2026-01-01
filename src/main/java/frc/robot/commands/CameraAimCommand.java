package frc.robot.commands;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class CameraAimCommand extends Command {

  private final CameraSubsystem cameraSub;
  private final CommandSwerveDrivetrain driveTrain;
  private final SwerveRequest.RobotCentric alignRequest;
  private final PIDController angleController;
  private final PIDController xController;
  private final PIDController yController;

  public CameraAimCommand(CameraSubsystem subsystem, CommandSwerveDrivetrain swerveDrivetrain) {
    cameraSub = subsystem;
    driveTrain = swerveDrivetrain;

    // drive system request    
    alignRequest = new SwerveRequest.RobotCentric().withDriveRequestType(DriveRequestType.OpenLoopVoltage);
    alignRequest.VelocityX = 0;
    alignRequest.VelocityY = 0;
    
    // pid controllers
    xController = new PIDController(1, 0, 0);
    yController = new PIDController(1,0, 0);    
    angleController = new PIDController(0.15, 0, 0);
    angleController.enableContinuousInput(-Math.PI, Math.PI);
    
    addRequirements(subsystem, swerveDrivetrain);
  }

  @Override
  public void initialize() {
    
    // reset controllers
    xController.reset();
    yController.reset();
    angleController.reset();

    // update dashboard
    cameraSub.getId();
    cameraSub.getHorizontalOffset();
    cameraSub.getYaw();
    cameraSub.getDistance();
  }

  @Override
  public void execute() {
    boolean hasTarget = cameraSub.hasTarget();
    System.out.println("hasTarget: " + hasTarget);
    if (hasTarget) {
      double tx = cameraSub.getHorizontalOffset();
      double dist = cameraSub.getDistance();
      double yaw = cameraSub.getYaw();
            
      // double rotationSpeed = angleController.calculate(tx, 0);
      double xSpeed = computeHorizontalSpeed(tx);
      double rotationSpeed = computeRotationSpeed(yaw);
      double ySpeed = computeDistanceSpeed(dist);
      System.out.println("rotationalspeed: " + rotationSpeed);
      alignRequest.RotationalRate = rotationSpeed;
      alignRequest.VelocityX = xSpeed;
      alignRequest.VelocityY = ySpeed;
    } else {
      alignRequest.VelocityX = 0;
      alignRequest.VelocityY = 0;
      alignRequest.RotationalRate = 0;
    }
    driveTrain.setControl(alignRequest);
  }

  @Override
  public void end(boolean interrupted) { 
    alignRequest.VelocityX = 0;
    alignRequest.VelocityY = 0;
    alignRequest.RotationalRate = 0;
    driveTrain.setControl(alignRequest);    
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private double computeRotationSpeed(double yaw) {
    double speed = 0.5;
    if (Math.abs(yaw) < 10) speed = 0.25;
    if (Math.abs(yaw) < 5) speed = 0.1;
    if (Math.abs(yaw) < 1) speed = 0;    
    if (yaw > 0) {
      speed *= -1;
    } 
    //System.out.println("rotation speed: " + speed);
    return speed;
  }

  private double computeHorizontalSpeed(double tx) {
    double speed = 0.75;
    if (Math.abs(tx) < 20) speed = 0.5;
    if (Math.abs(tx) < 10) speed = 0.25;
    if (Math.abs(tx) < 5) speed = 0;    
    if (tx < 0) {
      speed *= -1;
    } 
    //System.out.println("x: " + tx);
    //System.out.println("x speed: " + speed);
    return speed;
  }

  private double computeDistanceSpeed(double distance) {
    double offset = 30;
    distance = distance - offset;
    double speed = 0.75;
    if (Math.abs(distance) < 20) speed = 0.5;
    if (Math.abs(distance) < 10) speed = 0.25;
    if (Math.abs(distance) < 2) speed = 0;    
    if (distance < 0) {
      speed *= -1;
    } 
    System.out.println("distance: " + distance);
    System.out.println("y speed: " + speed);
    return speed;
  }

}
