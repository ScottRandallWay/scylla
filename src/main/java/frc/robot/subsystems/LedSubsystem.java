package frc.robot.subsystems;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LedSubsystem extends SubsystemBase {

  private final I2C arduino;
  private int allianceColor;
  private int currentColor;
  private int mode;

  // local constants
  private static final int ADDRESS_STATUS = 0;
  private static final int ADDRESS_COLOR = 1;
  private static final int SOLID_MODE = 0;
  private static final int FLASHING_MODE = 30;

  // color constants
  public static final class Color {
    public static final int BLUE = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int WHITE = 12;
  }

  public LedSubsystem() {
    arduino = new I2C(I2C.Port.kOnboard, 0x08);
    setAllianceColor();
    mode = SOLID_MODE;
    setColor(allianceColor);
  }

  private void setAllianceColor() {
    allianceColor = Color.BLUE;
    Optional<Alliance> ally = DriverStation.getAlliance();
    if (ally.isPresent()) {
        if (ally.get() == Alliance.Red) {
            allianceColor = Color.RED;
        }
    }
  }

  public void setFlashing(boolean flash) {
    if (flash) {
      mode = FLASHING_MODE;
    } else {
      mode = SOLID_MODE;
    }
    arduino.write(ADDRESS_COLOR, currentColor + mode);
  }

  public void setColor(int color) {
    currentColor = color;
    arduino.write(ADDRESS_COLOR, color + mode);
  }

  public void setStatus(int status) {
    arduino.write(ADDRESS_STATUS, status + mode);
  }

  public void resetColor() {
    currentColor = allianceColor;
    arduino.write(ADDRESS_COLOR, allianceColor + mode);
  }

}
