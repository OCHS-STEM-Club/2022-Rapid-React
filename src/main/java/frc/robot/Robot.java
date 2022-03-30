// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.XboxController;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Drive driveManager = new Drive();
  Intake intakeMotor = new Intake();
  Shooter shooterMotor = new Shooter();
  Indexer indexerMotor = new Indexer();
  Climber climber = new Climber();
  Potentiometer shooterPotentiometer = new Potentiometer();
  Limelight limelight = new Limelight();
  Autonomous autonomous = new Autonomous(indexerMotor, shooterMotor, intakeMotor, driveManager, intakeMotor, limelight);

  private XboxController controller = new XboxController(0);

  double visionTurn;
  double visionMove;
  
  
  //private AHRS navx = new AHRS();

  private String m_autoSelected;
  private final SendableChooser<Integer> m_chooser = new SendableChooser<Integer>();
  
  public Robot() {
    m_chooser.setDefaultOption("2 Ball Shoot", 0);
    m_chooser.addOption("2 Ball Shoot Part 2", 1);
    m_chooser.addOption("1 Ball Shoot", 2);
    m_chooser.addOption("Move", 3);
    //m_chooser.addOption("DrivePID", 4);
  }

  

  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    SmartDashboard.putNumber("Top Speed", 0.5);
    driveManager.motorSettings();
    

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putData("Autonomous Chooser", m_chooser);
    limelight.limelight();
    limelight.getDistance();

    
  
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
   //navx.zeroYaw();
      //resets the encoders of the drivemotors 
    autonomous.timer();

    if (m_chooser.getSelected() == 0) {
      autonomous.autonomousShoot2Balls();
    } else if(m_chooser.getSelected() == 1){
      autonomous.autonomousShoot2Balls2();
    }else if (m_chooser.getSelected() == 2) {
      autonomous.autonomousShoot1Ball();
    }else if (m_chooser.getSelected() == 3) {
      autonomous.autonomousMoveOutOnly();
    } /*else if (m_chooser.getSelected() == 4) {
      autonomous.autonomousDrivePID();
    }*/
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
    if (m_chooser.getSelected() == 0) {
      autonomous.autonomousShoot2Balls();
    } else if (m_chooser.getSelected() == 1) {
      autonomous.autonomousShoot2Balls2();
    }else if (m_chooser.getSelected() == 2) {
      autonomous.autonomousShoot1Ball();
    }else if (m_chooser.getSelected() == 3) {
      autonomous.autonomousMoveOutOnly();
    }/* else if (m_chooser.getSelected() == 4) {
      autonomous.autonomousDrivePID();
    }*/
    }
  

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {

  //navx.zeroYaw();
  //resets the encoders of the drivemotors 
 }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    if (controller.getRawButton(8)) {
      visionTurn = limelight.trackTurn();
      driveManager.subclassTurn(visionTurn, controller.getRawAxis(4) * 0.5);
    } else if (controller.getRawButton(7)) {
      visionMove = limelight.trackDrive();
      driveManager.subclassDrive(controller.getRawAxis(1) * 0.5, visionMove);
    }
    else {
      driveManager.drive();
    }
    
    driveManager.creep();

    shooterMotor.shooter();
    shooterMotor.shooterTemperatureAndPosition();
    autonomous.limelightAutoSet();
    autonomous.bloop();
    
    indexerMotor.indexWheel();
    /*indexerMotor.ColorSensor();
    indexerMotor.getAllianceColor();
    indexerMotor.publishAllianceColor();
    indexerMotor.getColor();
    indexerMotor.getRed();
    indexerMotor.getBlue();
    indexerMotor.isBallOurs();*/

    intakeMotor.intakeController(); 
    intakeMotor.intakeUpDown();

    climber.climberControl();
    
    shooterPotentiometer.hoodMotor();
    //shooterPotentiometer.setHood();
    shooterPotentiometer.hoodPotentiometer();
    shooterPotentiometer.hoodMotor3();
   //limelight.limelight();

    //autonomous.limelightAutoSet();

    limelight.getDistance();

   
  }




  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}