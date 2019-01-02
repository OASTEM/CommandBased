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

import java.util.ArrayList;

import com.ctre.phoenix.motion.MotionProfileStatus;

import org.usfirst.frc4079.RobotBuilderProject1.Robot;
import org.usfirst.frc4079.RobotBuilderProject1.RobotMap;
import org.usfirst.frc4079.RobotBuilderProject1.motion.MotionProfileManager;

/**
 *
 */
public class DriveDistance1 extends Command {
      private double distance;
      private double leftPosition, rightPosition;
      private ArrayList<Double> LeftErrors = new ArrayList<Double>(); //ArrayList that stores error
      private ArrayList<Double> RightErrors = new ArrayList<Double>();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveDistance1(double distance, double duration) {

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
        double CurrentPosLeft = Robot.driveTrain.getLeftDistance(); //Gets current distances
        double CurrentPosRight = Robot.driveTrain.getRightDistance();
        double CurrentLeftError = leftPosition+distance-CurrentPosLeft; //Calculates current error
        double CurrentRightError = Math.abs(rightPosition-distance-CurrentPosRight);
        
        if(LeftErrors.size() < 10){
        LeftErrors.add(CurrentLeftError); //Adds current Error to list
        RightErrors.add(CurrentRightError);
        }
        else if(LeftErrors.size() == 10){
            LeftErrors.remove(0); //Removes the first value in both array lists
            RightErrors.remove(0);
            LeftErrors.add(CurrentLeftError); //Adds current error to lists
            RightErrors.add(CurrentRightError);
        }
    	Robot.driveTrain.driveToTarget(leftPosition+distance, rightPosition-distance);
        //Robot.driveTrain.drivePercentOutput(0.4, -0.4);    	
    }


    // Make this return true when this Command no longer needs to run execute()
    
    @Override
    protected boolean isFinished() {
        //Calculates average error of the lists
        double LeftSum = 0;
        double RightSum = 0;
        for(Double num: LeftErrors){
            LeftSum += num;
        }
        for(Double num: RightErrors){
            RightSum += num;
        }
        double LeftErrorAvg = LeftSum/LeftErrors.size();
        double RightErrorAvg = RightSum/RightErrors.size();
        
        //if error is less than 2 inches or timed out, returns true
        if( (LeftErrorAvg <= 2 && RightErrorAvg <= 2) || isTimedOut() ){
            return true;
        }
        return false;
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