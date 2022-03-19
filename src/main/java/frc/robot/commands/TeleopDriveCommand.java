// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** An example command that uses an example subsystem. */
public class TeleopDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private DriveSub drive; 
  private Joystick driver;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopDriveCommand(DriveSub drive, Joystick driver) {
    this.drive = drive; 
    this.driver = driver;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double right = driver.getRawAxis(Constants.DRIVER_RIGHT_Y) * -.75;
    double left =  driver.getRawAxis(Constants.DRIVER_LEFT_Y) * -.75;
    //double turn =  -driver.getRawAxis(Constants.DRIVER_LEFT_X);
    //boolean button = new JoystickButton(driver, Constants.X_BUTTON).get();

    // double right = driver.getRawAxis(Constants.DRIVER_RTRIGGER) * .75;
    // double left =  driver.getRawAxis(Constants.DRIVER_LTRIGGER) * .75;
    // double turn =  -driver.getRawAxis(Constants.DRIVER_LEFT_X);
    // boolean button = new JoystickButton(driver, Constants.X_BUTTON).get();

   drive.TeleOpDrive(-left, -right);
   // drive.TeleOpCurvatureDrive(right, left, turn, button);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // drive.TeleOpCurvatureDrive(0, 0, 0, false);
   // drive.TeleOpDrive(0, 0);
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean runsWhenDisabled(){
    return false;
  }
}
