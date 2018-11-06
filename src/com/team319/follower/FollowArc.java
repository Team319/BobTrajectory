package com.team319.follower;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import frc.models.BobTalonSRX;
import frc.models.SrxMotionProfile;
import frc.models.SrxTrajectory;
import frc.robot.subsystems.Drivetrain;

public class FollowArc extends Command {

	private BobTalonSRX rightTalon = Drivetrain.getInstance().getRight();
	private BobTalonSRX leftTalon = Drivetrain.getInstance().getLeft();

	private int distancePidSlot = 0;
	private int rotationPidSlot = 1;
	private int kMinPointsInTalon = 5;
	private boolean isFinished = false;
	private SrxTrajectory trajectoryToFollow = null;
	private MotionProfileStatus status = new MotionProfileStatus();
	private boolean hasPathStarted;

	/**
	 * this is only either Disable, Enable, or Hold. Since we'd never want one side
	 * to be enabled while the other is disabled, we'll use the same status for both
	 * sides.
	 */
	private SetValueMotionProfile setValue = SetValueMotionProfile.Disable;

	private class BufferLoader implements java.lang.Runnable {
		private int lastPointSent = 0;
		private TalonSRX talon;
		private SrxMotionProfile prof;
		private final boolean flipped;
		private double startPosition = 0;

		public BufferLoader(TalonSRX talon, SrxMotionProfile prof, boolean flipped, double startPosition) {
			this.talon = talon;
			this.prof = prof;
			this.flipped = flipped;
			this.startPosition = startPosition;
		}

		public void run() {
			talon.processMotionProfileBuffer();

			if (lastPointSent >= prof.numPoints) {
				return;
			}

			if (!talon.isMotionProfileTopLevelBufferFull() && lastPointSent < prof.numPoints) {
				TrajectoryPoint point = new TrajectoryPoint();
				/* for each point, fill our structure and pass it to API */
				point.position = prof.points[lastPointSent][0] + startPosition;
				point.velocity = prof.points[lastPointSent][1];
				point.timeDur = TrajectoryDuration.Trajectory_Duration_10ms;
				point.auxiliaryPos = (flipped ? -1 : 1) * 10 * (prof.points[lastPointSent][3]);
				point.profileSlotSelect0 = distancePidSlot;
				point.profileSlotSelect1 = rotationPidSlot;
				point.zeroPos = false;
				point.isLastPoint = false;
				if ((lastPointSent + 1) == prof.numPoints) {
					point.isLastPoint = true; /** set this to true on the last point */
				}

				talon.pushMotionProfileTrajectory(point);
				lastPointSent++;
				hasPathStarted = true;
			}
		}
	}

	// Runs the runnable
	private Notifier buffer;

	public FollowArc(SrxTrajectory trajectoryToFollow) {
		requires(Drivetrain.getInstance());
		this.trajectoryToFollow = trajectoryToFollow;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		HelixEvents.getInstance().addEvent("DRIVETRAIN", "Starting to follow arc: " + trajectoryToFollow.getClass().getSimpleName());
		setUpTalon(leftTalon);
		setUpTalon(rightTalon);

		setValue = SetValueMotionProfile.Disable;

		rightTalon.set(ControlMode.MotionProfileArc, setValue.value);
		leftTalon.follow(rightTalon, FollowerType.AuxOutput1);
		buffer = new Notifier(
				new BufferLoader(rightTalon, trajectoryToFollow.centerProfile, trajectoryToFollow.flipped,
						Drivetrain.getInstance().getDistance()));

		buffer.startPeriodic(.005);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		rightTalon.getMotionProfileStatus(status);

		if (status.isUnderrun) {
			// if either MP has underrun, stop both
			HelixEvents.getInstance().addEvent("DRIVETRAIN", trajectoryToFollow.getClass().getSimpleName() + " has underrun!");
			setValue = SetValueMotionProfile.Disable;
		} else if (status.btmBufferCnt > kMinPointsInTalon) {
			// if we have enough points in the talon, go.
			setValue = SetValueMotionProfile.Enable;
		} else if (status.activePointValid && status.isLast) {
			// if both profiles are at their last points, hold the last point
			setValue = SetValueMotionProfile.Hold;
		}

		rightTalon.set(ControlMode.MotionProfileArc, setValue.value);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (!hasPathStarted) {
			return false;
		}
		boolean leftComplete = status.activePointValid && status.isLast;
		boolean trajectoryComplete = leftComplete;
		if (trajectoryComplete) {
			HelixEvents.getInstance().addEvent("DRIVETRAIN", "Finished following arc: " + trajectoryToFollow.getClass().getSimpleName());
		}
		return trajectoryComplete || isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		buffer.stop();
		// resetTalon(rightTalon, ControlMode.PercentOutput, 0);
		// resetTalon(leftTalon, ControlMode.PercentOutput, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		buffer.stop();
		resetTalon(rightTalon, ControlMode.PercentOutput, 0);
		resetTalon(leftTalon, ControlMode.PercentOutput, 0);
	}

	// set up the talon for motion profile control
	private void setUpTalon(TalonSRX talon) {
		talon.clearMotionProfileTrajectories();
		talon.changeMotionControlFramePeriod(5);
		talon.clearMotionProfileHasUnderrun(10);
	}

	// set the to the desired controlMode
	// used at the end of the motion profile
	private void resetTalon(TalonSRX talon, ControlMode controlMode, double setValue) {
		talon.clearMotionProfileTrajectories();
		talon.clearMotionProfileHasUnderrun(10);
		talon.changeMotionControlFramePeriod(10);
		talon.set(controlMode, setValue);
	}
}