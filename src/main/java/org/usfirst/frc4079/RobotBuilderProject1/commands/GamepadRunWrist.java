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

public class GamepadRunWrist extends Command {
  public GamepadRunWrist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.oi.gamePad.getYButton()){
      Robot.wrist.run(Constants.kWristMoveUp);
    }
    else if(Robot.oi.gamePad.getAButton()){
      Robot.wrist.run(Constants.kWristMoveDown);
    }
    else{
      Robot.wrist.stop();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.wrist.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
      end();
  }
}
