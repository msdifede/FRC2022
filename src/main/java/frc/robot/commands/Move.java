// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. **/
public class Move extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  public Move(DriveSub drive){
    drive.setToCoast();
    addCommands(
      // new RunCommand(() -> drive.TeleOpCurvatureDrive(0, 0.25, 0, false), drive).withTimeout(3.5)
      //new RunCommand(() -> drive.TeleOpDrive(.25, 0.25), drive).withTimeout(3.5)
      new DriveDistance(drive, -3)
    );

  }
}
