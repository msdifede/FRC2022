// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;

/** An example command that uses an example subsystem. **/
public class FetchSecondBallOuttake extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public FetchSecondBallOuttake(IntakeSub intake, DriveSub drive, ArmSub arm){
    addCommands(
      new DriveDistance(drive, 6.98),
      new ArmDown(arm),
      new ParallelCommandGroup(
        new IntakeCommand(intake),
        new DriveDistance(drive, 0.75)
      ),
      new ArmUp(arm),
      new Rotate(drive, 180),
      new DriveDistance(drive, 7.5).withTimeout(3),
      new OuttakeCommand(intake)  
    ); 
  }
}