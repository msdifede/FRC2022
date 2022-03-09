// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. **/
public class ShootAndMove extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  

  public ShootAndMove(DriveSub drive, IntakeSub intake){

    addCommands(
      new OuttakeCommand(intake).withTimeout(0.5),
      new DriveDistance(drive, -5)
    );

  }
}
