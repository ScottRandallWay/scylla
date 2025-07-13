package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ElevatorSubsystem;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorMoveCommand extends Command {

  private final ElevatorSubsystem elevatorSub;
  private final DoubleSupplier yAxis;
  private double motorSpeed;
  
  public ElevatorMoveCommand(ElevatorSubsystem subsystem, DoubleSupplier y) {
    elevatorSub = subsystem;
    yAxis = y;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    motorSpeed = Settings.getElevatorManualSpeed();
  }

  @Override
  public void execute() {
    double y = yAxis.getAsDouble();
    if (Math.abs(y) > 0.2) {
      if (y > 0) {
        elevatorSub.SetSpeed(motorSpeed * -1);
      } else {
        elevatorSub.SetSpeed(motorSpeed);
      }
    } else {
      elevatorSub.SetSpeed(0);
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
