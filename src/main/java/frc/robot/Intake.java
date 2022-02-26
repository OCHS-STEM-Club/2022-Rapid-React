package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.XboxController;


public class Intake {

    private CANSparkMax intakeMotor = new CANSparkMax(8, MotorType.kBrushless);
    private XboxController controller = new XboxController(1);
    private CANSparkMax intakeLiftMotor = new CANSparkMax(10, MotorType.kBrushless);


public void intakeController(){
    // if bumpers are pressed is pressed move the motor else don't move it
    if(controller.getRawButton(5)){
        intakeMotor.set(0.9);
    }
    else if(controller.getRawButton(6)) {
        intakeMotor.set(-0.9);

    }else{
        intakeMotor.set(0);
    }
   
}    

public void intakeAuto(double x){
    intakeMotor.set(x);
  }


public void intakeUpDown(){
    // Press A and B to make intake go up and down. Max speed 0.3 currently.   
    if(controller.getRawButton(1) && intakeLiftMotor.getOutputCurrent() < 15){
        intakeLiftMotor.set(-0.3);
    }
    else if(controller.getRawButton(2) && intakeLiftMotor.getOutputCurrent() < 15) {
        intakeLiftMotor.set(0.3);

    }else{
        intakeLiftMotor.set(0);
    }
   
}

public void intakeLiftMotorAuto(double x) {
    intakeLiftMotor.set(x);
}


}



