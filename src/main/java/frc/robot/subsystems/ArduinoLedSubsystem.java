package frc.robot.subsystems;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArduinoLedSubsystem extends SubsystemBase {

  private final I2C arduino;
  private int allianceColor;
  private static final int ADDRESS_STATUS = 0;
  private static final int ADDRESS_COLOR = 1;

  public enum Color {
    OFF, 
    BLUE,
    RED,
    GREEN,
    CYAN,
    MAGENTA,
    YELLOW,
    TURQUOISE,
    ORANGE,
    RASBERRY,
    RAINBOW
  }

  public ArduinoLedSubsystem() {
    arduino = new I2C(I2C.Port.kOnboard, 0x08);
    allianceColor = Color.BLUE.ordinal();
    Optional<Alliance> ally = DriverStation.getAlliance();
    if (ally.isPresent()) {
        if (ally.get() == Alliance.Red) {
            allianceColor = Color.RED.ordinal();
        }
    }
  }

  public void setColor(int color) {
    arduino.write(ADDRESS_COLOR, color);
  }

  public void setStatus(int status) {
    arduino.write(ADDRESS_STATUS, status);
  }

  public void resetColor() {
    arduino.write(ADDRESS_COLOR, allianceColor);
  }

}
