// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TeleopDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private DriveSub drive; 
  private double left;
  private double right;
  private double turn;
  private boolean button; 
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopDriveCommand(DriveSub drive, double left, double right, double turn, boolean button) {
    this.drive = drive; 
    this.left = left; 
    this.right = right; 
    this.turn = turn; 
    this.button = button;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    drive.TeleOpCurvatureDrive(right, left, turn, button);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    drive.TeleOpDrive(0, 0);
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
