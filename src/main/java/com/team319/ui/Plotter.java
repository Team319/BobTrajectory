package com.team319.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory.Segment;
import com.team319.trajectory.SrxTranslatorConfig;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Plotter {
	
	private SrxTranslatorConfig config;

	public Plotter(){}

	@SuppressWarnings("unchecked")
	public void plotChezyTrajectory(Path path, SrxTranslatorConfig config) {
		this.config = config;
		Stage stage = new Stage();

		Canvas canvas = new Canvas(648, 648);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		DecimalFormat df = new DecimalFormat("0.00##");
		StringBuilder title = new StringBuilder();
		title.append(path.getName()).append(" : ")
		.append(df.format(path.getTrajectory().getNumSegments() * path.getTrajectory().getSegment(0).dt))
		.append("s");
		stage.setTitle(title.toString());
		Segment start = path.getTrajectory().getSegment(0);
		Segment end = path.getTrajectory().getSegment(path.getTrajectory().getNumSegments() - 1);
		drawRobot(start, canvas, Color.BLUE, 5.0);
		drawRobot(end, canvas, Color.DARKORANGE, 5.0);
		
		final NumberAxis timeAxis1 = new NumberAxis(0, path.getTrajectory().getNumSegments(), 100);
		final NumberAxis velocityAxis = new NumberAxis(-config.max_vel, config.max_vel, 5);
		velocityAxis.setTickLabelsVisible(false);
		final ScatterChart<Number, Number> velocity = new ScatterChart<Number, Number>(timeAxis1, velocityAxis);
		XYChart.Series<Number, Number> velocityData = new XYChart.Series<>();
		velocity.setTitle("Velocity");
		velocity.setLegendVisible(false); 
		
		final NumberAxis timeAxis2 = new NumberAxis(0, path.getTrajectory().getNumSegments(), 100);
		final NumberAxis headingAxis = new NumberAxis(getSmallestHeading(path) - 10, getLargestHeading(path) + 10, 30);
		headingAxis.setTickLabelsVisible(false);
		final ScatterChart<Number, Number> heading = new ScatterChart<Number, Number>(timeAxis2, headingAxis);
		XYChart.Series<Number, Number> headingData = new XYChart.Series<>();
		heading.setTitle("Heading");
		heading.setLegendVisible(false); 
		
		final NumberAxis timeAxis3 = new NumberAxis(0, path.getTrajectory().getNumSegments(), 100);
		final NumberAxis positionAxis = new NumberAxis(getSmallestPosition(path) - 5, getLargestPosition(path) + 5, 5);
		positionAxis.setTickLabelsVisible(false);
		final ScatterChart<Number, Number> position = new ScatterChart<Number, Number>(timeAxis3, positionAxis);
		XYChart.Series<Number, Number> positionData = new XYChart.Series<>();
		position.setTitle("Position");
		position.setLegendVisible(false); 
		
		final NumberAxis positionAxis2 = new NumberAxis(0, end.pos, 100);
		final NumberAxis velocityAxis2 = new NumberAxis(-config.max_vel, config.max_vel, 5);
		positionAxis.setTickLabelsVisible(false);
		final ScatterChart<Number, Number> vAtP = new ScatterChart<Number, Number>(positionAxis2, velocityAxis2);
		XYChart.Series<Number, Number> vAtPData = new XYChart.Series<>();
		vAtP.setTitle("Velocity at Position");
		vAtP.setLegendVisible(false); 
		
		for (int i = 1; i < path.getTrajectory().getNumSegments(); i++) {
			Segment segment = path.getTrajectory().getSegment(i);
			gc.setFill(getColor(segment.vel, config.max_vel));
			gc.setStroke(getColor(segment.vel, config.max_vel));
			gc.fillOval(segment.x * 24, 
					getFieldPoint(segment.y), 3, 3);
			if (i%50 == 0) {
				drawRobot(segment, canvas, Color.GREY, 1.0);
			}
			velocityData.getData().add(new XYChart.Data<>(i, segment.vel));
			headingData.getData().add(new XYChart.Data<>(i, Math.toDegrees(segment.heading)));
			positionData.getData().add(new XYChart.Data<>(i, segment.pos));
			vAtPData.getData().add(new XYChart.Data<>(segment.pos, segment.vel));
		}
		gc.restore();
		
		velocity.getData().addAll(velocityData);
		heading.getData().addAll(headingData);
		position.getData().addAll(positionData);
		vAtP.getData().addAll(vAtPData);

		FileInputStream input;
		try {
			input = new FileInputStream("./src/main/java/com/team319/ui/field.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		ImageView iv1 = new ImageView(new Image(input, 648, 648, true, true));
		
		Group root = new Group();
		root.getChildren().add(iv1);
		root.getChildren().add(canvas);
		
		HBox pane = new HBox();
		VBox charts = new VBox();
		charts.setMaxHeight(648);
		charts.getChildren().add(velocity);
		charts.getChildren().add(position);
		charts.getChildren().add(heading);
		charts.getChildren().add(vAtP);
		pane.getChildren().add(root);
		pane.getChildren().add(charts);
		Scene scene = new Scene(pane);
		scene.getStylesheets().clear();
		scene.getStylesheets().add(this.getClass().getResource("Plotter.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	private void drawRobot(Segment segment, Canvas canvas, Color color, double thickness) {
		double x = segment.x * 24;
	    double y = getFieldPoint(segment.y);
	    double height = config.robotWidth * 2;
	    double width = config.robotLength * 2;

	    GraphicsContext gc = canvas.getGraphicsContext2D();	   
	    
	    gc.setLineWidth(thickness);

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
	
	private Paint getColor(double speed, double limit) {
		double absSpeed = Math.abs(speed);
		double absLimit = Math.abs(limit);
		if (absSpeed >= absLimit) {
			return Color.BLACK;
		}
		if (absSpeed / absLimit == 0.5) {
			return Color.web("ffff00");
		}
		double percentage = absSpeed / absLimit * 100;
	    int green = percentage < 50 ? 255 : (int)Math.floor(256 - (percentage - 50 ) * 5.12);
	    int red = percentage > 50 ? 255 : (int)Math.floor((percentage) * 5.12);
	    return Color.web(String.format("%02X", red) + String.format("%02X", green) + "00");
	}
	
	private double getLargestPosition(Path path) {
		double largest = path.getTrajectory().getSegment(0).pos;
		for (Segment segment : path.getTrajectory().getSegments()) {
			if (segment.pos > largest) {
				largest = segment.pos;
			}
		}
		return largest;
	}
	
	private double getSmallestPosition(Path path) {
		double smallest = path.getTrajectory().getSegment(0).pos;
		for (Segment segment : path.getTrajectory().getSegments()) {
			if (segment.pos < smallest) {
				smallest = segment.pos;
			}
		}
		return smallest;
	}
	
	private double getLargestHeading(Path path) {
		double largest = path.getTrajectory().getSegment(0).heading;
		for (Segment segment : path.getTrajectory().getSegments()) {
			if (segment.heading > largest) {
				largest = segment.heading;
			}
		}
		return Math.toDegrees(largest);
	}
	
	private double getSmallestHeading(Path path) {
		double smallest = path.getTrajectory().getSegment(0).heading;
		for (Segment segment : path.getTrajectory().getSegments()) {
			if (segment.heading < smallest) {
				smallest = segment.heading;
			}
		}
		return Math.toDegrees(smallest);
	}
}