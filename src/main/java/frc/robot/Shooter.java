package frc.robot;


import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.ColorMatch;
//import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.math.controller.PIDController;




public class Shooter {
  private WPI_TalonFX shooterMotor = new WPI_TalonFX(7);
  //private VictorSPX hoodMotor = new VictorSPX(11);
  private XboxController controller = new XboxController(1);
  // Encoder for shooter motor
  TalonFXConfiguration configs = new TalonFXConfiguration();

  private PIDController shooterController = new PIDController(0, 0, 0);

  
  //Constant that gets compares to the current shooter temperature

  //Constant that is used in RotationRevolution
  final int UNITPERREV = 2048; 

  private ColorSensorV3 colorSensor;
  // private ColorMatch colorMatcher;

    public double shooterTemperatureAndPosition() {

      double shooterMotorPosition = shooterMotor.getSelectedSensorPosition();
      SmartDashboard.putNumber("Shooter Position", shooterMotorPosition);
      
      double shooterMotorVelocity = -shooterMotor.getSelectedSensorVelocity();
      //Only to show RPM on smart dashboard
      SmartDashboard.putNumber("Shooter Velocity in RPM" , shooterMotorVelocity / 2048 * 600);
      
      double shooterMotorTemperature = shooterMotor.getTemperature() * 1.8 + 32;
      SmartDashboard.putNumber("Shooter Temperature", shooterMotorTemperature);

      
    return shooterMotorPosition;
    }

    public void shooter(){
      if(controller.getRawButton(3)){
        shooterMotor.set(shooterController.calculate(shooterMotor.getSelectedSensorVelocity() , 1));
      }else {
        shooterMotor.set(0);
      }
    }

      //shooterMotor.setNeutralMode(NeutralMode.Coast);
        // Spins motor
    
    public void ShooterPID() {

      SmartDashboard.putNumber("Kp", shooterController.getP());
      SmartDashboard.putNumber("Ki", shooterController.getI());
      SmartDashboard.putNumber("Kd", shooterController.getD());

    }

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

    }


    public void configSelectedFeedbackSensor(TalonFXFeedbackDevice integratedsensor, int i, int j) {
    }

    public void shooterAuto(double x){
        shooterMotor.set(x);
    }

  }