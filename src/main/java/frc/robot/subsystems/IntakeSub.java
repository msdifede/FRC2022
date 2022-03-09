// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import com.revrobotics.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ArmUp;
import frc.robot.commands.IntakeCommand;

public class IntakeSub extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  /*
  private CANSparkMax intakeMotor;
  private CANSparkMax intakeMotor2;
  */
  
  private VictorSPX intakeMotor;


  public IntakeSub(VictorSPX intakeMotor) {
    this.intakeMotor = intakeMotor;
    //this.intakeMotor2 = intakeMotor2;
    //this.intakeMotor2.follow(this.intakeMotor);
  }

  public void spinIntake(double s){

    // intakeMotor.set(ControlMode.PercentOutput, s);
    intakeMotor.set(ControlMode.PercentOutput, s);
    /*
    SmartDashboard.putNumber("SPARKMaxEncoder1", intakeMotor.getEncoder().getPosition());
    SmartDashboard.putNumber("SPARKMaxEncoder2", intakeMotor.getEncoder().getPosition());
    */
    // if(intakeMotor.getSelectedSensorPosition(0) >= 360){
    //   intakeMotor.setSelectedSensorPosition(0);
    // }
    SmartDashboard.putNumber("Intake Encoder", intakeMotor.getSelectedSensorPosition(0));
    SmartDashboard.putData("Intake Command", new IntakeCommand(new IntakeSub(intakeMotor)));
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
