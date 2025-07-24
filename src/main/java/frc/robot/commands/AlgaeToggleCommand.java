package frc.robot.commands;

import frc.robot.subsystems.AlgaeGrabberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class AlgaeToggleCommand extends Command {

  private final AlgaeGrabberSubsystem algaeGrabberSub;
  private boolean up;

  public AlgaeToggleCommand(AlgaeGrabberSubsystem subsystem, boolean up) {
    this.up = up;
    this.algaeGrabberSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    this.algaeGrabberSub.setSpeed(0);
    if (up) {
      this.algaeGrabberSub.raise(); 
    } else {
      this.algaeGrabberSub.lower();
    }

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
