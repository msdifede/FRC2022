// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;

import javax.swing.JList.DropLocation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Rotate extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSub drivesub;
  private double tangle;
  private double start;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Rotate(DriveSub drivesub, double tangle) {
    this.drivesub = drivesub;
    this.tangle = tangle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivesub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    start = drivesub.getAngle();
    drivesub.TeleOpCurvatureDrive(0, 0, 0, false);

    SmartDashboard.putNumber("Start Angle", start);
    SmartDashboard.putNumber("Tangle Angle", start + tangle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(tangle > 0) {
      double speed = .1;
      
      //speed = log(drivesub.getAngle() - (start + tangle), Constants.logAngle);
      // if (!(drivesub.getAngle() > start + tangle - 5)) {
      //  speed = -speed;
      // } 

      // drivesub.TeleOpDrive(-speed, speed);
      drivesub.TeleOpCurvatureDrive(0, 0, -0.2, true);

    }
    else {
      drivesub.TeleOpCurvatureDrive(0, 0, 0.2, true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // drivesub.TeleOpDrive(0, 0);
    drivesub.TeleOpCurvatureDrive(0, 0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if ( tangle > 0  && (drivesub.getAngle() > (start + tangle) )){
      return true;
    } else if ( tangle < 0 && (drivesub.getAngle() < (start + tangle) )){
      return true;
    }
    return false;
  }
}
