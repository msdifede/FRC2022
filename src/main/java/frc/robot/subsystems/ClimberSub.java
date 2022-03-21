/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSub extends SubsystemBase {

  private WPI_TalonFX leftClimber;
  // private WPI_TalonFX rightClimber;

  /**
   * Creates a new ExampleSubsystem.
   */
  public ClimberSub(WPI_TalonFX lc) {
    leftClimber = lc;
    leftClimber.setSelectedSensorPosition(0);
    // rightClimber = rc;

    leftClimber.setNeutralMode(NeutralMode.Brake);
    // rightClimber.setNeutralMode(NeutralMode.Brake);

    // rightClimber.follow(leftClimber);
  }
  // 206043
  // 200000
  // 24803
  public double getPos(){
    return leftClimber.getSelectedSensorPosition(0);
  }

  // public double getRightPos(){
  //   // return rightClimber.getSelectedSensorPosition(0);
  // }

  public void setSpeed(double speed){
    leftClimber.set(speed);
    // rightClimber.set(speed);
    SmartDashboard.putNumber("Left Climber Encoder", getPos());
    // SmartDashboard.putNumber("Right Climber Encoder", getRightPos());

  }
}
