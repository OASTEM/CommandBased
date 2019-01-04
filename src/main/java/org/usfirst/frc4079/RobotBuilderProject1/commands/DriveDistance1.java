/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4079.RobotBuilderProject1.commands;

import org.usfirst.frc4079.RobotBuilderProject1.Constants;
import org.usfirst.frc4079.RobotBuilderProject1.Robot;

import edu.wpi.first.wpilibj.command.Command;

import java.util.ArrayList;

public class DriveDistance1 extends Command {

  private double distance; 
  private double leftPosition, rightPosition;
  private ArrayList<Double> errors;
  private int threshold;

  public DriveDistance1(double distance, int threshold) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
    this.distance = distance;
    this.threshold = threshold;
    errors.add(distance);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    leftPosition = Robot.driveTrain.getLeftDistance();
    rightPosition = Robot.driveTrain.getRightDistance();
    Robot.driveTrain.configureForDrive();
    errors.add(distance);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.driveToTarget(leftPosition+distance, rightPosition-distance);

    double error = (leftPosition+distance)-Robot.driveTrain.getLeftDistance();

    if(errors.size() == Constants.kSampleSize){
      errors.remove(0);
    }
    errors.add(error);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(getAvgError() < threshold){
      return true;
    }
    else{
      return false;
    }

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

  private double getAvgError(){
    double sum = 0;
    for(int i = 0; i < errors.size(); i++){
      sum += errors.get(i);
    }
    return sum/errors.size();
  }
}