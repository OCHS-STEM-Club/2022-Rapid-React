package frc.robot;

import edu.wpi.first.wpilibj.Timer;
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

    Indexer indexerMotor;
    Shooter shooterMotor;
    Intake intakeMotor;
    Timer timer = new Timer();
    Drive driveManager;
    Intake intakeLiftMotor;

    public Autonomous( Indexer i , Shooter s, Intake in, Drive l, Intake in2){
        indexerMotor = i;
        shooterMotor = s; 
        driveManager = l;
        intakeMotor = in;
        intakeLiftMotor = in2;
    }
   
    public void timer() {
        timer.reset();
        timer.start();
    }
    
    public void autonomous() {
        SmartDashboard.putNumber("Timer", timer.get());
      //  driveMotorLeft1.setNeutralMode(NeutralMode.Coast);
      //  driveMotorRight1.setNeutralMode(NeutralMode.Coast);
       
        if (timer.get() < 1) {
            intakeLiftMotor.intakeLiftMotorAuto(-0.3);
        } else if (timer.get() < 2) {
            shooterMotor.shooterAuto(0.60);
            // start shooter motor
        } else if (timer.get() > 2 && timer.get() < 4) {
            indexerMotor.indexAuto(-0.75);
        // start index wheel (shoots ball)
        } else if (timer.get() > 4 && timer.get() < 6.5) {
             shooterMotor.shooterAuto(0);
             indexerMotor.indexAuto(0);
             driveManager.auto(0,-0.3);
             intakeMotor.intakeAuto(-0.3);
        } else if (timer.get() > 6.5 && timer.get() < 7.5) {
            driveManager.auto(0, 0); 
        // start shooter
        } else if (timer.get() > 7.5 && timer.get() < 10) {
            driveManager.auto(0, 0.3);
            shooterMotor.shooterAuto(0.60);
        // move back to starting point
        }else if (timer.get() > 10 && timer.get() < 12 ) {
            indexerMotor.indexAuto(0);
        }else if (timer.get() > 12 && timer.get() < 14 ) {
            indexerMotor.indexAuto(-0.3);
        //shoots ball
        }else if (timer.get() > 14 && timer.get() < 15 ) {
            shooterMotor.shooterAuto(0);
            indexerMotor.indexAuto(0);
            intakeMotor.intakeAuto(0);
        // shuts off all motors
        }
    }
}


     
      
    
