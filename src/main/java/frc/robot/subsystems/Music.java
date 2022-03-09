package frc.robot.subsystems;

import javax.sound.midi.Instrument;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Music extends SubsystemBase{
    WPI_TalonFX fr = new WPI_TalonFX(Constants.FR_FALCON);
    WPI_TalonFX fl = new WPI_TalonFX(Constants.FL_FALCON);
    WPI_TalonFX br = new WPI_TalonFX(Constants.BR_FALCON);
    WPI_TalonFX bl = new WPI_TalonFX(Constants.BL_FALCON);

    Orchestra music = new Orchestra();
    
  // music.addInstrument(fr);
  //  music.addInstrument(fl);
  //  music.addInstrument(br);
  //  music.addInstrument(bl);

    
}
