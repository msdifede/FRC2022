// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ArmDown;

public class ArmSub extends SubsystemBase {

  private CANSparkMax armMotor;
  private boolean isUP = false;

  // DigitalInput topLimitSwitch = new DigitalInput(0);
  // DigitalInput bottomLimitSwitch = new DigitalInput(1);

  /** Creates a new ExampleSubsystem. */
  public ArmSub(CANSparkMax arm) {
    this.armMotor = arm;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }


  public void setArmSpeed(double speed){
    // if (speed > 0){
    //   if (topLimitSwitch.get()){
    //     armMotor.set(0.03);
    //   } else {
    //     armMotor.set(speed);
    //   }
    // } else {
    //   if (bottomLimitSwitch.get()){
    //     armMotor.set(0);
    //   } else {
    //     armMotor.set(speed);
    //   }
    // }
    armMotor.set(speed);

    SmartDashboard.putData("Arm Up", new ArmUp(new ArmSub(armMotor)));
    SmartDashboard.putData("Arm Down", new ArmDown(new ArmSub(armMotor)));
    SmartDashboard.putData("Arm 0", new RunCommand(() -> setArmPosition(0), this));
    SmartDashboard.putNumber("Arm Encoder Val", getArmPos());
   // SmartDashboard.putBoolean("Top Switch Val", topLimitSwitch.get()); 
   // SmartDashboard.putBoolean("Bottom Switch Val", bottomLimitSwitch.get());   
  }

  public void setArmPosition(double rotations){
    // armMotor.getEncoder()
    armMotor.getEncoder().setPosition(rotations);
    
  }
  public double getArmPos(){
    return armMotor.getEncoder().getPosition();
  }

  public boolean getIsUp(){
    return isUP; 
  }

  public void setIsUp(boolean isUP){
    this.isUP = isUP;
  }

}