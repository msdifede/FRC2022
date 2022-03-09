package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DPadButton extends JoystickButton{
    private final int direction;
    private final Joystick m_joystick;
    
    public DPadButton(Joystick m_joystick, int direction){
        super(m_joystick, direction);
        this.direction = direction;
        this.m_joystick = m_joystick;
    }

    public boolean get(){
        if(m_joystick.getPOV() == direction){
            return true;
        }
        return false;
    }
}