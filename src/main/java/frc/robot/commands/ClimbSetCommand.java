package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimbSetCommand extends Command {

  private final ClimberSubsystem climbSub;
  private double setPoint;
  private double motorHighSpeed;
  private double motorLowSpeed;
  private double position;
  private double targetZone;
  private double slowZone;
  private boolean close;
  
  public ClimbSetCommand(ClimberSubsystem subsystem, boolean close) {
    climbSub = subsystem;
    this.close = close;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    motorHighSpeed = Settings.getDoorHighSpeed();
    motorLowSpeed = Settings.getDoorLowSpeed();
    if (close == false) {
      setPoint = 0;
    } else {
      setPoint = Settings.getDoorClosedSetpoint();
    }
    slowZone = Settings.getDoorSlowZone();
    targetZone = Settings.getDoorTargetZone();
  }

  @Override
  public void execute() {
    position = climbSub.GetPostion();
    double error = setPoint - position;    
    double speed = motorHighSpeed;
    if (Math.abs(error) < slowZone) {
      speed = motorLowSpeed;
    }
    if (error > 0) {
      climbSub.SetSpeed(speed);
    } else {
      climbSub.SetSpeed(speed * -1);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    double error = position - setPoint;
    if (Math.abs(error) < targetZone) {
      return true;
    }
    return false;
  }

}
