# BobTrajectory
A library for Generating paths using 254's 2014 code and following them via Talon SRX Motion Profiles

## Setup
1. Install e(fx)clipse for eclipse: https://www.eclipse.org/efxclipse/install.html#for-the-lazy
2. Install the included json-simple-1.1.1.jar as a third party library: http://wpilib.screenstepslive.com/s/4485/m/13503/l/682619-3rd-party-libraries
3. Configure your trajectory via the SrxTranslator.Config (wheelbase is in feet and wheel diameter is in inches).
4. Add a WaypointSequence (positive X is forward, positive Y is left, units are in feet and degrees)
5. Export the BobPaths via the ExportPathToJavaFile method.
6. Run Main.java, you'll see the generated path in a new window.
7. Move the generated java files from the Paths directory to a package in your robot code called `org.usfirst.frc.team319.paths`
8. Download your code to your robot.
9. Follow with `FollowTrajectory(new PathToFollow())`


For more details, see the wiki: https://github.com/Team319/BobTrajectory/wiki
