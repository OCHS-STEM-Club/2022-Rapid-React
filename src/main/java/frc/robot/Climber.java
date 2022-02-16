package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;

public class Climber {
    
    private XboxController controller = new XboxController(1);
    private DoubleSolenoid climberSolonoid = new DoubleSolenoid(2,PneumaticsModuleType.REVPH, 0,1);
    private DoubleSolenoid climberSolonoid2 = new DoubleSolenoid(2,PneumaticsModuleType.REVPH, 2,3); 
    public void setSpeedSoleniod(DoubleSolenoid climberSoleniod) {
        this.climberSolonoid = climberSoleniod;
    }

    public void climberControl(){
        if(controller.getRawButton(1)){
            climberSolonoid.set(Value.kForward);
            climberSolonoid2.set(Value.kForward);
        }else if(controller.getRawButton(2)){
            climberSolonoid.set(Value.kReverse);
            climberSolonoid2.set(Value.kReverse);
        }else {climberSolonoid.set(Value.kOff);
               climberSolonoid2.set(Value.kOff);}
    
}
}