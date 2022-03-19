package frc.robot.commands;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class DriveDistance extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSub drivesub;
  private double distance;
  private double startPos = 0;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveDistance(DriveSub drivesub, double distance) {
    this.drivesub = drivesub;
    this.distance = distance;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivesub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //drivesub.setToBrake();
    drivesub.TeleOpDrive(0, 0);
   // drivesub.setLeftPos(0);
    startPos = drivesub.getLeftPosition();
    SmartDashboard.putNumber("Starting Encoder Val", startPos);
    SmartDashboard.putNumber("Ending Encoder Val", startPos-distance*13038);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if (distance < 0) {
      drivesub.TeleOpCurvatureDrive(0, 0.15, 0, false);
    } else {
      drivesub.TeleOpCurvatureDrive(0.3, 0, 0, false);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivesub.TeleOpCurvatureDrive(0, 0, 0, false); 
   // drivesub.setToCoast();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (distance > 0){
      if (drivesub.getLeftPosition() < (startPos - (distance*13038))) {
        return true;
      } else {
        return false;
      }
    } else {
      if (drivesub.getLeftPosition() > (startPos - (distance*13038) )){
        return true;
      } else {
        return false;
      }
    }

  }
}
