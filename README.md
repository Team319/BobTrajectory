# BobTrajectory
A library for Generating paths using 254's 2014 code and following them via Talon SRX Motion Profiles

## Setup
1. Add the following to your build.gradle, This allows you to pull the dependencies needed from a github repository:

   repositories {
      jcenter()
      maven { url "https://jitpack.io" }
   }

2. Add this line to the dependencies block:

    compile 'com.github.Team319:BobTrajectory:1.0.0'

3. Create a class that extends AbstractBobPathCreator.

4. Update getTeamArcs() with any paths you need. Make sure to add the paths to the list that gets returned. If you do not add them to this list they will not be generated.

5. Add a main method to your class that looks like this:

    public static void main(String[] args) {
        new YourClassName().generatePaths();
    }

6. Run your class, your paths will generate and be placed in your robot code in the frc.arcs package. A GUI window will also appear with your paths displayed in a list of tabs.


For more details, see the wiki: https://github.com/Team319/BobTrajectory/wiki