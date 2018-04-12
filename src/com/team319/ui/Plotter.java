package com.team319.ui;

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
		gc.setLineWidth(5);
		
		final NumberAxis timeAxis1 = new NumberAxis(0, path.getTrajectory().getNumSegments(), 100);
		final NumberAxis velocityAxis = new NumberAxis(-config.max_vel, config.max_vel, 1);
		velocityAxis.setTickLabelsVisible(false);
		final ScatterChart<Number, Number> velocity = new ScatterChart<Number, Number>(timeAxis1, velocityAxis);
		XYChart.Series<Number, Number> velocityData = new XYChart.Series<>();
		velocity.setTitle("Velocity");
		velocity.setLegendVisible(false); 
		
		final NumberAxis timeAxis2 = new NumberAxis(0, path.getTrajectory().getNumSegments(), 100);
		final NumberAxis headingAxis = new NumberAxis(-180, 180, 30);
		headingAxis.setTickLabelsVisible(false);
		final ScatterChart<Number, Number> heading = new ScatterChart<Number, Number>(timeAxis2, headingAxis);
		XYChart.Series<Number, Number> headingData = new XYChart.Series<>();
		heading.setTitle("Heading");
		heading.setLegendVisible(false); 
		
		final NumberAxis timeAxis3 = new NumberAxis(0, path.getTrajectory().getNumSegments(), 100);
		final NumberAxis positionAxis = new NumberAxis(0, (int)(path.getTrajectory().getSegment(
				path.getTrajectory().getNumSegments() - 1).pos), 5);
		positionAxis.setTickLabelsVisible(false);
		final ScatterChart<Number, Number> position = new ScatterChart<Number, Number>(timeAxis3, positionAxis);
		XYChart.Series<Number, Number> positionData = new XYChart.Series<>();
		position.setTitle("Position");
		position.setLegendVisible(false); 
			
		Segment start = path.getTrajectory().getSegment(0);
		Segment end = path.getTrajectory().getSegment(path.getTrajectory().getNumSegments() - 1);
		gc.translate(start.x * 24, getFieldPoint(start.y));
		gc.rotate(start.heading);
		
		drawRobot(start, canvas, Color.BLUE, 5.0);
		for (int i = 1; i < path.getTrajectory().getNumSegments(); i++) {
			Segment segment = path.getTrajectory().getSegment(i);
			Segment previousSegment = path.getTrajectory().getSegment(i - 1);
			if (i%100 == 0) {
				drawRobot(path.getTrajectory().getSegment(i), canvas, Color.GREY, 1.0);
			}
			drawPoint(
					segment.heading - previousSegment.heading, 
					segment.pos - previousSegment.pos,
					canvas,
					getColor(segment.vel, config.max_vel));
			velocityData.getData().add(new XYChart.Data<>(i, segment.vel));
			headingData.getData().add(new XYChart.Data<>(i, Math.toDegrees(segment.heading)));
			positionData.getData().add(new XYChart.Data<>(i, segment.pos));
		}
		gc.restore();
		
		velocity.getData().addAll(velocityData);
		heading.getData().addAll(headingData);
		position.getData().addAll(positionData);

		ImageView iv1 = new ImageView(new Image("file:field.png", 648, 648, true, true));
		
		drawRobot(end, canvas, Color.DARKORANGE, 5.0);
		Group root = new Group();
		root.getChildren().add(iv1);
		root.getChildren().add(canvas);
		
		HBox pane = new HBox();
		VBox charts = new VBox();
		charts.setMaxHeight(648);
		charts.getChildren().add(velocity);
		charts.getChildren().add(position);
		charts.getChildren().add(heading);
		pane.getChildren().add(root);
		pane.getChildren().add(charts);
		Scene scene = new Scene(pane);
		scene.getStylesheets().clear();
		scene.getStylesheets().add(getClass().getResource("Plotter.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	private void drawPoint(double heading, double distance, Canvas canvas, Paint color) {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill(color);
		gc.setStroke(color);
		gc.setLineWidth(2);

		gc.save();
		gc.rotate(Math.toDegrees(-heading));
		gc.translate(distance * 24, 0);
		gc.fillOval(0, 0, 2, 2);
	}
	
	private void drawRobot(Segment segment, Canvas canvas, Color color, double thickness) {
	    double height = config.robotWidth * 2;
	    double width = config.robotLength * 2;

	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    
	    gc.setFill(color);
		gc.setStroke(color);
		gc.setLineWidth(thickness);

	    gc.strokeRoundRect(-width/2, -height/2, width, height, 10, 10);
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
}