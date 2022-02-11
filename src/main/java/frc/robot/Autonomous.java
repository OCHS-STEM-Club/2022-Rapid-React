package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Drive;
import frc.robot.Shooter;
import frc.robot.Indexer;

public class Autonomous {
   
    private double getAverageDistance;
    private DifferentialDrive differentialDrive;
    private WPI_TalonFX shooterMotor;
    private WPI_TalonFX indexWheel;

    Timer timer = new Timer();

    public void autonomous() {
        timer.reset();
        timer.start();

        if (timer.get() < 3) {
            shooterMotor.set(0.8);
        // if time is less than 3 seconds, start motor
        
        } else if (timer.get() > 3 && timer.get() < 5) {
            indexWheel.set(-0.3);
        // if time is greater than 3 seconds and less than 5 seconds, start index wheel

        } else if (getAverageDistance < 10 && timer.get() > 7) {
            differentialDrive.arcadeDrive(-0.5, 0);
        // if encoders are less than 10 and time is greater than 7 seconds, move at 50% speed forward

        } else if (getAverageDistance > 10 || timer.get() > 13) {
            differentialDrive.arcadeDrive(0, 0);
            shooterMotor.set(0);
            indexWheel.set(0);
        // if encoders are greater than 10, or if the time is greater than 13 seconds stop motors 

        } else;
        

        
       
        }

   

}
