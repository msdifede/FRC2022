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
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/** An example command that uses an example subsystem. **/
public class Crazy extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  

  public Crazy(DriveSub drive, IntakeSub intake, ArmSub arm){

    addCommands(
     // new WaitCommand(),
      new OuttakeCommand(intake).withTimeout(0.5),
     // new RunCommand(() -> drive.TeleOpDrive(.25, 0.25), drive).withTimeout(3.5)
      new RunCommand(() -> drive.TeleOpCurvatureDrive(0, 0.25, 0, false), drive).withTimeout(3),
      new Rotate(drive, 180),
      new RunCommand(() -> drive.TeleOpCurvatureDrive(0.25, 0, 0, false), drive).withTimeout(0.5), 
      new ParallelCommandGroup(new IntakeCommand(intake), new ArmDown(arm)), 
      new ArmUp(arm),
      new Rotate(drive, 180), 
      new RunCommand(() -> drive.TeleOpCurvatureDrive(0.25, 0, 0, false), drive).withTimeout(3.5), 
      new OuttakeCommand(intake).withTimeout(0.5),
      new RunCommand(() -> drive.TeleOpCurvatureDrive(0, 0.25, 0, false), drive).withTimeout(3)
    );
    
    addRequirements(drive, intake, arm);

  }
}
