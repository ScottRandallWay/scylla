package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.AlgaeGrabberSubsystem;
import frc.robot.subsystems.LedSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class AlgaeGrabCommand extends Command {

  private final AlgaeGrabberSubsystem algaeGrabberSub;
  private double motorSpeed;
  private double holdSpeed;
  private LedSubsystem ledSubsystem;

  public AlgaeGrabCommand(AlgaeGrabberSubsystem algeaSubsystem, LedSubsystem ledSubsystem) {
    this.algaeGrabberSub = algeaSubsystem;
    this.ledSubsystem = ledSubsystem;
    addRequirements(algeaSubsystem, ledSubsystem);
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
    ledSubsystem.setColor(LedSubsystem.Color.GREEN);
  }

  @Override
  public boolean isFinished() {
    return this.algaeGrabberSub.isBallCaputured();
  }

}
