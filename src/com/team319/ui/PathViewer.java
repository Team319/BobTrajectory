package com.team319.ui;

import javax.swing.SwingUtilities;

import com.team254.lib.trajectory.Path;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class PathViewer {

	public static void showPath(final Path path){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // this will prepare JavaFX toolkit and
								// environment
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Plotter pl = new Plotter();
						pl.plotChezyTrajectory(path);
					}
				});
			}
		});
	}

}
