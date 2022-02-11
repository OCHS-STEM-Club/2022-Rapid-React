package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.XboxController;

public class Indexer {
    private CANSparkMax indexerMotor = new CANSparkMax(20 , MotorType.kBrushless);
    private XboxController controller = new XboxController(1);

public void indexWheelAuto(){
  indexerMotor.set(0.3);
}

public void indexWheelAutoStop(){
  indexerMotor.set(0);
}

public void indexWheel(){
    if(controller.getPOV() == 0){
      indexerMotor.set(-0.75);
   }else{
     indexerMotor.set(0);
     }
   }
}
