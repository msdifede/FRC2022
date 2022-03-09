// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.DPadButton;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ArmDown;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.Move;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.Rotate;
import frc.robot.commands.Shoot;
import frc.robot.commands.ShootAndMove;
import frc.robot.commands.ShootAndMoveAndRotate;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  
  private final DriveSub drivetrain = 
    new DriveSub(
      new WPI_TalonFX(Constants.FR_FALCON),
      new WPI_TalonFX(Constants.FL_FALCON), 
      new WPI_TalonFX(Constants.BR_FALCON), 
      new WPI_TalonFX(Constants.BL_FALCON)
    );
  
  
  private final IntakeSub intake =
    new IntakeSub(
      
      // new CANSparkMax(Constants.intakeNeo1, MotorType.kBrushless)
      // new CANSparkMax(Constants.intakeNeo2, MotorType.kBrushless)
      
      new VictorSPX(Constants.intakeVictor)
    );
  
    private final ArmSub arm = new ArmSub(new CANSparkMax(Constants.ARM, MotorType.kBrushless));

  private final Command m_simpleAuto1 = new Shoot(intake);
  private final Command m_simpleAuto2 = new Move(drivetrain); 
  private final Command m_complexAuto1 = new ShootAndMove(drivetrain, intake);
  private final Command m_complexAuto2 = new ShootAndMoveAndRotate(drivetrain, intake, arm);
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  
  private final Joystick driver = new Joystick(Constants.DRIVER_PORT);
  // private final Joystick operator = new Joystick(Constants.OPERATOR_PORT);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_chooser.setDefaultOption("Shoot", m_simpleAuto1);
    m_chooser.addOption("Move", m_simpleAuto2);
    m_chooser.addOption("Shoot and Move", m_complexAuto1);
    m_chooser.addOption("Shoot, Move, and Rotate", m_complexAuto2);
    
    SmartDashboard.putData(m_chooser); 
    SmartDashboard.putData("Rotate 180", new Rotate(drivetrain, 180));


    drivetrain.setDefaultCommand(new TeleopDriveCommand(drivetrain, driver.getRawAxis(Constants.DRIVER_RTRIGGER) * .75, 
    driver.getRawAxis(Constants.DRIVER_LTRIGGER) * .75, -driver.getRawAxis(Constants.DRIVER_LEFT_X), new JoystickButton(driver, Constants.X_BUTTON).get()));
    
    // drivetrain.setDefaultCommand(
    //   new RunCommand(() -> drivetrain.TeleOpCurvatureDrive(
    //     driver.getRawAxis(Constants.DRIVER_RTRIGGER) * .75, 
    //     driver.getRawAxis(Constants.DRIVER_LTRIGGER) * .75,
    //     -driver.getRawAxis(Constants.DRIVER_LEFT_X), new JoystickButton(driver, Constants.X_BUTTON).get()), drivetrain));   

    
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton driver_Y = new JoystickButton(driver, Constants.Y_BUTTON);
    driver_Y.whileHeld(new OuttakeCommand(intake));

    JoystickButton driver_B_RIGHT = new JoystickButton(driver, Constants.BUMPER_RIGHT);
    driver_B_RIGHT.whileHeld(new IntakeCommand(intake));
   
    // JoystickButton driver_Y = new JoystickButton(driver, Constants.Y_BUTTON);
    // driver_Y.whenPressed(new ArmUp(arm)); 

    // JoystickButton driver_A = new JoystickButton(driver, Constants.A_BUTTON);
    // driver_A.whenPressed(new ArmDown(arm));

    DPadButton driver_DOWN = new DPadButton(driver, Constants.DPAD_DOWN);
    driver_DOWN.whenPressed(new ArmDown(arm));

    DPadButton driver_UP = new DPadButton(driver, Constants.DPAD_UP);
    driver_UP.whenPressed(new ArmUp(arm));


    
    JoystickButton driver_A = new JoystickButton(driver, Constants.A_BUTTON);
   //driver_A.whenPressed(new RunCommand(() -> setArmSpeed(0), arm));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return new DriveCommand(drivetrain, 0.5).withTimeout(3);
    // return new DriveDistance(drivetrain, 0.5); 
    return m_chooser.getSelected();

  }

}