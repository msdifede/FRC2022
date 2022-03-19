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
public class ImmediateBallOuttake extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public ImmediateBallOuttake(IntakeSub intake, DriveSub drive, ArmSub arm){
    addCommands(
      new InstantCommand( drive::setToBrake, drive),
      new OuttakeCommand(intake).withTimeout(0.5),
      new DriveDistance(drive, -2.49),
      new Rotate(drive, -170),
      new ArmDown(arm),
      new ParallelCommandGroup(
        new IntakeCommand(intake),
        new DriveDistance(drive, 3.75)
      ).withTimeout(1.5),
      
      // new ArmDown(arm),
      // new IntakeCommand(intake),
      // new DriveDistance(drive, 0.75),
      new InstantCommand(intake::stop, intake),
      new ArmUp(arm),
      new Rotate(drive, 170),
      new DriveDistance(drive, 8).withTimeout(1.5),
      new OuttakeCommand(intake).withTimeout(1),
      new InstantCommand( drive::setToCoast, drive)
    ); 
  }
}
