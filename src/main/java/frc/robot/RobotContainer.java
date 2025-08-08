package frc.robot;

//import frc.robot.commands.Autos;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import static edu.wpi.first.units.Units.*;
import frc.robot.subsystems.AlgaeGrabberSubsystem;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.Constants.JoystickChannels;
import frc.robot.Constants.ButtonIndex;
import frc.robot.commands.AlgaeEjectCommand;
import frc.robot.commands.AlgaeGrabCommand;
import frc.robot.commands.AlgaeToggleCommand;
import frc.robot.commands.ClimbMoveCommand;
import frc.robot.commands.CoralSetCommand;
import frc.robot.commands.CoralShootCommand;
import frc.robot.commands.ElevatorGoCommand;
import frc.robot.commands.ElevatorMoveCommand;
import frc.robot.commands.ElevatorSetCommand;
import frc.robot.commands.LedBallCommand;
import frc.robot.commands.LedFlashCommand;
import frc.robot.Constants.PnuematicChannels;
import frc.robot.Constants.TimeConstants;
import frc.robot.Constants.ButtonIndex.DriverLeft;
import frc.robot.Constants.ButtonIndex.DriverRight;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;

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
  private double MaxSpeed;
  private double MaxAngularRate;
  private final SwerveRequest.FieldCentric drive;
  private final SwerveRequest.RobotCentric strafe;
  private final SwerveRequest.SwerveDriveBrake brake;
  private final SwerveRequest.PointWheelsAt point;
  private final Telemetry logger;
  public final CommandSwerveDrivetrain drivetrain;

  public RobotContainer() {

    // swerve system
    MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity
    drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); 
    strafe = new SwerveRequest.RobotCentric()
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
    brake = new SwerveRequest.SwerveDriveBrake();
    point = new SwerveRequest.PointWheelsAt();
    logger = new Telemetry(MaxSpeed);
    drivetrain = TunerConstants.createDrivetrain();

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
    configureSwerveBindings();
    configureAlgaeBindings();
    configureLedBindings();
    configureClimberBindings();
    configureElevatorBindings();
    configureCoralBindings();
  }

  // check for precision or turbo mode
  private double getSpeedFactor() {
    double speedFactor = 1;
    boolean turbo = false;
    boolean precision = false;
    if (driverRightStick.getRawButton(DriverRight.PRECISION_MODE_BUTTON)) {
      precision = true;
      speedFactor =  Settings.getSwervePrecisionFactor();
    } else if (!driverLeftStick.getRawButton(DriverLeft.TURBO_MODE_BUTTON)) {
      turbo = true;
      speedFactor = Settings.getSwerveSpeedFactor();
    } 
    SmartDashboard.putBoolean("Precision", precision);
    SmartDashboard.putBoolean("Turbo", turbo);
    return MaxSpeed * speedFactor;
  }

  private void configureSwerveBindings() {
      
      double speedFactor = getSpeedFactor();
      drivetrain.setDefaultCommand(
          drivetrain.applyRequest(() ->
              drive
                .withVelocityX(-driverLeftStick.getY() * speedFactor) 
                .withVelocityY(-driverLeftStick.getX() * speedFactor) 
                .withRotationalRate(-driverRightStick.getZ() * speedFactor) 
          )
      );

      final var idle = new SwerveRequest.Idle();
      RobotModeTriggers.disabled().whileTrue(
          drivetrain.applyRequest(() -> idle).ignoringDisable(true)
      );
      drivetrain.registerTelemetry(logger::telemeterize);

      // pigeon reset
      new JoystickButton(driverRightStick, 4).onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

      // strafe left
      new JoystickButton(driverLeftStick, 3).whileTrue(drivetrain.applyRequest(() -> strafe
        .withVelocityX(0)
        .withVelocityY(0.1)
        .withRotationalRate(0)));

      // strafe right
      new JoystickButton(driverLeftStick, 4).whileTrue(drivetrain.applyRequest(() -> strafe
        .withVelocityX(0)
        .withVelocityY(-0.1)
        .withRotationalRate(0)));
  }

  private void configureAlgaeBindings() {
    
    // grab algae
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ALGAE_GRAB_BUTTON)
      .onTrue(
        Commands.sequence(
          new AlgaeGrabCommand(algaeGrabberSub), 
          new LedBallCommand(ledSub, algaeGrabberSub)
        )
      );

    // eject algae
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ALGAE_SET_BUTTON)
      .onTrue(
        Commands.sequence(
          new AlgaeEjectCommand(algaeGrabberSub),
          new LedBallCommand(ledSub, algaeGrabberSub)
        )
      );
    
    // raise arm
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ALGAE_RAISE_BUTTON)
      .onTrue(new AlgaeToggleCommand(algaeGrabberSub, true));
    
    // lower arm
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ALGAE_LOWER_BUTTON)
      .onTrue(new AlgaeToggleCommand(algaeGrabberSub, false));  
  }

  private void configureLedBindings() {

    // make lights flash during end game
    int seconds = TimeConstants.TELEOP_SECONDS - Settings.getEndGameSeconds();
    endgameTrigger.onTrue(Commands.waitSeconds(seconds).andThen(new LedFlashCommand(ledSub, true)));
    startTeleopTrigger.onTrue(new LedFlashCommand(ledSub, false));

  }

  private void configureClimberBindings() {

    // manual door movement
    climberSub.setDefaultCommand(new ClimbMoveCommand(climberSub, () -> operatorRightStick.getX(),
        () -> operatorRightStick.getRawButton(ButtonIndex.OperatorRight.OVERRIDE_BUTTON)));
            
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.DOOR_CLOSE_BUTTON)
      .onTrue(Commands.sequence(
        new ElevatorSetCommand(elevatorSub, 4),
        new CoralShootCommand(coralSub),
        new ElevatorSetCommand(elevatorSub, 7)
      ));

    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.DOOR_OPEN_BUTTON)
      .onTrue(Commands.sequence(
        new ElevatorSetCommand(elevatorSub, 3),
        new CoralShootCommand(coralSub),
        new ElevatorSetCommand(elevatorSub, 7)
      ));      

    // lower piston
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.CLIMB_UP_BUTTON)
      .onTrue(new RunCommand(() -> climberSub.Lower(), climberSub));
    
    // raise piston
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.CLIMB_DOWN_BUTTON)
      .onTrue(new RunCommand(() -> climberSub.Raise(), climberSub));
  }

  private void configureElevatorBindings(){
    
    // manaul elevator movement
    elevatorSub.setDefaultCommand(new ElevatorMoveCommand(elevatorSub, () -> operatorLeftStick.getY(), 
        () -> operatorRightStick.getRawButton(ButtonIndex.OperatorRight.OVERRIDE_BUTTON)));
    
    // home position
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ELEVATOR_HOME)
      .onTrue(new ElevatorSetCommand(elevatorSub, 0));

    // PID level 3
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.ELEVATOR_LEVEL1)
      .onTrue(new ElevatorGoCommand(elevatorSub, 3));
    
    // level 2
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.ELEVATOR_LEVEL2)
      .onTrue(new ElevatorSetCommand(elevatorSub, 2));

    // level 3  
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.ELEVATOR_LEVEL3)
      .onTrue(new ElevatorSetCommand(elevatorSub, 3));

    // level 4
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.ELEVATOR_LEVEL4)
      .onTrue(new ElevatorSetCommand(elevatorSub, 4));

    // algae high  
    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.ALGAE_HIGH_BUTTON)
      .onTrue(new ElevatorSetCommand(elevatorSub, 5));  

    // algae low  
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ALGAE_LOW_BUTTON)
      .onTrue(new ElevatorSetCommand(elevatorSub, 6));      

    new JoystickButton(operatorLeftStick, ButtonIndex.OperatorLeft.HOME_TRAVEL_BUTTON)
      .onTrue(new ElevatorSetCommand(elevatorSub, 7));

    // reset position  
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.ELEVATOR_RESET_BUTTON)
      .onTrue(new RunCommand(() -> elevatorSub.ResetPositoion(), elevatorSub));
    
  }

  private void configureCoralBindings() {

    // pull coral in
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.CORAL_IN_BUTTON)
      .whileTrue(new CoralSetCommand(coralSub, false));
    
    // eject coral
    new JoystickButton(operatorRightStick, ButtonIndex.OperatorRight.CORAL_OUT_BUTTON)
      .whileTrue(new CoralSetCommand(coralSub, true));

  }

  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous

  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
