package frc.robot.commands;

import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimbUpCommand extends Command {

  private final ClimberSubsystem climbSub;
  
  public ClimbUpCommand(ClimberSubsystem subsystem) {
    climbSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    climbSub.Lower();
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
