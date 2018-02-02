# BobTrajectory
A library for Generating paths using 254's 2014 code and following them via Talon SRX Motion Profiles

## Setup
1. Install e(fx)clipse for eclipse: https://www.eclipse.org/efxclipse/install.html#for-the-lazy
2. Install the included json-simple-1.1.1.jar as a third party library: http://wpilib.screenstepslive.com/s/4485/m/13503/l/682619-3rd-party-libraries
3. Configure your trajectory via the SrxTranslator.Config (wheelbase is in feet and wheel diameter is in inches).
4. Add a WaypointSequence (positive X is forward, positive Y is left, units are in feet and radians)
5. Run Main.java, you'll see the generated path in a new window.
6. Download your exported trajectory JSON file to the roborio
7. Import via the roborio with SrxTrajectoryImporter.


For more details, see the wiki: https://github.com/Team319/BobTrajectory/wiki
