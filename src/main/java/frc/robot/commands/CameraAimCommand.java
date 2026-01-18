package frc.robot.commands;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.SpeedController;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.CommandSwerveDrivetrain;


public class CameraAimCommand extends Command {

  private final CameraSubsystem cameraSub;
  private final CommandSwerveDrivetrain driveTrain;
  private final SwerveRequest.RobotCentric alignRequest;
  private final SpeedController xController;
  private final SpeedController yController; 
  private final SpeedController yawControler;
  
  public CameraAimCommand(CameraSubsystem subsystem, CommandSwerveDrivetrain swerveDrivetrain) {
    cameraSub = subsystem;
    driveTrain = swerveDrivetrain;

    // speed controllers
    xController = new SpeedController(2, 0.07, 0, 30, 5, 100);
    yController = new SpeedController(2, 0.07, 75, 30, 5, 100);
    yawControler = new SpeedController(0.35, 0.07, 0, 25, 5, 100);

    // drive system request    
    alignRequest = new SwerveRequest.RobotCentric().withDriveRequestType(DriveRequestType.OpenLoopVoltage);
    alignRequest.VelocityX = 0;
    alignRequest.VelocityY = 0;
    alignRequest.RotationalRate = 0;
       
    addRequirements(subsystem, swerveDrivetrain);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    boolean hasTarget = cameraSub.hasTarget();
    if (hasTarget) {

      // get camera values
      double tx = cameraSub.getHorizontalOffset();
      double dist = cameraSub.getDistance(0);
      double yaw = cameraSub.getYaw();
      
      double rotationSpeed = yawControler.calculate(yaw);
      double xSpeed = xController.calculate(tx) * -1;
      double ySpeed = yController.calculate(dist) * -1;
      
      alignRequest.RotationalRate = rotationSpeed;
      alignRequest.VelocityX = xSpeed;
      alignRequest.VelocityY = ySpeed;

    } else {

      // stop if no target
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
  
}
