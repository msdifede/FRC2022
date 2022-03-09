package frc.robot.commands;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

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
    drivesub.TeleOpDrive(0, 0);
    startPos = drivesub.getLeftPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if (distance < 0) {
      drivesub.TeleOpDrive(-0.15, -0.15);
    } else {
      drivesub.TeleOpDrive(0.15, 0.15);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivesub.TeleOpDrive(0, 0); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (distance < 0){
      if ( Math.abs( drivesub.getLeftPosition() - (startPos - distance * 182120 ) ) < 10000 ) {
        return true;
      } else {
        return false;
      }
    } else {
      if ( Math.abs( drivesub.getLeftPosition() - (startPos + distance * 182120 ) ) < 10000 ) {
        return true;
      } else {
        return false;
      }
    }

  }
}
