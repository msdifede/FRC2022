// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;

/** An example command that uses an example subsystem. **/
public class ThreeBallAuto extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public ThreeBallAuto(IntakeSub intake, DriveSub drive, ArmSub arm){
    addCommands(
      new InstantCommand( drive::setToBrake, drive),
      new ArmDown(arm),
      new ParallelCommandGroup(
        new IntakeCommand(intake),
        new DriveDistance(drive, 5.75)
      ).withTimeout(2.5),
      new InstantCommand(intake::stop, intake),
      new ArmUp(arm),
      new Rotate(drive, 170),
      new DriveDistance(drive, 9).withTimeout(2.5),
      new OuttakeCommand(intake).withTimeout(.5),
      new DriveDistance(drive, -6.89),
      // Max dist: -7.151
      new Rotate(drive, -110),
      new ArmDown(arm),
      new ParallelCommandGroup(
        new IntakeCommand(intake),
        new DriveDistance(drive, 16.3)
      ).withTimeout(2.5),
      // Max dist: 20.368
      

      new InstantCommand(drive::setToCoast, drive)
    ); 
  }
}