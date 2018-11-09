package com.team319.ui;

import javax.swing.SwingUtilities;

import com.team254.lib.trajectory.Path;
import com.team319.trajectory.SrxTranslatorConfig;

public class PathViewer {

	private static Viewer viewer = new Viewer();

	public static void showPath(final Path path, SrxTranslatorConfig config) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				viewer.addPath(path, config);
			}
		});
	}

}
