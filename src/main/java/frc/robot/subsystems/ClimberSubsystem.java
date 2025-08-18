package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import frc.robot.Dashboard;
import frc.robot.Constants.MotorPorts;
import frc.robot.Constants.PnuematicChannels;

public class ClimberSubsystem extends SubsystemBase {

  private TalonSRX talon;
  private DoubleSolenoid solenoid;
  private GenericEntry doorEntry;
  
  public ClimberSubsystem(PneumaticHub hub) {
    solenoid = hub.makeDoubleSolenoid(PnuematicChannels.CLIMBER_FORWARD, PnuematicChannels.CLIMBER_REVERSE);
    talon = new TalonSRX(MotorPorts.CLIMBER_DOOR);
    Raise();
    setTalonConfig();   
    doorEntry = Dashboard.getDoorEntry();
  }
   
  public void Lower() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void Raise() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }
    
  public void SetSpeed(double speed) {    
    talon.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public double GetPostion() {
    double position = talon.getSelectedSensorPosition(0);
    doorEntry.setDouble(position);
    return position;
  }

  public void setTalonConfig() {
    talon.setSensorPhase(true);
    talon.setSelectedSensorPosition(0);
  }

}
