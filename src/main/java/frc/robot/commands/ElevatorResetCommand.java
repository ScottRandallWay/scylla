package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorResetCommand extends Command {

  private final ElevatorSubsystem elevatorSub;
  
  private double holdSpeed;
  
  public ElevatorResetCommand(ElevatorSubsystem subsystem) {
    elevatorSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
      elevatorSub.ResetPosition();
  }

  @Override
  public void execute() { }

  @Override
  public void end(boolean interrupted) {
    elevatorSub.SetSpeed(holdSpeed);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

}
