package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Limelight {

   

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry networkTableEntry = table.getEntry("networkTableEntry");
    double targetOffsetAngle_Vertical = networkTableEntry.getDouble(0.0);

    double limelightMountAngleDegrees = 0.0; //mount angle from vertical
    double limelightLensHeightInches = 0.0; //distance (in) from center of limelight lenses to floor
    double goalHeightInches = 104; //height of upper hub

    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180);

    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);

    
}
