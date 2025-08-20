package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.CoralSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class CoralShootCommand extends Command {

  private final CoralSubsystem coralSub;
  private double motorSpeed;
  private Timer timer;

  public CoralShootCommand(CoralSubsystem subsystem) {
    this.coralSub = subsystem;
    addRequirements(subsystem);
    timer = new Timer();
  }

  @Override
  public void initialize() {
    motorSpeed = Settings.getCoralSpeed();
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    coralSub.SetSpeed(motorSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    this.coralSub.SetSpeed(0);
  }

  @Override
  public boolean isFinished() {
    if (timer.hasElapsed(0.5)) {
      timer.stop();
      return true;
    } else {
      return false;
    }
  }

}
