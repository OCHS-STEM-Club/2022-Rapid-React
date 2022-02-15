package frc.robot;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

import java.sql.DriverManager;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;







public class Autonomous {

    Indexer indexerMotor;
    Shooter shooterMotor;
    Timer timer = new Timer();
    Drive driveMotorLeft1;
    Drive driveMotorRight1;
    Drive differentialDrive;


    public double driveMotorLeftPosition = driveMotorLeft1.getSelectedSensorPosition(3);
    public double driveMotorRightPosition = driveMotorRight1.getSelectedSensorPosition(5);

    public double getAverageDistance = (driveMotorLeftPosition + driveMotorRightPosition) /2;


   
   
    public Autonomous( Indexer i , Shooter s, Drive l, Drive r, Drive d){
        indexerMotor = i;
        shooterMotor = s; 
        driveMotorLeft1 = l;
        driveMotorRight1 = r;
        differentialDrive = d;


    }
    /*DifferentialDrive differentialDrive = driveManager.differentialDrive;
    public void averageDistance(double a) {
         getAverageDistance = driveManager.encoders();
    }

*/

    public void timer() {
        timer.reset();
        timer.start();
    }
    

    public void autonomous() {
        SmartDashboard.putNumber("Timer", timer.get());

        if (timer.get() < 3.0) {
            shooterMotor.shooterAuto(0.8);
        
        }
        // if time is less than 3 seconds, start motor
         else if (timer.get() > 3 && timer.get() < 5) {
            indexerMotor.indexAuto(-0.75);
        // if time is greater than 3 seconds and less than 5 seconds, start index wheel
         }else if (timer.get()> 6){
             shooterMotor.shooterAuto(0);
             indexerMotor.indexAuto(0);

             /*
         } else if (getAverageDistance > 0.0 && timer.get() > 10) {
            differentialDrive.arcadeDrive(0.5, 0, false);
        // if encoders are less than 10 and time is greater than 10 seconds, move at 50% speed forward

        } else if (getAverageDistance > 10 && timer.get() > 13) {
            differentialDrive.arcadeDrive(0, 0);
            shooterMotor.shooterAuto(0);
            indexerMotor.indexAuto(0);
        // if encoders are greater than 10, stop motors

       // } else;

        */

        }
     }
    }   
    
