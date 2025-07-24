package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorSetCommand extends Command {

  private final ElevatorSubsystem elevatorSub;
  private double setPoint;
  private double highSpeed;
  private double lowSpeed;
  private double holdSpeed;
  private double position;
  private double targetZone;
  private double slowZone;
  private int level;
  
  public ElevatorSetCommand(ElevatorSubsystem subsystem, int level) {
    this.level = level;
    elevatorSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    highSpeed = Settings.getDoorHighSpeed();
    lowSpeed = Settings.getDoorLowSpeed();
    holdSpeed = Settings.getElevatorHoldSpeed();
    setPoint = Settings.getElevatorSetpoint(level);
    slowZone = Settings.getElevatorSlowZone();
    targetZone = Settings.getElevatorTargetZone();
  }

  @Override
  public void execute() {
    position = elevatorSub.GetPostion();
    double error = setPoint - position;    
    double speed = highSpeed;
    if (Math.abs(error) < slowZone) {
      speed = lowSpeed;
    }
    if (error > 0) {
      elevatorSub.SetSpeed(speed);
    } else {
      elevatorSub.SetSpeed(speed * -1);
    }
  }

  @Override
  public void end(boolean interrupted) {
    elevatorSub.SetSpeed(holdSpeed);
  }

  @Override
  public boolean isFinished() {
    double error = position - setPoint;
    if (Math.abs(error) < targetZone) {
      return true;
    }
    return false;
  }

}
