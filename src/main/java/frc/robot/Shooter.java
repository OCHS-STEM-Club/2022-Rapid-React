package frc.robot;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
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

  //private double velocityWant;

  

  //private PIDController shooterController = new PIDController(0, 0, 0);
  //public void configSelectedFeedbackSensor(TalonFXFeedbackDevice integratedsensor, int i, int j) {}

  public Shooter() {
    shooterMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 10);
    //shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10;);
    shooterMotor.setSensorPhase(false);
    shooterMotor.setInverted(true);
    shooterMotor.config_kP(0, 0.06, 10);
    shooterMotor.config_kI(0, 0.06, 10);
    shooterMotor.config_kD(0, 0.06, 10);
    shooterMotor.setNeutralMode(NeutralMode.Coast);
    shooterMotor.configPeakOutputForward(0.8, 10);
    shooterMotor.configClosedloopRamp(1, 10);
  }
  

  
 
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
      //velocityWant = SmartDashboard.getNumber("shoot position", 12000);
    
      if(controller.getRawButton(3)){
        shooterMotor.set(TalonFXControlMode.PercentOutput, -0.8);
        //shooterMotor.set(shooterController.calculate(shooterMotor.getSelectedSensorVelocity(), -8000));
        System.out.print("shooter velocity" + shooterMotor.getSelectedSensorVelocity() + "\n");
       //shooterMotor.set(0.8);
      }else {
        shooterMotor.set(0);
      }
    }
        

    public void shooterAuto(double x){
        shooterMotor.set(x);
    }

   
  }