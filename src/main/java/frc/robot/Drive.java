package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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

    private double topSpeed = 0.5; //Percent value for drive motor speed from 0 to 1
    private double xStickValue; //Variable to store the value from the Xbox Controller
    private double yStickValue; //Variable to store the value from the Xbox Controller

    public Drive(){
        driveMotorLeft2.follow(driveMotorLeft1);
        driveMotorRight2.follow(driveMotorRight1);
        driveMotorRight1.setInverted(true);
        //Number represents the time in seconds it takes for the motors to go from Neutral to Full speed
        driveMotorLeft1.configOpenloopRamp(1);
        driveMotorRight1.configOpenloopRamp(1);
        //Brakes the motors; can be Coaster
        driveMotorLeft1.setNeutralMode(NeutralMode.Brake);
        driveMotorRight1.setNeutralMode(NeutralMode.Brake);


    }

    /**
     * Main method of driving for Teleop
     */
    public void drive(){
        topSpeed = SmartDashboard.getNumber("Top Speed", 0.5);
        xStickValue = -controller.getRawAxis(1) * topSpeed;
        yStickValue = controller.getRawAxis(4) * topSpeed;
        differentialDrive.arcadeDrive(xStickValue, yStickValue, false);

    }

}
