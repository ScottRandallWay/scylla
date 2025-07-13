package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import frc.robot.Constants.DashboardKeys;
import frc.robot.Constants.MotorPorts;

public class ElevatorSubsystem extends SubsystemBase {

  private TalonSRX talon;
  
  public ElevatorSubsystem() {
    talon = new TalonSRX(MotorPorts.ELEVATOR_MOTOR);
    setTalonConfig();     
  }
    
  public void SetSpeed(double speed) {   
    talon.set(TalonSRXControlMode.PercentOutput, speed);
  }

  //public void SetPostion(double pos) {
  //  talon.set(TalonSRXControlMode.Position, pos);
  //}

  public double GetPostion() {
    double position = talon.getSelectedSensorPosition(0);
    SmartDashboard.putNumber(DashboardKeys.DOOR_POSITION, position);
    return position;
  }

  public void setTalonConfig() {
    // *** playing with PID controller
    // TalonSRXConfiguration config = new TalonSRXConfiguration();
    // config.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
    // config.slot0.integralZone = 2000;
    // config.slot0.kP = 0.05; // Proportional gain
    // config.slot0.kI = 0.00025; // Integral gain
    // config.slot0.kD = 5.0; // Derivative gain
    // config.slot0.kF = 0.0; // Feedforward gain 
    // talon.configAllSettings(config);
    
    talon.setSensorPhase(true);
    talon.setSelectedSensorPosition(0);
    SmartDashboard.putNumber(DashboardKeys.DOOR_POSITION, 0);
  }

}
