package com.team319.ui;

import java.text.DecimalFormat;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory.Segment;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.*;

public class Plotter {
	
	public Plotter(){}
	
	public void plotChezyTrajectory(Path path) {
		Stage stage = new Stage();
		
		stage.setTitle("Scatter Chart Sample");
		
		final NumberAxis xAxis = new NumberAxis(getMinXY(path)[0]-2.5, getMaxXY(path)[0]+2.5, .5);
		final NumberAxis yAxis = new NumberAxis(getMinXY(path)[1]-2.5, getMaxXY(path)[1]+2.5, .5);
		final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);
		xAxis.setLabel("x");
		yAxis.setLabel("y");
		DecimalFormat df = new DecimalFormat("0.00##");
		StringBuilder title = new StringBuilder();
		title.append(path.getName()).append(" : ")
		.append(df.format(path.getLeftWheelTrajectory().getNumSegments() * path.getLeftWheelTrajectory().getSegment(0).dt))
		.append("s");
		sc.setTitle(title.toString());

		ScatterChart.Series series1 = new ScatterChart.Series();
		
		series1.setName("Left");
		for (int i = 0; i < path.getPair().left.getNumSegments(); i++) {
			series1.getData().add(new XYChart.Data(path.getPair().left.getSegment(i).x, path.getPair().left.getSegment(i).y));
		}

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Right");
		for (int i = 0; i < path.getPair().left.getNumSegments(); i++) {
			series2.getData().add(new XYChart.Data(path.getPair().right.getSegment(i).x, path.getPair().right.getSegment(i).y));
		}
		
		sc.getData().addAll(series1, series2);
		Scene scene = new Scene(sc, 1000, 800);
		scene.getStylesheets().clear();
		scene.getStylesheets().add(getClass().getResource("Plotter.css").toExternalForm());		
		stage.setScene(scene);
		stage.show();
	}
	/*
	public void plotChezyTrajectory(Path path, double xMin, double xMax, double yMin, double yMax) {
		Stage stage = new Stage();
		stage.setTitle("Scatter Chart Sample");
		
		final NumberAxis xAxis = new NumberAxis(xMin, xMax, .5);
		final NumberAxis yAxis = new NumberAxis(yMin, yMax, .5);
		final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);
		xAxis.setLabel("x");
		yAxis.setLabel("y");
		sc.setTitle(path.getName());

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Left");
		for (int i = 0; i < path.getPair().left.getNumSegments(); i++) {
			series1.getData().add(new XYChart.Data(path.getPair().left.getSegment(i).x, path.getPair().left.getSegment(i).y));
		}

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Right");
		for (int i = 0; i < path.getPair().left.getNumSegments(); i++) {
			series2.getData().add(new XYChart.Data(path.getPair().right.getSegment(i).x, path.getPair().right.getSegment(i).y));
		}
		
		sc.getData().addAll(series1, series2);
		Scene scene = new Scene(sc, 1000, 800);
		scene.getStylesheets().clear();
		scene.getStylesheets().add(getClass().getResource("Plotter.css").toExternalForm());		
		stage.setScene(scene);
		stage.show();
	}
	*/
	private double[] getMaxXY(Path p)
	{
		double maxX = -100;
		double maxY = -100;
		for (Segment s : p.getPair().left.getSegments()) {
			if (s.x > maxX)
			{
				maxX = s.x;
			}
			if (s.y > maxY)
			{
				maxY = s.y;
			}	
		}
		
		for (Segment s : p.getPair().right.getSegments()) {
			if (s.x > maxX)
			{
				maxX = s.x;
			}
			if (s.y > maxY)
			{
				maxY = s.y;
			}	
		}
		
		double[] max = {maxX, maxY};
		return max;
	}
	
	private double[] getMinXY(Path p)
	{
		double minX = 100;
		double minY = 100;
		for (Segment s : p.getPair().left.getSegments()) {
			if (s.x < minX)
			{
				minX = s.x;
			}
			if (s.y < minY)
			{
				minY = s.y;
			}	
		}
		
		for (Segment s : p.getPair().right.getSegments()) {
			if (s.x < minX)
			{
				minX = s.x;
			}
			if (s.y < minY)
			{
				minY = s.y;
			}	
		}
		
		double[] min = {minX, minY};
		return min;
	}

}
