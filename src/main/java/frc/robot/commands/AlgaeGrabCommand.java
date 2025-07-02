package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.AlgaeGrabberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class AlgaeGrabCommand extends Command {

  private final AlgaeGrabberSubsystem algaeGrabberSub;
  private double motorSpeed;
  private double holdSpeed;

  public AlgaeGrabCommand(AlgaeGrabberSubsystem subsystem) {
    this.algaeGrabberSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    motorSpeed = Settings.getAlgaeMotorSpeed();
    holdSpeed = Settings.getAlgaeHoldSpeed();
    this.algaeGrabberSub.lower();
  }

  @Override
  public void execute() {
    this.algaeGrabberSub.setSpeed(motorSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    this.algaeGrabberSub.setSpeed(holdSpeed);
    this.algaeGrabberSub.raise();
  }

  @Override
  public boolean isFinished() {
    return this.algaeGrabberSub.isBallCaputured();
  }

}
