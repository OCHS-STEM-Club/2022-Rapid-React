package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drive {
    public WPI_TalonFX driveMotorLeft1 = new WPI_TalonFX(3);
    public WPI_TalonFX driveMotorRight1 = new WPI_TalonFX(5);

    private WPI_TalonFX driveMotorLeft2 = new WPI_TalonFX(4);
    private WPI_TalonFX driveMotorRight2 = new WPI_TalonFX(6);

    public DifferentialDrive differentialDrive = new DifferentialDrive(driveMotorLeft1, driveMotorRight1);

    private XboxController controller = new XboxController(0);

    PIDController driveController = new PIDController(2.3449, 0, 0.19168);


    public double driveMotorLeftPosition = driveMotorLeft1.getSelectedSensorPosition(3);
    public double driveMotorRightPosition = driveMotorRight1.getSelectedSensorPosition(5);
        
    public double driveMotorLeftPosition2 = driveMotorLeft2.getSelectedSensorPosition(4);
    public double driveMotorRightPosition2 = driveMotorRight2.getSelectedSensorPosition(6);

    public double getAverageDistance = (driveMotorLeftPosition + driveMotorRightPosition) /2;


    

    private double topSpeed = 0.5; //Percent value for drive motor speed from 0 to 1
    private double creepSpeed = 1;
    private double xStickValue; //Variable to store the value from the Xbox Controller
    private double yStickValue; //Variable to store the value from the Xbox Controller

    

    public Drive(){
        driveMotorLeft2.follow(driveMotorLeft1);
        driveMotorRight2.follow(driveMotorRight1);

        driveController.setTolerance(6913.6);

        /*driveMotorLeft1.config_kP(0, 0.0092204, 10);
        driveMotorLeft1.config_kI(0, 0, 10);
        driveMotorLeft1.config_kD(0, 0.70703, 10);

        driveMotorRight1.config_kP(0, 0.0092204, 10);
        driveMotorRight1.config_kI(0, 0, 10);
        driveMotorRight1.config_kD(0, 0.70703, 10);*/

  
       
    }

    /**
     * Main method of driving for Teleop
     */
    public void drive(){
       topSpeed = SmartDashboard.getNumber("Top Speed", 0.6); 
       System.out.print(getAverageDistance);
       // Allows for change of speed limit on SmartDashboard for testing/demo 

        xStickValue = controller.getRawAxis(4) * topSpeed * creepSpeed;
        yStickValue = -controller.getRawAxis(1) * topSpeed * creepSpeed;
        differentialDrive.arcadeDrive(xStickValue, yStickValue, false);

    }

    public void creep(){
        if(controller.getRawAxis(2) == 1){
            creepSpeed = 0.5;
        }else if(controller.getRawAxis(3) == 1){
            creepSpeed = 2;
        }else {
            creepSpeed = 1;
        }
    }

    public void motorSettings() {
        driveMotorLeft1.setNeutralMode(NeutralMode.Brake);
        driveMotorRight1.setNeutralMode(NeutralMode.Brake);
        driveMotorLeft1.configOpenloopRamp(0.75);
        driveMotorRight1.configOpenloopRamp(0.75);
    }

    public void subclassTurn(double turnValue, double moveValue) {
        differentialDrive.arcadeDrive(turnValue, moveValue, false);
    }

    public void auto(double x, double y) {
        differentialDrive.arcadeDrive(x ,y , false);
    }

    
    private double distanceToRevolutions(double distance) {
        distance /= 6 * Math.PI;
        distance *= 10.86;
        distance *= 2048;

        return distance;
    }

    private double clamp(double in, double minval, double maxval) {
        if (in > maxval) {
          return maxval;
        }
        else if (in < minval) {
          return minval;
        }
        else {
          return in;
        }
    }

    public boolean autoDrive(double x) {
        driveMotorLeftPosition = driveMotorLeft1.getSelectedSensorPosition(3);
        driveMotorRightPosition = driveMotorRight1.getSelectedSensorPosition(5);
        getAverageDistance = (driveMotorLeftPosition + driveMotorRightPosition) /2;
        
        double revolutionsNeed = distanceToRevolutions(x);
        double power = driveController.calculate(getAverageDistance, revolutionsNeed);
        power = clamp(power, -0.5, 0.5);

        differentialDrive.arcadeDrive(0, power);

        if (driveController.atSetpoint()) {
            return true;
        } else return false;
    }

}





