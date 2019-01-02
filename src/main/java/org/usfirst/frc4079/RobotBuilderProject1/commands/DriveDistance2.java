// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4079.RobotBuilderProject1.commands;
import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motion.MotionProfileStatus;

import org.usfirst.frc4079.RobotBuilderProject1.Robot;
import org.usfirst.frc4079.RobotBuilderProject1.RobotMap;
import org.usfirst.frc4079.RobotBuilderProject1.motion.MotionProfileManager;

/**
 *
 */
public class DriveDistance2 extends Command {
      private double distance;
      private double leftPosition, rightPosition;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveDistance2(double distance, double duration) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        this.distance=distance;
        setTimeout(duration);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	leftPosition=Robot.driveTrain.getLeftDistance();
    	rightPosition=Robot.driveTrain.getRightDistance();
    	Robot.driveTrain.configureForDrive();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.driveTrain.driveToTarget(leftPosition+distance, rightPosition-distance);
    	//Robot.driveTrain.drivePercentOutput(0.4, -0.4);
    	
    }


    // Make this return true when this Command no longer needs to run execute()
    MotionProfileStatus LeftMasterStatus = new MotionProfileStatus();
    MotionProfileStatus RightMasterStatus = new MotionProfileStatus();

    //As of right now I'm unsure if this method will work
    @Override
    protected boolean isFinished() {
       RobotMap.driveTrainLeftMaster.getMotionProfileStatus(LeftMasterStatus);
       RobotMap.driveTrainRightMaster.getMotionProfileStatus(RightMasterStatus);
        boolean PathComplete = LeftMasterStatus.activePointValid && LeftMasterStatus.isLast && RightMasterStatus.activePointValid && RightMasterStatus.isLast;
        if (PathComplete || isTimedOut()) {
            return true;
       }
       return false;
       //return isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	end();
    }
}