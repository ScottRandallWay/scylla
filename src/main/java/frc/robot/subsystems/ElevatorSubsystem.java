package frc.robot.subsystems;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;

import static edu.wpi.first.units.Units.Second;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants.DashboardKeys;
import frc.robot.Constants.MotorPorts;

public class ElevatorSubsystem extends SubsystemBase {

  private TalonFX talon;
  private final MotionMagicVoltage motionMagicRequest;
  
  public ElevatorSubsystem() {
    talon = new TalonFX(MotorPorts.ELEVATOR_MOTOR);
    motionMagicRequest = new MotionMagicVoltage(0);
    SetConfiguration();
    ResetPositoion();  
  }
    
  public void SetSpeed(double speed) {   
    talon.set(speed);
  }

  public double GetPostion() {
    var signal = talon.getPosition();
    double position = signal.getValueAsDouble();
    SmartDashboard.putNumber(DashboardKeys.ELEVATOR_POSITION, position);
    return position;
  }

  public void ResetPositoion() {
    talon.setPosition(0.0);
    SmartDashboard.putNumber(DashboardKeys.ELEVATOR_POSITION, 0.0);
  }

  public void SetPosition(double position) {
    talon.setControl(motionMagicRequest.withPosition(position));
  }

  private void SetConfiguration() {

    var magicMotionConfig = new MotionMagicConfigs()
      .withMotionMagicAcceleration(200)
      .withMotionMagicCruiseVelocity(75);

    var motorOutputConfig = new MotorOutputConfigs()
      .withInverted(InvertedValue.CounterClockwise_Positive)
      .withNeutralMode(NeutralModeValue.Brake);
    
    var slot0Config = new Slot0Configs()
      .withGravityType(GravityTypeValue.Elevator_Static)
      .withKA(0)
      .withKG(0.075)
      .withKP(4.0)
      .withKI(0.15)
      .withKS(0.0)
      .withKV(0.0)
      .withKD(0.02);

    var talonFXConfig = new TalonFXConfiguration()
      .withMotorOutput(motorOutputConfig)
      .withSlot0(slot0Config)
      .withMotionMagic(magicMotionConfig);

    talon.getConfigurator().apply(talonFXConfig);
  }

}
