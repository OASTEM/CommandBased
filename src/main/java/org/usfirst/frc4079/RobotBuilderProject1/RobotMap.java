// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4079.RobotBuilderProject1;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
   
   // public static WPI_TalonSRX elevatorMotor;
    public static DigitalInput elevatorLimitSwitchUp;
    public static DigitalInput elevatorLimitSwitchDown;
    public static SpeedController intakeLeftMaster;
    public static SpeedController intakeRightMaster;
    public static SpeedController wristMotor;
    public static DigitalInput wristLimitSwitchDown;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	    public static TalonSRX driveTrainLeftMaster;
	    public static TalonSRX driveTrainLeftSlave;
	    public static TalonSRX driveTrainRightMaster;
	    public static TalonSRX driveTrainRightSlave;
	    public static AHRS driveTrainNavX1;
        public static Talon elevatorMotor;
        public static Encoder elevatorEncoder;

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        
        
        elevatorLimitSwitchUp = new DigitalInput(0);
        LiveWindow.addSensor("Elevator", "Limit Switch Up", elevatorLimitSwitchUp);
        
        elevatorLimitSwitchDown = new DigitalInput(1);
        LiveWindow.addSensor("Elevator", "Limit Switch Down", elevatorLimitSwitchDown);
        
        intakeLeftMaster = new Spark(0);
        LiveWindow.addActuator("Intake", "LeftMaster", (Spark) intakeLeftMaster);
        intakeLeftMaster.setInverted(false);
        intakeRightMaster = new Spark(1);
        LiveWindow.addActuator("Intake", "RightMaster", (Spark) intakeRightMaster);
        intakeRightMaster.setInverted(false);
        wristMotor = new Spark(2);
        LiveWindow.addActuator("Wrist", "Motor", (Spark) wristMotor);
        wristMotor.setInverted(false);
        wristLimitSwitchDown = new DigitalInput(2);
        LiveWindow.addSensor("Wrist", "Limit Switch Down", wristLimitSwitchDown);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainLeftMaster = new TalonSRX(1);  
        driveTrainLeftSlave = new TalonSRX(2);
        driveTrainRightMaster = new TalonSRX(3);
        driveTrainRightSlave = new TalonSRX(0);
        
        elevatorMotor = new Talon(4);
        elevatorEncoder = new Encoder(4, 3, false);
        driveTrainNavX1 = new AHRS(Port.kMXP, (byte)50);
        
    }
}
