package org.usfirst.frc.team6193.robot.commands;

import org.usfirst.frc.team6193.robot.Robot;
import org.usfirst.frc.team6193.robot.lib.DrivelinePIDMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class will create a command that will move the robot
 * using a PID control method. 
 */
public class DrivelineMoveCommand extends Command {
	private double m_distance = 0.0;
	private double m_maxTimeout = 0.0;
	private double m_speed = 0.0;
	private double m_percentTolerance = 0.0;
	private double m_p = 0.0;
	private double m_i = 0.0;
	private double m_d = 0.0;
	private double m_f = 0.0;
	/**
	 * 
	 * @param distance Distance to drive in inches
	 * @param speed The speed to drive at from -1.0 to 1.0
	 * @param timeout A timeout if distance not reached
	 * @param percentTolerance See formula of PIDController percent tolerance
	 * @param p Proportional
	 * @param i Integral
	 * @param d Derivative
	 * @param f Feed Forward
	 */
    public DrivelineMoveCommand(double distance, double speed, double timeout, double percentTolerance, double p, double i, double d, double f) {
    	m_distance = distance;
    	m_maxTimeout = timeout;
    	m_speed = speed;
    	m_percentTolerance = percentTolerance;
    	m_p = p;
    	m_i = i;
    	m_d = d;
    	m_f = f;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveline);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveline.setInputRange(-m_distance, m_distance);
    	Robot.driveline.setOutputRange(-m_speed,m_speed);
    	Robot.driveline.setPercentTolerance(m_percentTolerance);
    	Robot.driveline.setSetpoint(m_distance);
    	Robot.driveline.setPIDMode(DrivelinePIDMode.MOVE, m_p, m_i, m_d, m_f);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timeSinceInitialized() > m_maxTimeout || Robot.driveline.onTarget()){
    		return true;
    	}else {
    		return false;
    	}
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
