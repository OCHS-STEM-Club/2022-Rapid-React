package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX; // Need to test if needed
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drive {
    private WPI_TalonFX driveMotorLeft1 = new WPI_TalonFX(3);
    private WPI_TalonFX driveMotorRight1 = new WPI_TalonFX(5);

    private WPI_TalonFX driveMotorLeft2 = new WPI_TalonFX(4);
    private WPI_TalonFX driveMotorRight2 = new WPI_TalonFX(6);

    private DifferentialDrive differentialDrive = new DifferentialDrive(driveMotorLeft1, driveMotorRight1);

    private XboxController controller = new XboxController(0);

    private double topSpeed = 0.25; //Percent value for drive motor speed from 0 to 1
    private double xStickValue; //Variable to store the value from the Xbox Controller
    private double yStickValue; //Variable to store the value from the Xbox Controller

    public Drive(){
        driveMotorLeft2.follow(driveMotorLeft1);
        driveMotorRight2.follow(driveMotorRight1);



        //driveMotorRight1.setInverted(true);
        //Number represents the time in seconds it takes for the motors to go from Neutral to Full speed
       
        //Brakes the motors; can be Coaster
       

    }

    /**
     * Main method of driving for Teleop
     */
    public void drive(){
       topSpeed = SmartDashboard.getNumber("Top Speed", 0.25); 
       // Allows for change of speed limit on SmartDashboard for testing/demo 

        xStickValue = controller.getRawAxis(4) * topSpeed;
        yStickValue = controller.getRawAxis(1) * topSpeed;
        differentialDrive.arcadeDrive(xStickValue, yStickValue, false);

    }

    public void encoders(){
    
        double driveMotorLeftPosition = driveMotorLeft1.getSelectedSensorPosition(3);
        double driveMotorRightPosition = driveMotorRight1.getSelectedSensorPosition(5);
        
        double driveMotorLeftPosition2 = driveMotorLeft2.getSelectedSensorPosition(4);
        double driveMotorRightPosition2 = driveMotorRight2.getSelectedSensorPosition(6);

        SmartDashboard.putNumber("Left motor Encoder", driveMotorLeftPosition);
        SmartDashboard.putNumber("Left motor 2 Encoder", driveMotorLeftPosition2);

        SmartDashboard.putNumber("Right motor Encoder", driveMotorRightPosition);
        SmartDashboard.putNumber("Right motor 2 Encoder", driveMotorRightPosition2);

        //double getAverageDistance = (driveMotorLeftPosition + driveMotorRightPosition) / 2;

        
        
    }

    public void motorSettings() {
        driveMotorLeft1.setNeutralMode(NeutralMode.Brake);
        driveMotorRight1.setNeutralMode(NeutralMode.Brake);

        //driveMotorLeft1.configOpenloopRamp(1);
        //driveMotorRight1.configOpenloopRamp(1);

    }

    public void arcadeDrive(double d, int i) {
    }

   

}


