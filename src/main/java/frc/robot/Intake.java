package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;



public class Intake {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;


    private CANSparkMax intakeMotor = new CANSparkMax(8, MotorType.kBrushless);
    private ColorSensorV3 intakeColorSensor = new ColorSensorV3(i2cPort);
    private XboxController controller = new XboxController(1);

public void intakeController(){
    // if bumpers are pressed is pressed move the motor else don't move it
    if(controller.getRawButton(5)){
        intakeMotor.set(0.5);
    }
    else if(controller.getRawButton(6)) {
        intakeMotor.set(-0.5);
    }else{
        intakeMotor.set(0);
    }
   
}    

public double intakeColorSensor(){

    Color intakeCargoColor = intakeColorSensor.getColor();

    SmartDashboard.putNumber("Red", intakeCargoColor.red);
    SmartDashboard.putNumber("Blue", intakeCargoColor.blue);

    
    

    return 0;
    
}


    
}
