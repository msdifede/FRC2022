/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSub extends SubsystemBase {

  private WPI_TalonFX leftClimber;
  private WPI_TalonFX rightClimber;

  private DifferentialDrive dDrive;

  private AHRS ahrs;

  /**
   * Creates a new ExampleSubsystem.
   */
  public ClimberSub(WPI_TalonFX lc, WPI_TalonFX rc) {
    leftClimber = lc;
    rightClimber = rc;


    leftClimber.setNeutralMode(NeutralMode.Brake);
    rightClimber.setNeutralMode(NeutralMode.Brake);


    rightClimber.follow(leftClimber);

    

    
    try {
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch ( RuntimeException ex ){
     // DriverStation.reportError("Error instantiating nvX " + ex.getMessage(), true);
    }
    

    TalonFXConfiguration config = new TalonFXConfiguration();
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    
    leftClimber.configAllSettings(config);
    rightClimber.configAllSettings(config);
  }

  public double getPos(){
    return(leftClimber.getSelectedSensorPosition());
  }

  public void setSpeed(double speed){
    leftClimber.set(speed);
  }
}
