package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import frc.robot.Constants.MotorPorts;
import frc.robot.Constants.PnuematicChannels;

public class ClimberSubsystem extends SubsystemBase {

  private TalonSRX talon;
  private DoubleSolenoid solenoid;
    
  public ClimberSubsystem(PneumaticHub hub) {
    talon = new TalonSRX(MotorPorts.CLIMBER_DOOR);
    solenoid = hub.makeDoubleSolenoid(PnuematicChannels.CLIMBER_FORWARD, PnuematicChannels.CLIMBER_REVERSE);  
    Raise();
  }
   
  public void Lower() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void Raise() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }
    
  public void SetMotor(double speed) {    
    talon.set(TalonSRXControlMode.PercentOutput, speed);
  }

  

}
