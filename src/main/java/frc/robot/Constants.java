// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Wheel Can IDs
    public static final int FR_FALCON = 2;
    public static final int FL_FALCON = 7;
    public static final int BR_FALCON = 1;
    public static final int BL_FALCON = 6;

    // Intake
    
    // public static final int intakeNeo2 = 35;
    public static final int intakeVictor = 0;
    public static final double intakeSpeed = -1;

    public static final double outtakeSpeed = 1;

    // Arm
    // Can ID
    public static final int ARM = 4;
    public static final int ARM_TOP = 59;
    // Other stuff
    public static final double armSpeed = 0.25;
    public static final double armSpeedDown = -0.4;
    
    public static final int climber = 5;
    public static final int CLIMBER_UP = 200000;
    public static final int climber_max = 206043;

    // public static final int CLIMBER_DOWN = 

    // Driver Ports
    public static final int DRIVER_PORT = 0;
    public static final int OPERATOR_PORT = 1;
    
    // Controller Inputs
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int BUMPER_LEFT = 5;
    public static final int BUMPER_RIGHT = 6;
    public static final int DPAD_UP = 0;
    public static final int DPAD_DOWN = 180;

    // Driver Inputs
    public static int DRIVER_LEFT_X = 0;
    public static int DRIVER_LEFT_Y = 1;
    public static int DRIVER_RTRIGGER = 3;
    public static int DRIVER_LTRIGGER = 2;
	public static int DRIVER_RIGHT_X = 4;
    public static int DRIVER_RIGHT_Y = 5;

    // Operator Inputs
    public static int OPERATOR_PINK = 3;
    public static int OPERATOR_PURPLE = 1;
    public static int OPERATOR_RED = 2;
    public static int OPERATOR_GREEN = 4;
    public static int OPERATOR_LEFT_TOP_GRAY = 5;
    public static int OPERATOR_RIGHT_TOP_GRAY = 6;
    public static int OPERATOR_LEFT_BOTTOM_GRAY = 9;
    public static int OPERATOR_RIGHT_BOTTOM_GRAY = 8;
    public static int LITTLE_GRAY_SHARE = 7;
    public static int LITTLE_GRAY_OPTION = 8;
    public static int LITTLE_GRAY_HOME = 13;

}
