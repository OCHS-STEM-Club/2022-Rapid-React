package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {
    private WPI_TalonFX driveMotorLeft1 = new WPI_TalonFX(3);
    private WPI_TalonFX driveMotorRight1 = new WPI_TalonFX(5);

    private WPI_TalonFX driveMotorLeft2 = new WPI_TalonFX(4);
    private WPI_TalonFX driveMotorRight2 = new WPI_TalonFX(6);

    private DifferentialDrive differentialDrive = new DifferentialDrive(driveMotorLeft1, driveMotorRight1);

    private XboxController controller = new XboxController(0);

    public Drive(){
        driveMotorLeft2.follow(driveMotorLeft1);
        driveMotorRight2.follow(driveMotorRight1);
        driveMotorRight1.setInverted(true);

    }

    /**
     * Main method of driving for Teleop
     */
    public void drive(){
        differentialDrive.arcadeDrive(controller.getRawAxis(1), controller.getRawAxis(4));
    }

}
