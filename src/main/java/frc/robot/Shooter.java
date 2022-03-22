package frc.robot;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;





public class Shooter {
  


  private WPI_TalonFX shooterMotor = new WPI_TalonFX(7);
  private XboxController controller = new XboxController(1);
  TalonFXConfiguration configs = new TalonFXConfiguration();

  private double velocityWant;
  private double velocityRPM;

  

  //private PIDController shooterController = new PIDController(0, 0, 0);
  //public void configSelectedFeedbackSensor(TalonFXFeedbackDevice integratedsensor, int i, int j) {}

  public Shooter() {
    shooterMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 10);
    //shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10;);
    shooterMotor.setSensorPhase(false);
    shooterMotor.setInverted(true);
    shooterMotor.config_kP(0, 0.7, 10);
    shooterMotor.config_kI(0, 0.0001, 10);
    shooterMotor.config_kD(0, 20, 10);
    shooterMotor.setNeutralMode(NeutralMode.Coast);
    shooterMotor.configPeakOutputForward(0, 10);
    shooterMotor.configClosedloopRamp(1, 10);
  }
  

  
 
  //Constant that is used in RotationRevolution
  final int UNITPERREV = 2048;

  //public double shooterMotorVelocity = -shooterMotor.getSelectedSensorVelocity();
      //Only to show RPM on smart dashboard
      

  
    public double shooterTemperatureAndPosition() {

      double shooterMotorPosition = shooterMotor.getSelectedSensorPosition();
      SmartDashboard.putNumber("Shooter Position", shooterMotorPosition);
      
      SmartDashboard.putNumber("Shooter Velocity in RPM" , -shooterMotor.getSelectedSensorVelocity() / 2048 * 600);

      
      double shooterMotorTemperature = shooterMotor.getTemperature() * 1.8 + 32;
      SmartDashboard.putNumber("Shooter Temperature", shooterMotorTemperature);

      
    return shooterMotorPosition;
    }


    public void shooter(){
      velocityRPM = -1700;
      velocityWant = velocityRPM * 2048 / 600;
      //SmartDashboard.getNumber("shoot position", 12000);
      SmartDashboard.putNumber("velocity", -shooterMotor.getSelectedSensorVelocity());
      SmartDashboard.putNumber("velocityWant", -velocityWant);

       //double shooterMotorVelocityRPM = -shooterMotor.getSelectedSensorVelocity() / 2048 * 600;
    
      /*if(controller.getRawButton(3)){
        //shooterMotor.set(TalonFXControlMode.PercentOutput, -0.7);
        shooterMotor.set(ControlMode.Velocity, velocityWant);
        //shooterMotor.set(shooterController.calculate(shooterMotor.getSelectedSensorVelocity(), -8000));
        System.out.print("shooter velocity " + shooterMotor.getSelectedSensorVelocity() + " velocityWant " + velocityWant + "\n");
  

       //shooterMotor.set(0.8);
       */
     
      }
  
    
  
    public void shooterAuto(double x){
      shooterMotor.set(ControlMode.Velocity, -x * 2048 / 600);
    }


   
  }