package frc.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Potentiometer {

    private AnalogInput input = new AnalogInput(0);
    private AnalogPotentiometer hoodPotentiometer = new AnalogPotentiometer(input);

    public void hoodPotentiometer(){
        input.setAverageBits(2);
        double angle = hoodPotentiometer.get();
        SmartDashboard.putNumber("Hood Angle", angle);
  
        
      }
  
    
}
