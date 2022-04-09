package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-khonsu");
    //NetworkTableEntry networkTableEntry = table.getEntry("networkTableEntry");

    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tl = table.getEntry("tl");
    
    static double  limelightMountAngleDegrees = 89.75; //mount angle from vertical
    static double limelightLensHeightInches = 17.4375; //distance (in) from center of limelight lenses to floor
    static double goalHeightInches = 104; //height of upper hub

    double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    double targetOffsetAngle_Horizontal = tx.getDouble(0.0);
    double targetArea = ta.getDouble(0.0);
    double targetSkew = tl.getDouble(0.0);

   


    
    //double targetOffsetAngle_Horizontal = table.getEntry("tx").getDouble(0.0);
    //double targetOffsetAngle_Vertical = table.getEntry("ty").getDouble(0.0);
    //double targetArea = table.getEntry("ta").getDouble(0.0);
    //double targetSkew = table.getEntry("ts").getDouble(0.0);

  
    private double targetValue;
    private double turnOutput;
    private double driveOutput;
    private final double MAX_STEER = 0.1;
    private final double STEER_K = 0.075;

    public void limelight() {
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tl = table.getEntry("tl");

        double targetOffsetAngle_Vertical = ty.getDouble(0.0);
        double targetOffsetAngle_Horizontal = tx.getDouble(0.0);
        double targetArea = ta.getDouble(0.0);
        double targetSkew = tl.getDouble(0.0);

        SmartDashboard.putNumber("tx", targetOffsetAngle_Horizontal);
        SmartDashboard.putNumber("ty", targetOffsetAngle_Vertical);
        SmartDashboard.putNumber("ta", targetArea);
        SmartDashboard.putNumber("tl", targetSkew);

        
        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180);
        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
        SmartDashboard.putNumber("Distance to Hub", distanceFromLimelightToGoalInches);

    }
     

    public double getDistance() {
        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180);
        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
        System.out.print(distanceFromLimelightToGoalInches);
        return distanceFromLimelightToGoalInches;

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

    public double trackTurn() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-khonsu");
        targetOffsetAngle_Horizontal = table.getEntry("tx").getDouble(0.0);
        targetValue = table.getEntry("tv").getDouble(0.0);
        SmartDashboard.putNumber("tv", targetValue);
    
        if (targetValue == 1) {
            turnOutput = targetOffsetAngle_Horizontal * STEER_K; //or divid by max value (27 degrees)
            turnOutput = clamp(turnOutput, -MAX_STEER, MAX_STEER);
            return turnOutput;
        }
        else {
            return 0;
        }
    }

    public double trackDrive() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-khonsu");
        targetOffsetAngle_Vertical = table.getEntry("ty").getDouble(0.0);
        targetValue = table.getEntry("tv").getDouble(0.0);
        SmartDashboard.putNumber("tv", targetValue);
        
        if (targetValue == 1) {
            driveOutput = targetOffsetAngle_Vertical * STEER_K; //or divid by max value (27 degrees)
            driveOutput = clamp(driveOutput, -0.2, 0.2);
            return driveOutput;
        }
        else {
            return 0;
        }
    }

  


}
