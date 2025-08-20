package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Dashboard;
import frc.robot.Settings;
import frc.robot.Constants.AnalogChannels;
import frc.robot.Constants.MotorPorts;
import frc.robot.Constants.PnuematicChannels;

public class AlgaeGrabberSubsystem extends SubsystemBase {

  private TalonSRX talon;
  private DoubleSolenoid solenoid;
  private AnalogInput sensor;
  private double algaeDetectThreshold;
  private GenericEntry sensorEntry;
  
  public AlgaeGrabberSubsystem(PneumaticHub hub) {
    algaeDetectThreshold = Settings.getAlgaeDetectThreshold();
    talon = new TalonSRX(MotorPorts.ALGAE_GRABBER);
    sensor = new AnalogInput(AnalogChannels.BALL_SENSOR);
    solenoid = hub.makeDoubleSolenoid(PnuematicChannels.ALGAE_GRABBER_FORWARD, PnuematicChannels.ALGAE_GRABBER_REVERSE); 
    sensorEntry = Dashboard.getAlgaeEntry(); 
  }

  public void setSpeed(double speed) {
    talon.set(ControlMode.PercentOutput, speed);
  }
   
  public void lower() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void raise() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  public double getSensorVoltage() {
    double sensorVoltage = sensor.getVoltage();
    sensorEntry.setDouble(sensorVoltage);
    return sensorVoltage;  
  }

  public boolean isBallCaputured() {
    if (getSensorVoltage() > algaeDetectThreshold) {
      return true;
    } else {
      return false;
    }
  }

}
