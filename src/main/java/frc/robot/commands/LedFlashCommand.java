package frc.robot.commands;

import frc.robot.subsystems.LedSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class LedFlashCommand extends Command {

  private final LedSubsystem ledSub;

  public LedFlashCommand(LedSubsystem subsystem) {
    ledSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    ledSub.setFlashing(true);
  }

  @Override
  public void execute() { }

  @Override
  public void end(boolean interrupted) { }

  @Override
  public boolean isFinished() {
    return true;
  }

}
