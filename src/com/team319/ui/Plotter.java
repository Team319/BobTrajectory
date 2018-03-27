package com.team319.ui;

import java.text.DecimalFormat;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory.Segment;
import com.team319.trajectory.SrxTranslatorConfig;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Plotter {
	
	private SrxTranslatorConfig config;

	public Plotter(){}

	public void plotChezyTrajectory(Path path, SrxTranslatorConfig config) {
		this.config = config;
		Stage stage = new Stage();

		Canvas canvas = new Canvas(648, 648);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		DecimalFormat df = new DecimalFormat("0.00##");
		StringBuilder title = new StringBuilder();
		title.append(path.getName()).append(" : ")
		.append(df.format(path.getLeftWheelTrajectory().getNumSegments() * path.getLeftWheelTrajectory().getSegment(0).dt))
		.append("s");
		stage.setTitle(title.toString());
		gc.setLineWidth(5);
		for (int i = 0; i < path.getPair().left.getNumSegments(); i++) {
			gc.setFill(Color.BLUE);
			gc.setStroke(Color.BLUE);
			gc.fillOval(path.getPair().left.getSegment(i).x * 24, getFieldPoint(path.getPair().left.getSegment(i).y), 1, 1);
		}

		for (int i = 0; i < path.getPair().right.getNumSegments(); i++) {
			gc.setFill(Color.ORANGERED);
			gc.setStroke(Color.ORANGERED);
			gc.fillOval(path.getPair().right.getSegment(i).x * 24, getFieldPoint(path.getPair().right.getSegment(i).y), 1, 1);
		}

		for (int i = 0; i < path.getPair().center.getNumSegments(); i++) {
			gc.setFill(Color.PURPLE);
			gc.setStroke(Color.PURPLE);
			gc.fillOval(path.getPair().center.getSegment(i).x * 24, 
					getFieldPoint(path.getPair().center.getSegment(i).y), 1, 1);
			if (i%50 == 0) {
				drawRobot(path.getPair().center.getSegment(i), canvas, Color.GREY, 1.0);
			}
		}

		ImageView iv1 = new ImageView(new Image("file:field.png", 648, 648, true, true));
		Segment start = path.getPair().center.getSegment(0);
		Segment end = path.getPair().center.getSegment(path.getPair().center.getNumSegments() - 1);
		drawRobot(start, canvas, Color.ORANGERED, 5.0);
		drawRobot(end, canvas, Color.PURPLE, 5.0);
		Group root = new Group();
		root.getChildren().add(iv1);
		root.getChildren().add(canvas);
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	private void drawRobot(Segment segment, Canvas canvas, Color color, double lineWidth) {
		double x = segment.x * 24;
	    double y = getFieldPoint(segment.y);
	    double height = config.robotWidth * 2;
	    double width = config.robotLength * 2;

	    GraphicsContext gc = canvas.getGraphicsContext2D();	   
	    
	    gc.setLineWidth(lineWidth);

		gc.setFill(color);
		gc.setStroke(color);

	    gc.save();
	    gc.translate(x, y);
	    gc.rotate(Math.toDegrees(-segment.heading));
	    gc.strokeRoundRect(-width/2, -height/2, width, height, 10, 10);
	    gc.translate(-x, -y);

	    gc.restore();
	}

	private double getFieldPoint(double point) {
		return 648 - point * 24;
	}
}