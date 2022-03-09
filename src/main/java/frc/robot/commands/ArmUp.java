// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ArmSub;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ArmUp extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ArmSub arm;
  private double armStart;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArmUp(ArmSub arm) {
    this.arm = arm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
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
    if (!arm.getIsUp()){
      arm.setArmSpeed(Constants.armSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.setArmSpeed(0.03);
    arm.setIsUp(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (arm.getArmPos() < armStart + 45){
      return false;
    }
    return true;
  }
}
