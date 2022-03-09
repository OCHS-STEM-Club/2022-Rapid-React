package frc.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Potentiometer {

    private AnalogInput input = new AnalogInput(0);
    private AnalogPotentiometer hoodPotentiometer = new AnalogPotentiometer(input);
    private VictorSPX hoodMotor = new VictorSPX(11);
    private XboxController controller = new XboxController(1);
    //public double angle = hoodPotentiometer.get();

    public void hoodPotentiometer(){
        input.setAverageBits(2);
        SmartDashboard.putNumber("Hood Angle", hoodPotentiometer.get());
      }

      public void hoodMotor() {
        if (controller.getRawAxis(2) > 0){
          hoodMotor.set(ControlMode.PercentOutput, 0.3);
        }else if(controller.getRawAxis(3) > 0){
          hoodMotor.set(ControlMode.PercentOutput, -0.3);
        }else {
          hoodMotor.set(ControlMode.PercentOutput, 0);
        }
      }


        public void hoodMotor2(){
        if (controller.getRawButton(4) && hoodPotentiometer.get() < 0.3) {
          hoodMotor.set(ControlMode.PercentOutput, -0.3);
        }
      }

        public void hoodMotor3() {
        if (controller.getRawButton(3) && hoodPotentiometer.get() > 0.03) {
          hoodMotor.set(ControlMode.PercentOutput, 0.3);
        }
        }
    }

      /*public void setHood() {
        if (controller.getRawButton(4) && hoodPotentiometer.get() < 0.4) {
          hoodMotor.set(ControlMode.PercentOutput, -0.4);
        }
      } */
      
    
      
      
  
    

 