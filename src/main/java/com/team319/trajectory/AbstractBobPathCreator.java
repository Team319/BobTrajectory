
package com.team319.trajectory;

import java.util.List;

/**
 * Forked from 254's 2014 Trajectory library just a comment to make a change
 * 
 * @author Jared341
 * @author ttremblay
 */
public abstract class AbstractBobPathCreator {

	/**
	 * Generate the path files, to include config paths, display the paths in GUIS, and 
	 * move the files into the robot code project
	 */
	protected void generatePaths() {
		generateArcFiles(getArcs());
	}

	/**
	 * Return the list of arcs that are to be generated
	 */
	protected abstract List<BobPath> getArcs(); 

	private void generateArcFiles(List<BobPath> paths) {
		for (BobPath path : paths) {
			BobPathGenerator.exportArcToJavaFile(path);
		}
	}
}
