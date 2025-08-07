package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ClimberSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimbMoveCommand extends Command {

  private final ClimberSubsystem climbSub;
  private final DoubleSupplier xAxis;
  private final BooleanSupplier overrideButton;
  private double motorSpeed;
  private double deadZone;
  private final double OPEN_LIMIT = 0;
  private final double CLOSE_LIMIT = 13500;
  
  public ClimbMoveCommand(ClimberSubsystem subsystem, DoubleSupplier x, BooleanSupplier override) {
    climbSub = subsystem;
    xAxis = x;
    overrideButton = override;
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
    double position = climbSub.GetPostion();
    boolean override = overrideButton.getAsBoolean();
    if (Math.abs(x) > deadZone) {
      if (x > 0 && (position > OPEN_LIMIT || override)) {
        climbSub.SetSpeed(motorSpeed * -1);
      } else if (x < 0 && (position < CLOSE_LIMIT || override)) {
        climbSub.SetSpeed(motorSpeed);
      } else {
        climbSub.SetSpeed(0);  
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
