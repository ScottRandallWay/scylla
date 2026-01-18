package frc.robot;

public final class SpeedController {

  // possition values
  double target;          // desired position
  double slowZone;        // offset when to deccelerate
  double targetZone;      // offset from taget considered on the mark 
  int holdCount;          // count of interations in targetZone
  int targetCount;        // count of times inside targetZone
  
  // power settings
  double maxSpeed;        // maximum speed allowed
  double minSpeed;        // minimum speed allowed
  double accelRate;       // rate at which to decelerate

  public SpeedController(double maxSpeed, double minSpeed, double target, double slowZone, double targetZone, int holdCount) {
    
    // set local member variables
    this.minSpeed = minSpeed;
    this.maxSpeed = maxSpeed;
    this.target = target;
    this.slowZone = slowZone;
    this.targetZone = targetZone;
    this.holdCount = holdCount;
    this.targetCount = 0;

    // compute acceleration rate
    if (slowZone > 0) {
      accelRate = (maxSpeed - minSpeed) / slowZone; 
    } else {
      accelRate = 0;
    }
  }

  public double calculate(double position) {
    
    // compute offset from target
    double error = target - position;        

    // are we in the target zone?
    if (Math.abs(error) < targetZone) {
      targetCount += 1;
    } else {
      targetCount = 0;
    }

    // we are on the mark?
    if (holdCount > 0) {
      if (targetCount > holdCount) return 0;
    }

    // compute speed
    double speed = maxSpeed;
    if (Math.abs(error) < slowZone) {
      speed = (Math.abs(error) * accelRate) + minSpeed;
    }
    if (error < 0) speed *= -1;     
      
    return speed;

  }

  public void reset() {
    targetCount = 0;
  }

  public boolean isAligned() {
    return (targetCount > holdCount);    
  }
  
}
