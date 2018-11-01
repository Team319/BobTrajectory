# BobTrajectory
A library for Generating paths using 254's 2014 code and following them via Talon SRX Motion Profiles

## Setup
1. Install e(fx)clipse for eclipse: https://www.eclipse.org/efxclipse/install.html#for-the-lazy
2. In BobPathCreator update getConfig() to resemble your robot's physical characteristics
3. Update getRobotProjectName() with the name of your robot code project. Make sure that  the base directory for your project is in the same directory as the base of the BobTrajectory project.
4. Update getArcs() with any paths you need. Make  sure to add the paths to the asList return statement. If you do not add them to this list they will not be generated.
5. Run BobPathCreator.java, you'll see the generated path in a new window.
6. The files will be automaticall copied over to your robot code project for use in the project


For more details, see the wiki: https://github.com/Team319/BobTrajectory/wiki
