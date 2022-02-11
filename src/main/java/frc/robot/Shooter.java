package frc.robot;

import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
//import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorSensorV3.RawColor;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class Shooter {
  private WPI_TalonFX shooterMotor = new WPI_TalonFX(7);
  private VictorSPX hoodMotor = new VictorSPX(11);
  private XboxController controller = new XboxController(1);
  // Encoder for shooter motor
  TalonFXConfiguration configs = new TalonFXConfiguration();

 

  // Single solonoid for cooling shooter motor
  //private Solenoid shooterCoolerSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 2); 

  public static PIDController shooterPID =  new PIDController(001,0.001, 5, 1023.0/20660.0,  300,  1.00);

 
  
  
  //Constant that gets compares to the current shooter temperature
  private static final double MAX_SHOOT_TEMP = 72;

  //Constant that is used in RotationRevolution
  final int UNITPERREV = 2048; 

  private ColorSensorV3 colorSensor;
  // private ColorMatch colorMatcher;


   public void hoodMotor() {
    if (controller.getRawAxis(2) > 0){
      hoodMotor.set(ControlMode.PercentOutput, 0.3);
    }else if(controller.getRawAxis(3) > 0){
      hoodMotor.set(ControlMode.PercentOutput, -0.3);
    }else {
      hoodMotor.set(ControlMode.PercentOutput, 0);
    }

  }

    public void shooterAuto(){
      shooterMotor.set(0.8);
    }

    public void shooterAutoStop(){
      shooterMotor.set(0);
    }


    public void shooter(){
      if(controller.getRawButton(8)){
        shooterMotor.set(0.8);
      }else if(controller.getRawButton(3)){
        shooterMotor.set(0);
      }

      shooterMotor.setNeutralMode(NeutralMode.Coast);
        // Spins motor
    }
    

    /*
    *Takes the shooter motor temperature in Celcius and converts it to Fahrenheit 
    *and puts the output on Dashboard. "If else" statement takes the shooter motor 
    *temperature, and if it is heigher than the constant, then fire solonoids.
    */
   
    
    public double shooterTemperatureAndPosition() {

      double shooterMotorPosition = shooterMotor.getSelectedSensorPosition();
      SmartDashboard.putNumber("Shooter Position", shooterMotorPosition);
      double shooterMotorVelocity = -shooterMotor.getSelectedSensorVelocity();
      //Only to show RPM on smart dashboard
      SmartDashboard.putNumber("Shooter Velocity in RPM" , shooterMotorVelocity/2048 * 600);
      
      double shooterMotorTemperature = shooterMotor.getTemperature() * 1.8 + 32;
      SmartDashboard.putNumber("Shooter Temperature", shooterMotorTemperature);
/*
      start of temperature check 
      if (controller.getRawButton(1) || shooterMotorTemperature >= MAX_SHOOT_TEMP){
      shooterCoolerSolenoid.set(true);
      System.out.print(shooterMotorTemperature);
      } else { 
      shooterCoolerSolenoid.set(false);
      }
      SmartDashboard.putData("Solenoid Cooler Status", shooterCoolerSolenoid);
    */
    return shooterMotorPosition;
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

  }
