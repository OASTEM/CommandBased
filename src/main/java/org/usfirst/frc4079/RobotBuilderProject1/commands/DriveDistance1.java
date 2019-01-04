/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4079.RobotBuilderProject1.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4079.RobotBuilderProject1.Robot;

public class DriveDistance1 extends Command {
  private double distance, threshold;
  private double leftPos, rightPos;
  private double error;
  private double[] errors;
  private int counter;
  private double avg;
  public DriveDistance1(double dist, double duration, double thresh) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
    distance = dist;
    threshold = thresh;
    error = 0;
    errors = new double[10];
    counter = 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    leftPos = Robot.driveTrain.getLeftDistance();
    rightPos = Robot.driveTrain.getRightDistance();
    Robot.driveTrain.configureForDrive();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.driveToTarget(leftPos+distance, rightPos-distance);
    error = (leftPos + distance) - Robot.driveTrain.getLeftDistance();
    error = errors[counter];
    if (counter == errors.length - 1) {
      counter = 0;
    }
    else counter++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    for (int i = 0; i < errors.length; i++) {
      avg += errors[i];
    }
    avg /= errors.length;
    return (avg < threshold);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
