package frc.robot;


import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

public class Indexer {
    private CANSparkMax indexerMotor = new CANSparkMax(20 , MotorType.kBrushless);
    private XboxController controller = new XboxController(1);


public void indexWheel(){
    if(controller.getPOV() == 0){
      indexerMotor.set(-0.75);
   }else{
     indexerMotor.set(0);
     }
   }
public void indexAuto(){
  indexerMotor.set(-0.3);
}
   
}


