package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.XboxController;

public class Shooter {
  private WPI_TalonFX shooterMotor = new WPI_TalonFX(7);

  private XboxController controller = new XboxController(1);

  TalonFXConfiguration configs = new TalonFXConfiguration();
  shooterMotor.configAllSettings(configs);


    public void shooter(){
        shooterMotor.set(controller.getRawAxis(1));
        controller.
        // Spins motor
    }
}
