package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.CoralSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class CoralSetCommand extends Command {

  private final CoralSubsystem coralSub;
  private double motorSpeed;
  private boolean forward;

  public CoralSetCommand(CoralSubsystem subsystem, boolean forward) {
    this.coralSub = subsystem;
    addRequirements(subsystem);
    this.forward = forward;
  }

  @Override
  public void initialize() {
    if(forward){
      motorSpeed = Settings.getCoralSpeed();
    }
    else{
      motorSpeed = Settings.getCoralSpeed() * -1;
    }
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
    return false;
  }

}
