package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import frc.robot.Constants.MotorPorts;

public class CoralSubsystem extends SubsystemBase {

  private TalonSRX talon;
  
  public CoralSubsystem() {
    talon = new TalonSRX(MotorPorts.CORAL_MOTOR);     
  }
    
  public void SetSpeed(double speed) {    
    talon.set(TalonSRXControlMode.PercentOutput, speed);
  }

}
