package frc.robot.commands;

import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimbDownCommand extends Command {

  private final ClimberSubsystem climbSub;
  
  public ClimbDownCommand(ClimberSubsystem subsystem) {
    climbSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    climbSub.Raise();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;   
  }

}
