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

public class DriveSub extends SubsystemBase {

  private WPI_TalonFX frontRightMotor;
  private WPI_TalonFX frontLeftMotor;
  private WPI_TalonFX backRightMotor;
  private WPI_TalonFX backLeftMotor;

  private DifferentialDrive dDrive;

  private AHRS ahrs;

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveSub(WPI_TalonFX fr, WPI_TalonFX fl, WPI_TalonFX br, WPI_TalonFX bl) {
    super();
    frontRightMotor = fr;
    frontLeftMotor = fl;
    backRightMotor = br;
    backLeftMotor = bl;
    


    frontRightMotor.setNeutralMode(NeutralMode.Coast);
    frontLeftMotor.setNeutralMode(NeutralMode.Coast);
    backRightMotor.setNeutralMode(NeutralMode.Coast);
    backLeftMotor.setNeutralMode(NeutralMode.Coast);


    backRightMotor.follow(frontRightMotor);
    backLeftMotor.follow(frontLeftMotor);

    frontLeftMotor.setInverted(false);
    frontRightMotor.setInverted(true);

    backRightMotor.setInverted(InvertType.FollowMaster);
    backLeftMotor.setInverted(InvertType.FollowMaster);

    dDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    
    try {
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch ( RuntimeException ex ){
     // DriverStation.reportError("Error instantiating nvX " + ex.getMessage(), true);
    }
    

    TalonFXConfiguration config = new TalonFXConfiguration();
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    
    frontLeftMotor.configAllSettings(config);
    frontRightMotor.configAllSettings(config);
  }

  public void TeleOpDrive(double left, double right){
    // frontLeftMotor.set(ControlMode.PercentOutput, left);
    // frontRightMotor.set(ControlMode.PercentOutput, right);
    dDrive.tankDrive(left, right);

    
   // SmartDashboard.putNumber("Gyro", getAngle());
    // SmartDashboard.putNumber("Left Encoder", frontLeftMotor.getSelectedSensorPosition(0));
    // SmartDashboard.putNumber("Right Encoder", frontRightMotor.getSelectedSensorPosition(0));
  }
  /*
  public void TeleOpDrivePOV(double fdrive, double bdrive, double turn){
    // dDrive.arcadeDrive(bdrive - fdrive, -turn * .7);

    // dDrive.tankDrive(left, right);
    SmartDashboard.putNumber("Gyro", getAngle());
    SmartDashboard.putNumber("Left Encoder", frontLeftMotor.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Right Encoder", frontRightMotor.getSelectedSensorPosition(0));
  }
*/
  public void TeleOpCurvatureDrive(double fdrive, double bdrive, double turn, boolean button){
    dDrive.curvatureDrive(bdrive - fdrive, turn, button);
    //dDrive.curvatureDrive(bdrive - fdrive, turn*7, button);

    // dDrive.tankDrive(left, right);
    SmartDashboard.putNumber("Gyro", getAngle());
    SmartDashboard.putNumber("Left Encoder", getLeftPosition());
    SmartDashboard.putNumber("Right Encoder", getRightPosition());

  }


  
  public void resetGyro(){
    ahrs.reset();
  }

  public double getAngle(){
    return ahrs.getAngle();
  }
  

  public double getLeftPosition() {

    return frontLeftMotor.getSelectedSensorPosition(0);
  }

  public void setLeftPos(double pos){
    frontLeftMotor.setSelectedSensorPosition(pos);
  }

  public void setRightPos(double pos){
    frontRightMotor.setSelectedSensorPosition(pos);
  }

  public double getRightPosition() {
    return frontRightMotor.getSelectedSensorPosition(0);
  }

  public void setToBrake(){
    frontRightMotor.setNeutralMode(NeutralMode.Brake);
    frontLeftMotor.setNeutralMode(NeutralMode.Brake);
    backRightMotor.setNeutralMode(NeutralMode.Brake);
    backLeftMotor.setNeutralMode(NeutralMode.Brake);
  }
  public void setToCoast(){
    frontRightMotor.setNeutralMode(NeutralMode.Coast);
    frontLeftMotor.setNeutralMode(NeutralMode.Coast);
    backRightMotor.setNeutralMode(NeutralMode.Coast);
    backLeftMotor.setNeutralMode(NeutralMode.Coast); 
  }

  // public void driveMagic(double distance){
  //   setLeftPos(0);
  //   setRightPos(0);
  //   frontRightMotor.set(ControlMode.MotionMagic, distance);
  //   frontLeftMotor.set(ControlMode.MotionMagic, distance); 
  // }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("LeftFront Motor", frontLeftMotor.getMotorOutputVoltage());
  }
}
