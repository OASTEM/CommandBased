package org.usfirst.frc4079.RobotBuilderProject1.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4079.RobotBuilderProject1.subsystems.*;

public class middle2right extends CommandGroup {
       //robot length is approx 33 inches, distance from switch to wall is around 140 inches so travel distance is arouund 110 inches
       
       public middle2right(){
            //robot will travel 100 inches (5 sec) forward while simultaneously lowering intake wrist (0.3 sec)
            addParallel(new RaiseWrist(-0.3, .3));
            addSequential(new DriveDistance(100, 2.5));
            //robot will turn 90 degrees to the right (3 seconds)
            addSequential(new TurnByAngle(90, 3));
            //distance from center to switch to center of switch plate is 4.5 feet
            addSequential(new DriveDistance(54, 2.5));
            //Simultaneously lift the elevator while turning back towards switch
            addParallel(new RaiseElevator(.5, 2));
            addSequential(new TurnByAngle(-90,2));
            //drive forward and drop cube
            addParallel(new EjectCube(.5, 3));
            addSequential(new DriveDistance(10, 3));

       }

}