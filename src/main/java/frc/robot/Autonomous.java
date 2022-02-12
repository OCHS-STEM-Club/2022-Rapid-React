package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;





public class Autonomous {

    Indexer indexerMotor;
    Shooter shooterMotor;
   
    public Autonomous( Indexer i  , Shooter s ){
        indexerMotor = i;
        shooterMotor = s;
    }

    Timer timer = new Timer();
    

    public void autonomous() {
        timer.reset();
        timer.start();

        if (timer.get() < 3.0) {
            indexerMotor.indexAuto(-0.3);
        } else {
            indexerMotor.indexAuto(0);
        }
        /* if time is less than 3 seconds, start motor

        } else if (timer.get() > 3 && timer.get() < 10) {
            indexWheel.set(0.3);
        // if time is greater than 3 seconds and less than 5 seconds, start index wheel

        } else if (getAverageDistance < 10 && timer.get() > 10) {
            differentialDrive.arcadeDrive(0.5, 0);
        // if encoders are less than 10 and time is greater than 10 seconds, move at 50% speed forward

        } else if (getAverageDistance > 10) {
            differentialDrive.arcadeDrive(0, 0);
            shooterMotor.set(0);
            indexWheel.set(0);
        // if encoders are greater than 10, stop motors
*/
       // } else;



        }
     }   
    
