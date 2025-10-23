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
  private static final int ENABLED = 1;
  private static final int DISABLED = 0;
  private static final int SOLID_MODE = 0;
  private static final int FLASHING_MODE = 30;

  // color constants
  public static final class Color {
    public static final int BLUE = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int WHITE = 12;
    public static final int DEFAULT = 21;
  }

  public LedSubsystem() {
    arduino = new I2C(I2C.Port.kOnboard, 0x08);
    arduino.write(ADDRESS_STATUS, ENABLED);
    setEnabled(true);
    setAllianceColor();
    mode = SOLID_MODE;
    setColor(Color.DEFAULT);
  }

  public void setEnabled(boolean Enabled) {
    if (Enabled == true) {
      arduino.write(ADDRESS_STATUS, ENABLED);
    }
    else
    {
      arduino.write(ADDRESS_STATUS, DISABLED);
    }
  }

  public void setAllianceColor() {
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
    setColor(this.currentColor);
  }

  public void setColor(int color) {
    currentColor = color;
    //if (color != Color.DEFAULT) {
      color = color + mode;
    //}
    arduino.write(ADDRESS_COLOR, color);
  }

  public void resetColor() {
    setColor(allianceColor);
  }

}
