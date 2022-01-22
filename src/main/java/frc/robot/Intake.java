package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.XboxController;


public class Intake {
    private CANSparkMax intakeMotor = new CANSparkMax(8, MotorType.kBrushless);
    private XboxController controller = new XboxController(1);

public void intakeController(){
    if(controller.getRawButton(2)){
        intakeMotor.set(0.5);
    }
    else if(controller.getRawButton(3)) {
        intakeMotor.set(-0.5);
    }else{
        intakeMotor.set(0);
    }
   
}    



    
}
