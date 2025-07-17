package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ClimberSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimbMoveCommand extends Command {

  private final ClimberSubsystem climbSub;
  private final DoubleSupplier xAxis;
  private double motorSpeed;
  private double deadZone;
  
  public ClimbMoveCommand(ClimberSubsystem subsystem, DoubleSupplier x) {
    climbSub = subsystem;
    xAxis = x;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    motorSpeed = Settings.getDoorHighSpeed();
    deadZone = Settings.getJoystickDeadzone();
  }

  @Override
  public void execute() {
    double x = xAxis.getAsDouble();
    if (Math.abs(x) > deadZone) {
      if (x > 0) {
        climbSub.SetSpeed(motorSpeed * -1);
      } else {
        climbSub.SetSpeed(motorSpeed);
      }
    } else {
      climbSub.SetSpeed(0);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }

}
