package frc.robot;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

import java.sql.DriverManager;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;







public class Autonomous{

    Indexer indexerMotor;
    Shooter shooterMotor;
    Timer timer = new Timer();
    Drive driveManager;

    public Autonomous( Indexer i , Shooter s, Drive l){
        indexerMotor = i;
        shooterMotor = s; 
        driveManager = l;
    }
   
    public void timer() {
        timer.reset();
        timer.start();
    }
    
    public void autonomous() {
        SmartDashboard.putNumber("Timer", timer.get());
      //  driveMotorLeft1.setNeutralMode(NeutralMode.Coast);
      //  driveMotorRight1.setNeutralMode(NeutralMode.Coast);
       

        if (timer.get() < 2.0) {
            shooterMotor.shooterAuto(0.8);
            // if time is less than 3 seconds, start motor
        } else if (timer.get() > 2 && timer.get() < 4) {
            indexerMotor.indexAuto(-0.75);
        // if time is greater than 3 seconds and less than 5 seconds, start index wheel
        } else if (timer.get() > 4 && timer.get() < 6) {
             shooterMotor.shooterAuto(0);
             indexerMotor.indexAuto(0);
             driveManager.auto(0,-0.25);
        //if encoders are less than 10 and time is greater than 10 seconds, move at 50% speed forward
        } else if (timer.get() > 6) {
            driveManager.auto(0,0);
        // if encoders are greater than 10, stop ALL motors
        }

    }
}


     
      
    
