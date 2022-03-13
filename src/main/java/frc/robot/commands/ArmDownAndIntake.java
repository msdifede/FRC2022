// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.IntakeSub;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ArmDownAndIntake extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ArmSub arm;
  private final IntakeSub intake; 
  private double armStart;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArmDownAndIntake(ArmSub arm, IntakeSub intake) {
    this.arm = arm;
    this.intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    // Not sure about the , tactic
    addRequirements(arm, intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    armStart = arm.getArmPos(); 
    // arm.setArmPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // arm.setArmSpeed(Constants.armSpeed);
    // If the arm is up we go down meaning it is NOT down
    //if(!arm.isDOWN()){
        arm.setArmSpeed(Constants.armSpeedDown);
        intake.spinIntake(Constants.intakeSpeed);
   //}
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.spinIntake(0);
    arm.setIsUp(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   // if (arm.getArmPos() + 10 > armStart){'
  //  if( arm.getArmPos() <= 0 || arm.isDOWN()){
  //   // arm.setArmPosition(0);
  //     return true;
  //   }
    return false;
  }
}