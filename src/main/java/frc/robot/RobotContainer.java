package frc.robot;


//import frc.robot.commands.Autos;
import frc.robot.subsystems.AlgaeGrabberSubsystem;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.Constants.JoystickChannels;
import frc.robot.Constants.ButtonIndex;
import frc.robot.commands.AlgaeEjectCommand;
import frc.robot.commands.AlgaeGrabCommand;
import frc.robot.commands.AlgaeRaiseCommand;
import frc.robot.commands.ClimbSetCommand;
import frc.robot.commands.ClimbDownCommand;
import frc.robot.commands.ClimbMoveCommand;
import frc.robot.commands.ClimbUpCommand;
import frc.robot.commands.CoralSetCommand;
import frc.robot.commands.ElevatorMoveCommand;
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
  private final ElevatorSubsystem elevatorSub;
  private final CoralSubsystem coralSub;
  private final Joystick operatorLeftStick;
  private final Joystick operatorRightStick;
  private final Joystick driverLeftStick;
  private final Joystick driverRightStick;
  private final PneumaticHub hub;
  private final Trigger startTeleopTrigger;
  private final Trigger endgameTrigger;

  public RobotContainer() {

    // init pnuematics
    hub = new PneumaticHub(PnuematicChannels.PNEUMATIC_HUB_MODULE);
    hub.enableCompressorDigital();

    // init subsystems
    algaeGrabberSub = new AlgaeGrabberSubsystem(hub);
    climberSub = new ClimberSubsystem(hub);
    ledSub = new LedSubsystem();
    elevatorSub = new ElevatorSubsystem();
    coralSub = new CoralSubsystem();

    // init triggers
    startTeleopTrigger = new Trigger(DriverStation::isTeleopEnabled);
    endgameTrigger = new Trigger(DriverStation::isTeleopEnabled);

    // initi create joysticks
    operatorLeftStick = new Joystick(JoystickChannels.OPERATOR_LEFT_JOYSTICK);
    operatorRightStick = new Joystick(JoystickChannels.OPERATOR_RIGHT_JOYSTICK);
    driverLeftStick = new Joystick(JoystickChannels.DRIVER_LEFT_JOYSTICK);
    driverRightStick = new Joystick(JoystickChannels.DRIVER_RIGHT_JOYSTICK);

    // map controls to commands
    configureAlgaeBindings();
    configureLedBindings();
    configureClimberBindings();
    configureElevatorBindings();
  }

  private void configureAlgaeBindings() {
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ALGAE_GRAB_BUTTON)
      .onTrue(
        Commands.sequence(
          new AlgaeGrabCommand(algaeGrabberSub), 
          new LedBallCommand(ledSub, algaeGrabberSub)
        )
      );
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ALGAE_SET_BUTTON)
      .onTrue(
        Commands.sequence(
          new AlgaeEjectCommand(algaeGrabberSub),
          new LedBallCommand(ledSub, algaeGrabberSub)
        )
      );
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ALGAE_RAISE_BUTTON)
      .onTrue(new AlgaeRaiseCommand(algaeGrabberSub));
  }

  private void configureLedBindings() {
    int seconds = TimeConstants.TELEOP_SECONDS - Settings.getEndGameSeconds();
    endgameTrigger.onTrue(Commands.waitSeconds(seconds).andThen(new LedFlashCommand(ledSub, true)));
    startTeleopTrigger.onTrue(new LedFlashCommand(ledSub, false));
  }

  private void configureClimberBindings() {
    climberSub.setDefaultCommand(new ClimbMoveCommand(climberSub, () -> operatorRightStick.getX()));
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.DOOR_OPEN_BUTTON)
      .onTrue(new ClimbSetCommand(climberSub, false));
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.DOOR_CLOSE_BUTTON)
      .onTrue(new ClimbSetCommand(climberSub, true));
      new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.CLIMB_UP_BUTTON)
      .onTrue(new ClimbUpCommand(climberSub));
      new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.CLIMB_DOWN_BUTTON)
      .onTrue(new ClimbDownCommand(climberSub));
  }

  private void configureElevatorBindings(){
    elevatorSub.setDefaultCommand(new ElevatorMoveCommand(elevatorSub, () -> operatorLeftStick.getY()));
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.CORAL_IN_BUTTON)
      .whileTrue(new CoralSetCommand(coralSub, false));
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.CORAL_OUT_BUTTON)
      .whileTrue(new CoralSetCommand(coralSub, true));
  }

  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous

  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
