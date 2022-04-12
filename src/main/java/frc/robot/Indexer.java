package frc.robot;


//import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax;
/*import com.revrobotics.ColorMatch;
//import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
*/
import com.revrobotics.CANSparkMax.IdleMode;


public class Indexer {
    private CANSparkMax indexerMotor = new CANSparkMax(20 , MotorType.kBrushless);
    private XboxController controller = new XboxController(1);
    //private ColorSensorV3 colorSensor;


public void indexWheel(){
    if(controller.getPOV() == 0){
      indexerMotor.set(-0.75);
   }else if(controller.getPOV() == 180){
      indexerMotor.set(0.5);
   }else{
     indexerMotor.set(0);
     }
   }
   
public void indexMotorControl(){
  indexerMotor.setIdleMode(IdleMode.kBrake);
}

public void indexAuto(double x){
  indexerMotor.set(x);
}

/*
public void ColorSensor() {
  I2C.Port i2cPort = I2C.Port.kOnboard;
  colorSensor = new ColorSensorV3(i2cPort);
  //colorMatcher = new ColorMatch();
}


public Alliance getAllianceColor() {
  return DriverStation.getAlliance();
}


public String publishAllianceColor(){
 String myAlliance = "Invalid";
  if(getAllianceColor() == Alliance.Red){
    myAlliance = "Red";
  } else if (getAllianceColor() == Alliance.Blue){
    myAlliance = "Blue";
  }else if (getAllianceColor() == Alliance.Invalid){
    myAlliance = "Invalid";
  }

   SmartDashboard.putString("My Alliance", myAlliance);

return myAlliance;
}


public void getColor() {
  Color detectedColor = colorSensor.getColor();
 
  SmartDashboard.putNumber("Red", detectedColor.red);
  SmartDashboard.putNumber("Blue", detectedColor.blue);

}


public double getRed() {
  Double redDouble = colorSensor.getColor().red;
  return redDouble;
}


public double getBlue() {
  Double blueDouble = colorSensor.getColor().blue;
  return blueDouble;
}


public void isBallOurs() {
  if ((publishAllianceColor() == "Red") && (getRed() > getBlue())){
    SmartDashboard.putBoolean("Is Ball Ours", true);
  } else if ((publishAllianceColor() == "Blue") && (getBlue() > getRed())){
    SmartDashboard.putBoolean("Is Ball Ours", true);
  } else SmartDashboard.putBoolean("Is Ball Ours", false);

}*/

}


