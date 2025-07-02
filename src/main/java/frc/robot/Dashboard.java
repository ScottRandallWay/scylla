package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class Dashboard {
  
  public static final class Keys {
    public static final String ALGAE_VOLT_KEY = "Algae Voltage";
  }
 
  public static void init() {
    SmartDashboard.setDefaultNumber(Keys.ALGAE_VOLT_KEY, 0);    
  }

}
