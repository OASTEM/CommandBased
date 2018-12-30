package org.usfirst.frc4079.RobotBuilderProject1.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleToRight extends CommandGroup {
       //robot length is approx 33 inches, distance from switch to wall is around 140 inches so travel distance is arouund 110 inches
       
       public MiddleToRight(){
            /*
            //robot will travel 100 inches (5 sec) forward while simultaneously lowering intake wrist (0.3 sec)
            addParallel(new RaiseWrist(-0.3, .3));
            addSequential(new DriveDistance(100, 2.5));
            //robot will turn 90 degrees to the right (3 seconds)
            addSequential(new TurnByAngle(90, 3));
            //distance from center to switch to center of switch plate is 4.5 feet
            addSequential(new DriveDistance(54, 2.5));
            //Simultaneously lift the elevator while turning back towards switch
            addParallel(new RaiseElevator(.5, 2));
            addSequential(new TurnByAngle(-90,3));
            //drive forward and drop cube
            addParallel(new EjectCube(.5, 3));
            addSequential(new DriveDistance(10, 2));
            */

            //alternative method that may be quicker
            //robot distance to switch is approx 110 inches and middle to center of swtich plate is around 54 inches
            //robot lowers wrist while simultaneously slightly moving off of wall
            addParallel(new RaiseWrist(-0.3, .3));
            addSequential(new DriveDistance(5, 1));
            //robot rotates to face the right switch
            addSequential(new TurnByAngle(26, 2));
            //robot drives distance towards switch
            addSequential(new DriveDistance(120, 3));
            //robot turns back to face switch while lifting elevator
            addParallel(new RaiseElevator(.5, 2));
            addSequential(new TurnByAngle(-26,2));
            //robot drives forward and drops cube
            addParallel(new EjectCube(.5, 3));
            addSequential(new DriveDistance(5, 2));

       }

}