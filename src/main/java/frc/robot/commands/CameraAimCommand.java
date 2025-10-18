package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CameraSubsystem;

public class CameraAimCommand extends Command {

  private final CameraSubsystem cameraSub;
  private double maxSpeed;
  private double kP_range;
  private double kP_aim;
  private double MaxAngularRate;

  public CameraAimCommand(CameraSubsystem subsystem, double maxSpeed, double kP_range, double kP_aim, double MaxAngularRate) {
    this.maxSpeed = maxSpeed;
    this.kP_range = kP_range;
    this.kP_aim = kP_aim;
    this.MaxAngularRate = MaxAngularRate;
    cameraSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() { 
    if (cameraSub.hasTarget()) {
      double x = cameraSub.getTx();
      double y = cameraSub.getTy();
      cameraSub.getSpeed(y, kP_range, maxSpeed);
      cameraSub.getAngle(y, kP_aim, MaxAngularRate);
    } 
  }

  @Override
  public void end(boolean interrupted) { }

  @Override
  public boolean isFinished() {
    return false;
  }

}
