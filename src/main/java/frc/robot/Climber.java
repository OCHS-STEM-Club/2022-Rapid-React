package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;

public class Climber {
    
    private XboxController controller = new XboxController(1);
    private DoubleSolenoid climberSolenoid = new DoubleSolenoid(13,PneumaticsModuleType.REVPH, 0,1);
    private DoubleSolenoid climberSolenoid2 = new DoubleSolenoid(13,PneumaticsModuleType.REVPH, 2,3); 
    public void setSpeedSoleniod(DoubleSolenoid climberSoleniod) {
        this.climberSolenoid = climberSoleniod;
    }

    public void climberControl(){
        if(controller.getRawButton(1)){
            climberSolenoid.set(Value.kReverse);
            climberSolenoid2.set(Value.kReverse);
        }else if(controller.getRawButton(2)){
            climberSolenoid.set(Value.kForward);
            climberSolenoid2.set(Value.kForward);
        }
        else {climberSolenoid.set(Value.kOff);
              climberSolenoid2.set(Value.kOff);} 
    }

}