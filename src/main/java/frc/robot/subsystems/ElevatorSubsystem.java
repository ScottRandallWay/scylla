package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants.DashboardKeys;
import frc.robot.Constants.MotorPorts;

public class ElevatorSubsystem extends SubsystemBase {

  private TalonFX talon;
  
  public ElevatorSubsystem() {
    talon = new TalonFX(MotorPorts.ELEVATOR_MOTOR);
    setTalonConfig();     
  }
    
  public void SetSpeed(double speed) {   
    talon.set(speed);
  }

  public double GetPostion() {
    var signal = talon.getRotorPosition();
    double position = signal.getValueAsDouble();
    SmartDashboard.putNumber(DashboardKeys.ELEVATOR_POSITION, position);
    return position;
  }

  public void setTalonConfig() {
    talon.setPosition(0.0);
    SmartDashboard.putNumber(DashboardKeys.ELEVATOR_POSITION, 0.0);
  }

}
