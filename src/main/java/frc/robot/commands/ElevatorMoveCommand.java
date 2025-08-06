package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ElevatorSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorMoveCommand extends Command {

  private final ElevatorSubsystem elevatorSub;
  private final DoubleSupplier yAxis;
  private final BooleanSupplier override;
  private double motorSpeed;
  private double deadZone;
  private double holdSpeed;
  private double gravityFactor;
  private static final double MAX_HEIGHT = 80;
  private double position;
  
  public ElevatorMoveCommand(ElevatorSubsystem subsystem, DoubleSupplier y, BooleanSupplier override) {
    elevatorSub = subsystem;
    this.override = override;
    yAxis = y;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    motorSpeed = Settings.getElevatorManualSpeed();
    holdSpeed = Settings.getElevatorHoldSpeed();
    deadZone = Settings.getJoystickDeadzone();
    gravityFactor = Settings.getElevatorGravityFactor();
  }

  @Override
  public void execute() {
    double y = yAxis.getAsDouble();
    position = elevatorSub.GetPostion();
    boolean x = override.getAsBoolean();
    if (Math.abs(y) > deadZone) {
      if (y > 0) {
        if (position >= 5 || x) {
          elevatorSub.SetSpeed(motorSpeed * gravityFactor);
        } else {
          elevatorSub.SetSpeed(holdSpeed);
        }
      } else {
        if (position <= MAX_HEIGHT || x) {
          elevatorSub.SetSpeed(motorSpeed);
        } else {
          elevatorSub.SetSpeed(holdSpeed);
        }
      }
      elevatorSub.GetPostion();
    } else {
      elevatorSub.SetSpeed(holdSpeed);
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
