package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.AlgaeGrabberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class AlgaeEjectCommand extends Command {

  private final AlgaeGrabberSubsystem algaeGrabberSub;
  private double speed;
  private double delay;
  private Timer timer;

  public AlgaeEjectCommand(AlgaeGrabberSubsystem subsystem) {
    algaeGrabberSub = subsystem;
    timer = new Timer();
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    delay = Settings.getAlgaeEjectDelaySeconds();
    speed = Settings.getAlgaeMotorSpeed() * -1;
    timer.reset();
  }

  @Override
  public void execute() {
    this.algaeGrabberSub.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    this.algaeGrabberSub.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    if (!this.algaeGrabberSub.isBallCaputured()) {
      timer.start();
    }    
    if (timer.hasElapsed(delay)) {
      timer.stop();
      return true;
    } else {
      return false;
    }
  }

}
