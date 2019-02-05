package com.team319.follower;

import com.ctre.phoenix.motion.BufferedTrajectoryPointStream;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team319.follower.FollowsArc;
import com.team319.follower.SrxMotionProfile;
import com.team319.follower.SrxTrajectory;

import edu.wpi.first.wpilibj.command.Command;

public class FollowArc extends Command {

	int distancePidSlot = 0;
	int rotationPidSlot = 1;
	SrxTrajectory trajectory;
	boolean flipLeftAndRight;
	boolean flipRobot;
	BufferedTrajectoryPointStream buffer = new BufferedTrajectoryPointStream();
	FollowsArc drivetrain;

	public FollowArc(FollowsArc drivetrain, SrxTrajectory trajectory) {
		this(drivetrain, trajectory, false, false);
	}

	public FollowArc(FollowsArc drivetrain, SrxTrajectory trajectory, 
			boolean flipLeftAndRight, boolean flipRobot) {
		requires(drivetrain.getRequiredSubsystem());
		this.drivetrain = drivetrain;
		this.trajectory = trajectory;
		this.flipLeftAndRight = flipLeftAndRight;
		this.flipRobot = flipRobot;
	}

	protected void initialize() {
		setUpTalon(drivetrain.getLeft());
		setUpTalon(drivetrain.getRight());
		
		loadBuffer(trajectory, drivetrain.getDistance(), flipLeftAndRight, flipRobot);

		drivetrain.getLeft().follow(drivetrain.getRight(), FollowerType.AuxOutput1);
		drivetrain.getRight().startMotionProfile(buffer, 10, ControlMode.MotionProfileArc);
	}

	protected boolean isFinished() {
		return drivetrain.getRight().isMotionProfileFinished();
	}

	protected void end() {
		resetTalon(drivetrain.getRight(), ControlMode.PercentOutput, 0);
		resetTalon(drivetrain.getLeft(), ControlMode.PercentOutput, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		drivetrain.getRight().clearMotionProfileTrajectories();
		resetTalon(drivetrain.getRight(), ControlMode.PercentOutput, 0);
		resetTalon(drivetrain.getLeft(), ControlMode.PercentOutput, 0);
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

	private void loadBuffer(SrxTrajectory trajectory, double startPosition, boolean flipLeftAndRight, boolean flipRobot) {

        TrajectoryPoint point = new TrajectoryPoint(); // temp for for loop, since unused params are initialized
                                                       // automatically, you can alloc just one

        /* clear the buffer, in case it was used elsewhere */
        buffer.Clear();
		SrxMotionProfile profile = trajectory.centerProfile;

		double direction = flipRobot ? -1 : 1;
		double flippedLeftAndRight = flipLeftAndRight ? -1 : 1;
		double[][] path = profile.points;
        /* Insert every point into buffer, no limit on size */
        for (int i = 0; i < profile.numPoints; ++i) {
            /* for each point, fill our structure and pass it to API */
            point.timeDur = (int)path[i][2];

            /* drive part */
            point.position = direction * path[i][0] + startPosition;
            point.velocity = direction * path[i][1]; 
            point.arbFeedFwd = 0; 

            /* turn part */
            point.auxiliaryPos = flippedLeftAndRight * 10 * (path[i][3]);
            point.auxiliaryVel = 0;
            point.auxiliaryArbFeedFwd = 0;

            point.profileSlotSelect0 = distancePidSlot;
            point.profileSlotSelect1 = rotationPidSlot;
            point.zeroPos = false;
            point.isLastPoint = ((i + 1) == profile.numPoints);
            point.useAuxPID = true;

            buffer.Write(point);
        }
	}
}