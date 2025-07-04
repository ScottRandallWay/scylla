package frc.robot.commands;

import frc.robot.subsystems.AlgaeGrabberSubsystem;
import frc.robot.subsystems.LedSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class LedBallCommand extends Command {

  private final LedSubsystem ledSub;
  private final AlgaeGrabberSubsystem algaeGrabberSub;

  public LedBallCommand(LedSubsystem ledSubsystem, AlgaeGrabberSubsystem algaeSubsystem) {
    ledSub = ledSubsystem;
    algaeGrabberSub = algaeSubsystem;
    addRequirements(ledSubsystem, algaeSubsystem);
  }

  @Override
  public void initialize() {
    if (algaeGrabberSub.isBallCaputured()) {
      ledSub.setColor(LedSubsystem.Color.GREEN);
    } else {
      ledSub.resetColor();
    }
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
