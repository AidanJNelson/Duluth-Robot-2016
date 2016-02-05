package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import xyz.remexre.robotics.frc2016.util.MultiSpeedController;

/**
 * DriveTrain is a class that manages the robot's drive train as a whole.
 * @author Nathan Ringo
 */
public class DriveTrain {
	private RobotDrive drive;
	// TODO Some sort of safety/max speed?

	/**
	 * Constructs a drive train from motor IDs. Assumes that all motors are CANTalons.
	 * @param frontLeftMotorID The ID of the front-left motor.
	 * @param backLeftMotorID The ID of the back-left motor.
	 * @param frontRightMotorID The ID of the front-right motor.
	 * @param backRightMotorID The ID of the back-right motor.
	 */
	public DriveTrain(int frontLeftMotorID,
			int backLeftMotorID,
			int frontRightMotorID,
			int backRightMotorID) {
		this(new CANTalon(frontLeftMotorID), new CANTalon(backLeftMotorID),
			new CANTalon(frontRightMotorID), new CANTalon(backRightMotorID));
	}
	/**
	 * Constructs a drive train based on its constituent motors.
	 * @param frontLeft The front-left motor.
	 * @param backLeft The back-left motor.
	 * @param frontRight The front-right motor.
	 * @param backRight The back-right motor.
	 */
	public DriveTrain(SpeedController frontLeft,
			SpeedController backLeft,
			SpeedController frontRight,
			SpeedController backRight) {
		this(new MultiSpeedController(frontLeft, backLeft),
				new MultiSpeedController(frontRight, backRight));
	}
	/**
	 * Constructs a drive train based on its left and right motor components.
	 * @param left The left half of the drive train.
	 * @param right The right half of the drive train.
	 */
	public DriveTrain(SpeedController left, SpeedController right) {
		this.drive = new RobotDrive(left, right);
	}

	/**
	 * Immediately triggers the brakes. If brakes are not enabled, will simply
	 * cut power to the motors.
	 */
	public void brake() { this.drive.stopMotor(); }
	
	/**
	 * Steers the drive train based on the joystick's state.
	 * @param joystick The joystick to use.
	 */
	public void drive(Joystick joystick) {
		double speed = joystick.getY();
		double turn = joystick.getX();
		this.drive(speed, turn);
	}
	
	/**
	 * Steers the drive train based on a speed and direction.
	 * @param speed The speed to drive at.
	 * @param turn The amount of turning to do.
	 */
	public void drive(double speed, double turn) {
		this.drive.arcadeDrive(speed, turn);
	}
}