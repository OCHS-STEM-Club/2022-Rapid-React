package frc.robot;


import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;




import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.math.controller.PIDController;




public class Shooter {
  private WPI_TalonFX shooterMotor = new WPI_TalonFX(7);
  private XboxController controller = new XboxController(1);
  TalonFXConfiguration configs = new TalonFXConfiguration();

  private PIDController shooterController = new PIDController(0.25,0, 0);
 
  //Constant that is used in RotationRevolution
  final int UNITPERREV = 2048;

  public double shooterMotorVelocity = -shooterMotor.getSelectedSensorVelocity();
      //Only to show RPM on smart dashboard
      

  
    public double shooterTemperatureAndPosition() {

      double shooterMotorPosition = shooterMotor.getSelectedSensorPosition();
      SmartDashboard.putNumber("Shooter Position", shooterMotorPosition);
      
      SmartDashboard.putNumber("Shooter Velocity in RPM" , -shooterMotor.getSelectedSensorVelocity() /*/ 2048 * 600*/);

      
      double shooterMotorTemperature = shooterMotor.getTemperature() * 1.8 + 32;
      SmartDashboard.putNumber("Shooter Temperature", shooterMotorTemperature);

      
    return shooterMotorPosition;
    }

    public void shooter(){
      System.out.print("ur mom" + shooterMotorVelocity);
      if(controller.getRawButton(3)){
        shooterMotor.set(shooterController.calculate(shooterMotorVelocity , 0.8));
        

       // shooterMotor.set(0.8);
      }else {
        shooterMotor.set(0);
      }
    }

    
    /*public void ShooterPID() {

      SmartDashboard.putNumber("Kp", shooterController.getP());
      SmartDashboard.putNumber("Ki", shooterController.getI());
      SmartDashboard.putNumber("Kd", shooterController.getD());

    }*/

    public void configSelectedFeedbackSensor(TalonFXFeedbackDevice integratedsensor, int i, int j) {
    }

    public void shooterAuto(double x){
        shooterMotor.set(x);
    }

  }