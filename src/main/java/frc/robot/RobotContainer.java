package frc.robot;


//import frc.robot.commands.Autos;
import frc.robot.subsystems.AlgaeGrabberSubsystem;
import frc.robot.subsystems.ArduinoLedSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.Constants.JoystickChannels;
import frc.robot.Constants.ButtonIndex;
import frc.robot.commands.AlgaeEjectCommand;
import frc.robot.commands.AlgaeGrabCommand;
import frc.robot.commands.AlgaeRaiseCommand;
import frc.robot.Constants.PnuematicChannels;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticHub;
//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

  private final AlgaeGrabberSubsystem algaeGrabberSub;
  private final ClimberSubsystem climberSub;
  private final ArduinoLedSubsystem arduinoLedSub;
  private final Joystick operatorRightJoystick;
  private final PneumaticHub hub;

  public RobotContainer() {

    // init
    // Settings.Init();
    // Settings.Reset();
        
    // create subsystems
    hub = new PneumaticHub(PnuematicChannels.PNEUMATIC_HUB_MODULE);
    hub.enableCompressorDigital();
    algaeGrabberSub = new AlgaeGrabberSubsystem(hub);
    climberSub = new ClimberSubsystem(hub);
    arduinoLedSub = new ArduinoLedSubsystem();

    // create joysticks
    operatorRightJoystick = new Joystick(JoystickChannels.OPERATOR_RIGHT_JOYSTICK);

    // map controls to commands
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(operatorRightJoystick, ButtonIndex.OperatorRight.ALGAE_GRAB_BUTTON)
      .onTrue(new AlgaeGrabCommand(algaeGrabberSub));
    new JoystickButton(operatorRightJoystick, ButtonIndex.OperatorRight.ALGAE_SET_BUTTON)
      .onTrue(new AlgaeEjectCommand(algaeGrabberSub));
    new JoystickButton(operatorRightJoystick, ButtonIndex.OperatorRight.ALGAE_RAISE_BUTTON)
      .onTrue(new AlgaeRaiseCommand(algaeGrabberSub));
  }

  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous

  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
