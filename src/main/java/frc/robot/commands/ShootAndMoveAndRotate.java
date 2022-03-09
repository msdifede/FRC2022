// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. **/
public class ShootAndMoveAndRotate extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  

  public ShootAndMoveAndRotate(DriveSub drive, IntakeSub intake, ArmSub arm){

    addCommands(
      new OuttakeCommand(intake).withTimeout(0.5),
      new DriveDistance(drive, -5).withTimeout(2),
      new Rotate(drive, 180),
      new ParallelCommandGroup(new DriveDistance(drive, 10), new IntakeCommand(intake)).withTimeout(1),
      new Rotate(drive, 180),
      new ArmUp(arm),
      new DriveDistance(drive, 15).withTimeout(3),
      new OuttakeCommand(intake).withTimeout(0.5)
    ); 

  }
}
