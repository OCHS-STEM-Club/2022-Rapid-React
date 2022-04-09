package frc.robot;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
/*
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

import java.sql.DriverManager;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
*/
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Autonomous{

    private XboxController controller = new XboxController(1);

    Indexer indexerMotor;
    Shooter shooterMotor;
    Intake intakeMotor;
    Timer timer = new Timer();
    Drive driveManager;
    Intake intakeLiftMotor;
    Limelight limelight;

    boolean isFalse = false;

    public Autonomous( Indexer i , Shooter s, Intake in, Drive d, Intake in2, Limelight l){
        indexerMotor = i;
        shooterMotor = s; 
        driveManager = d;
        intakeMotor = in;
        intakeLiftMotor = in2;
        limelight = l;
    }
   
    public void timer() {
        timer.reset();
        timer.start();
    }
    //Main 2 ball shoot auto
    public Void autonomousShoot2Balls() {
        SmartDashboard.putNumber("Timer", timer.get());
       
        if (timer.get() < 0.5) {
            intakeLiftMotor.intakeLiftMotorAuto(0.3);
        } else if (timer.get() > 0.5 && timer.get() < 2.0) {
            intakeLiftMotor.intakeLiftMotorAuto(0);
            driveManager.auto(0,0.3);
            intakeMotor.intakeAuto(-0.85);
            // start shooter motor
        } else if (timer.get() > 2 && timer.get() < 2.5) {
            intakeMotor.intakeAuto(0);
            driveManager.auto(0,0);
        // start index wheel (shoots ball)
        } else if (timer.get() > 2.5 && timer.get() < 4) {
            driveManager.auto(0,-0.3);
            shooterMotor.shooterAuto(2650);
        } else if (timer.get() > 4 && timer.get() < 4.75) {
            driveManager.auto(0,0);
        } else if (timer.get() > 4.75 && timer.get() < 5.5) {
            indexerMotor.indexAuto(-0.75);
        } else if (timer.get() > 5.5 && timer.get() < 7.5) {
            intakeMotor.intakeAuto(-0.85);
            indexerMotor.indexAuto(0);
        // start shooter
        } else if (timer.get() > 7.5 && timer.get() < 9) {
            indexerMotor.indexAuto(-0.75);
        }else if (timer.get() > 9.0 && timer.get() < 15 ) {
            shooterMotor.shooterAuto(0);
            indexerMotor.indexAuto(0);
            intakeMotor.intakeAuto(0);
        // shuts off all motors
        }
        return null;
    }
// Another 2 Ball shoot
    public Void autonomousShoot2Balls2() {
        SmartDashboard.putNumber("Timer", timer.get());
       
        if (timer.get() < 0.5) {
            intakeLiftMotor.intakeLiftMotorAuto(0.3);
        } else if (timer.get() > 0.5 && timer.get() < 3) {
            intakeLiftMotor.intakeLiftMotorAuto(0);
            shooterMotor.shooterAuto(2800);
            // start shooter motor
        } else if (timer.get() > 3 && timer.get() < 4) {
            indexerMotor.indexAuto(-0.75);
            intakeMotor.intakeAuto(-0.85);
        // start index wheel (shoots ball)
        } else if (timer.get() > 4 && timer.get() < 5.5) {
             shooterMotor.shooterAuto(0);
             indexerMotor.indexAuto(0);
             driveManager.auto(0,0.3);
        } else if (timer.get() > 5.5 && timer.get() < 6.5) {
            driveManager.auto(0, 0); 
        // start shooter
        } else if (timer.get() > 6.5 && timer.get() < 8) {
            driveManager.auto(0, -0.3);
            shooterMotor.shooterAuto(2800);
        // move back to starting point
        }else if (timer.get() > 8.0 && timer.get() < 10.0 ) {
            indexerMotor.indexAuto(0);
        }else if (timer.get() > 10.0 && timer.get() < 12.0 ) {
            indexerMotor.indexAuto(-0.75);
        //shoots ball
        }else if (timer.get() > 12.0 && timer.get() < 15 ) {
            shooterMotor.shooterAuto(0);
            indexerMotor.indexAuto(0);
            intakeMotor.intakeAuto(0);
        // shuts off all motors
        }
        return null;
    }


    public Void autonomousShoot2BallsWALL() {
        SmartDashboard.putNumber("Timer", timer.get());
       
        if (timer.get() < 0.5) {
            intakeLiftMotor.intakeLiftMotorAuto(0.3);
        } else if (timer.get() > 0.5 && timer.get() < 3.0) {
            intakeLiftMotor.intakeLiftMotorAuto(0);
            driveManager.auto(0,0.15);
            intakeMotor.intakeAuto(-0.85);
            // start shooter motor
        } else if (timer.get() > 3 && timer.get() < 3.5) {
            intakeMotor.intakeAuto(0);
            driveManager.auto(0,0);
        // start index wheel (shoots ball)
        } else if (timer.get() > 3.5 && timer.get() < 5) {
            driveManager.auto(0,-0.15);
            shooterMotor.shooterAuto(2650);
            intakeLiftMotor.intakeLiftMotorAuto(-0.2);
        } else if (timer.get() > 5 && timer.get() < 5.75) {
            driveManager.auto(0,0);
            intakeLiftMotor.intakeLiftMotorAuto(-0);
        } else if (timer.get() > 5.75 && timer.get() < 6.5) {
            indexerMotor.indexAuto(-0.75);
        } else if (timer.get() > 6.5 && timer.get() < 8.5) {
            intakeMotor.intakeAuto(-0.85);
            indexerMotor.indexAuto(0);
        // start shooter
        } else if (timer.get() > 8.5 && timer.get() < 10) {
            indexerMotor.indexAuto(-0.75);
        }else if (timer.get() > 10.0 && timer.get() < 12.25 ) {
            driveManager.auto(0,0.15);
            shooterMotor.shooterAuto(0);
            indexerMotor.indexAuto(0);
            intakeMotor.intakeAuto(0);
        }else if (timer.get() > 12.25 && timer.get() < 15 ) {
            driveManager.auto(0,0);
        // shuts off all motors
        }
        return null;
    }
    
    public Void autonomousShoot1Ball(){
        SmartDashboard.putNumber("Timer", timer.get());
         
          if (timer.get() < 0.5) {
              intakeLiftMotor.intakeLiftMotorAuto(0.3);
          } else if (timer.get() > 0.5 && timer.get() < 3) {
              intakeLiftMotor.intakeLiftMotorAuto(0);
              shooterMotor.shooterAuto(2700);
              // start shooter motor
          } else if (timer.get() > 3 && timer.get() < 4) {
              indexerMotor.indexAuto(-0.75);
          // start index wheel (shoots ball)
          } else if (timer.get() > 4 && timer.get() < 6) {
               shooterMotor.shooterAuto(0);
               indexerMotor.indexAuto(0);
               driveManager.auto(0,0.3);
          } else if(timer.get()> 6){
              driveManager.auto(0,0);

          }
        return null;

    }

    public Void autonomousDrivePID() {
        if (!isFalse) {
            isFalse = driveManager.autoDrive(36);
        } else driveManager.auto(0, 0);
        return null;
    }

    public Void autonomousMoveOutOnly(){
        SmartDashboard.putNumber("Timer", timer.get());

        if(timer.get() < 0.5 ) {
            intakeLiftMotor.intakeLiftMotorAuto(0.3);
        } else if(timer.get() > 0.5 && timer.get() < 2){
            driveManager.auto(0,0.3);
        }else if (timer.get() > 1.5){
            driveManager.auto(0,0);
        }
        return null; 
    }
}




    
