package frc.robot;


//import frc.robot.commands.Autos;
import frc.robot.subsystems.AlgaeGrabberSubsystem;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.Constants.JoystickChannels;
import frc.robot.Constants.ButtonIndex;
import frc.robot.commands.AlgaeEjectCommand;
import frc.robot.commands.AlgaeGrabCommand;
import frc.robot.commands.AlgaeRaiseCommand;
import frc.robot.commands.LedBallCommand;
import frc.robot.commands.LedFlashCommand;
import frc.robot.Constants.PnuematicChannels;
import frc.robot.Constants.TimeConstants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

  private final AlgaeGrabberSubsystem algaeGrabberSub;
  private final ClimberSubsystem climberSub;
  private final LedSubsystem ledSub;
  private final Joystick operatorRightJoystick;
  private final PneumaticHub hub;
  private final Trigger endgameTrigger;

  public RobotContainer() {

    // pnuematics
    hub = new PneumaticHub(PnuematicChannels.PNEUMATIC_HUB_MODULE);
    hub.enableCompressorDigital();

    // subsystems
    algaeGrabberSub = new AlgaeGrabberSubsystem(hub);
    climberSub = new ClimberSubsystem(hub);
    ledSub = new LedSubsystem();

    // triggers
    // endgameTrigger = new Trigger(() -> DriverStation.getMatchTime() <= 30);
    endgameTrigger = new Trigger(DriverStation::isTeleopEnabled);
    //.onTrue(Commands.wait(10).andThen(FooCommand));

    // create joysticks
    operatorRightJoystick = new Joystick(JoystickChannels.OPERATOR_RIGHT_JOYSTICK);

    // map controls to commands
    configureAlgaeBindings();
    configureLedBindings();
  }

  private void configureAlgaeBindings() {
    new JoystickButton(operatorRightJoystick, ButtonIndex.OperatorRight.ALGAE_GRAB_BUTTON)
      .onTrue(Commands.sequence(
          new AlgaeGrabCommand(algaeGrabberSub), 
          new LedBallCommand(ledSub, algaeGrabberSub)));
    new JoystickButton(operatorRightJoystick, ButtonIndex.OperatorRight.ALGAE_SET_BUTTON)
      .onTrue(Commands.sequence(
         new AlgaeEjectCommand(algaeGrabberSub),
         new LedBallCommand(ledSub, algaeGrabberSub)));
    new JoystickButton(operatorRightJoystick, ButtonIndex.OperatorRight.ALGAE_RAISE_BUTTON)
      .onTrue(new AlgaeRaiseCommand(algaeGrabberSub));
  }

    private void configureLedBindings() {
      var seconds = TimeConstants.TELEOP_SECONDS - Settings.getEndGameSeconds();
      endgameTrigger.onTrue(Commands.waitSeconds(seconds).andThen(new LedFlashCommand(ledSub)));
    }

  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous

  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
