package frc.robot.commands;

import frc.robot.Settings;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorSetCommand extends Command {

  private final ElevatorSubsystem elevatorSub;
  private double setPoint;
  private double highSpeed;
  private double lowSpeed;
  private double holdSpeed;
  private double startingPosition;
  private double position;
  private double targetZone;
  private double slowZone;
  private double gravityFactor;
  private double accel;
  private int level;
  
  public ElevatorSetCommand(ElevatorSubsystem subsystem, int level) {
    this.level = level;
    elevatorSub = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    
    // maximum speed
    highSpeed = Settings.getElevatorHighSpeed();

    // minimum speed
    lowSpeed = Settings.getElevatorLowSpeed();
    
    // hold power when stopped
    holdSpeed = Settings.getElevatorHoldSpeed();

    // the target location
    setPoint = Settings.getElevatorSetpoint(level);

    // zone for acceleration or deceleration
    slowZone = Settings.getElevatorSlowZone();

    // stop within this proximity
    targetZone = Settings.getElevatorTargetZone();

    // factor to reduce power when going down
    gravityFactor = Settings.getElevatorGravityFactor();
    
    // starting postion when command starts
    startingPosition = elevatorSub.GetPostion();
    
    // rate of acceleration 
    accel = (highSpeed - lowSpeed) / slowZone;
  }

  @Override
  public void execute() {

    // get current position
    position = elevatorSub.GetPostion();

    // get distance from setpoint
    double error = setPoint - position;

    // get distance from starting position
    double startError = position - startingPosition;
    startError *= 3;
    
    // set default speed
    double speed = highSpeed;

    // slow as you approach or leave
    if (Math.abs(error) < slowZone) {
      speed = (Math.abs(error) * accel) + lowSpeed;
    } 
    else if (Math.abs(startError) < slowZone) {
      speed = (Math.abs(startError) * accel) + lowSpeed;
    }

    System.out.println("speed: " + speed);
    System.out.println("error: " + error);

    // set motor direction
    if (error > 0) {
      elevatorSub.SetSpeed(speed);
    } else {
      elevatorSub.SetSpeed(speed * gravityFactor);
    }
  }

  @Override
  public void end(boolean interrupted) {
    elevatorSub.SetSpeed(holdSpeed);
  }

  @Override
  public boolean isFinished() {
    double error = position - setPoint;
    if (Math.abs(error) < targetZone) {
      return true;
    }
    return false;
  }

}
