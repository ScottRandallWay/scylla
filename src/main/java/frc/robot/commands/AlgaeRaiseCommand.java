package frc.robot.commands;

import frc.robot.subsystems.AlgaeGrabberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class AlgaeRaiseCommand extends Command {

  private final AlgaeGrabberSubsystem algaeGrabberSub;

  public AlgaeRaiseCommand(AlgaeGrabberSubsystem subsystem) {
    this.algaeGrabberSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    this.algaeGrabberSub.setSpeed(0);
    this.algaeGrabberSub.raise();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }

}
