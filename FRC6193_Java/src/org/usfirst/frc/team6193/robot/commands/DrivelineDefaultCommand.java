package org.usfirst.frc.team6193.robot.commands;


import org.usfirst.frc.team6193.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivelineDefaultCommand extends Command {

    public DrivelineDefaultCommand() {
    	requires(Robot.driveline);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveline.Drive();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveline.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
