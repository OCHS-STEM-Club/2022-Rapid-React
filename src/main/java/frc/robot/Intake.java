package frc.robot;

import javax.swing.text.StyleConstants.ColorConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorSensorV3.ColorSensorMeasurementRate;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;



public class Intake {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;


    private CANSparkMax intakeMotor = new CANSparkMax(8, MotorType.kBrushless);
    private final ColorSensorV3 intakeColorSensor = new ColorSensorV3(i2cPort);
    private XboxController controller = new XboxController(1);
    private final ColorMatch intakeColorMatch = new ColorMatch();

    private final Color blueCargoTarget = new Color(0.555, 0.555, 0.555);
    private final Color redCargoTarget = new Color(0.5, 0.5, 0.5);

    
    


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

public void setupColor(){    
    intakeColorMatch.addColorMatch(blueCargoTarget);
    intakeColorMatch.addColorMatch(redCargoTarget);

}

public void intakeColorSensor(){

   // Color intakeCargoColor = intakeColorSensor.getColor();

    //SmartDashboard.putNumber("Red", intakeCargoColor.red);
    //SmartDashboard.putNumber("Blue", intakeCargoColor.blue);
    
    //Match color:

    Color detectColor = intakeColorSensor.getColor();

    String colorString;
    ColorMatchResult match = intakeColorMatch.matchClosestColor(detectColor);

    if (match.color == blueCargoTarget)
        colorString = "Blue";
    else if (match.color == redCargoTarget)
        colorString = "Red";
    else
        colorString = "Unknown";

    SmartDashboard.putString("Color", colorString);



    
}


    
}
