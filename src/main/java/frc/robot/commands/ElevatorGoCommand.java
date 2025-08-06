package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorGoCommand extends Command {

  private final ElevatorSubsystem elevatorSub;
  private double setPoint;
  private double targetZone;
  private double holdSpeed;
  private int checkCount;
  private int level;
  
  public ElevatorGoCommand(ElevatorSubsystem subsystem, int level) {
    this.level = level;
    elevatorSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    checkCount = 0;
    setPoint = Settings.getElevatorSetpoint(level);
    targetZone = Settings.getElevatorTargetZone();
    holdSpeed = Settings.getElevatorHoldSpeed();
    elevatorSub.SetPosition(setPoint);
  }

  @Override
  public void execute() {
    //elevatorSub.GetPostion();
  }

  @Override
  public void end(boolean interrupted) {
    elevatorSub.SetSpeed(holdSpeed);
  }  

  @Override
  public boolean isFinished() {
    double position = elevatorSub.GetPostion();
    double error = setPoint - position;
    if (Math.abs(error) < targetZone) {
      checkCount += 1;
    } else {
      checkCount = 0;
    }
    if (checkCount >= 10) {
      return true;
    } else {
      return false;
    }
  }

}
