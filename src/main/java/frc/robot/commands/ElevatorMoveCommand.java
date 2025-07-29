package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ElevatorSubsystem;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorMoveCommand extends Command {

  private final ElevatorSubsystem elevatorSub;
  private final DoubleSupplier yAxis;
  private double motorSpeed;
  private double deadZone;
  private double holdSpeed;
  
  public ElevatorMoveCommand(ElevatorSubsystem subsystem, DoubleSupplier y) {
    elevatorSub = subsystem;
    yAxis = y;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    motorSpeed = Settings.getElevatorManualSpeed();
    holdSpeed = Settings.getElevatorHoldSpeed();
    deadZone = Settings.getJoystickDeadzone();
  }

  @Override
  public void execute() {
    double y = yAxis.getAsDouble();
    elevatorSub.GetPostion();
    if (Math.abs(y) > deadZone) {
      if (y > 0) {
        elevatorSub.SetSpeed((motorSpeed/2) * -1);
      } else {
        elevatorSub.SetSpeed(motorSpeed);
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
