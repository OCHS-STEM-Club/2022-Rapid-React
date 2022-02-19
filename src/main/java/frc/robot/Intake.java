package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.XboxController;


public class Intake {

    private CANSparkMax intakeMotor = new CANSparkMax(8, MotorType.kBrushless);
    private XboxController controller = new XboxController(1);


public void intakeController(){
    // if bumpers are pressed is pressed move the motor else don't move it
    if(controller.getRawButton(5)){
        intakeMotor.set(0.3);
    }
    else if(controller.getRawButton(6)) {
        intakeMotor.set(-0.3);

    }else{
        intakeMotor.set(0);
    }
   
}    

public void intakeAuto(double x){
    intakeMotor.set(x);
  }


}

