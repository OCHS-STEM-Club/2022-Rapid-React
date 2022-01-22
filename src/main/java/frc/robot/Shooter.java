package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
  private WPI_TalonFX shooterMotor = new WPI_TalonFX(7);

  private XboxController controller = new XboxController(1);
  // Encoder for shooter motor
  TalonFXConfiguration configs = new TalonFXConfiguration();
  // Single solonoid for cooling shooter motor
  private Solenoid shooterCoolerSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 2); 

  //Constant that gets compared to the current shooter temperature
  private static final double MAX_SHOOT_TEMP = 78;

    public void shooter(){
      shooterMotor.set(controller.getRawAxis(1));
      shooterMotor.configAllSettings(configs);

        // Spins motor
    }
    /*
    *Takes the shooter motor temperature in Celcius and converts it to Fahrenheit 
    *and puts the output on Dashboard. "If else" statement takes the shooter motor 
    *temperature, and if it is heigher than the constant, then fire solonoids.
    */
    public double shooterTemperature() {

      double shooterMotorTemperature = shooterMotor.getTemperature() * 1.8 + 32;
      SmartDashboard.putNumber("Shooter Temperature", shooterMotorTemperature);

     //start of temperature check 
    if (controller.getRawButton(1) || shooterMotorTemperature >= MAX_SHOOT_TEMP){
      shooterCoolerSolenoid.set(true);
    } else { 
      shooterCoolerSolenoid.set(false);
    }
    SmartDashboard.putData("Solenoid Cooler Status", shooterCoolerSolenoid);
      
      return shooterMotorTemperature;  
    
    }
}
