package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorGoCommand extends Command {

  private final ElevatorSubsystem elevatorSub;
  private double setPoint;
  private double highSpeed;
  private double lowSpeed;
  private double holdSpeed;
  private double position;
  private double targetZone;
  private double slowZone;
  private int level;
  
  public ElevatorGoCommand(ElevatorSubsystem subsystem, int level) {
    this.level = level;
    elevatorSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    setPoint = Settings.getElevatorSetpoint(level);
    elevatorSub.SetPosition(setPoint);
  }

  @Override
  public void execute() {
    elevatorSub.GetPostion();
  }

  @Override
  public void end(boolean interrupted) {}  

  @Override
  public boolean isFinished() {
    return false;
  }

}
