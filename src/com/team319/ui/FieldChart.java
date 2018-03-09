package com.team319.ui;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FieldChart extends ScatterChart<Number, Number> {
	
	ImageView iv1 = new ImageView(new Image("file:field.png",648,648,true,true));

	public FieldChart(NumberAxis xAxis, NumberAxis yAxis) {
		super(xAxis, yAxis);
		getPlotChildren().add(iv1);
		this.autosize();
	}
	
	@Override
	protected void layoutPlotChildren() {
	    double x = 0 ; // x coordinate of image in xAxis coordinates
	    double y = 27 ; // y coordinate of image in yAxis coordinates

	    double layoutX = getXAxis().getDisplayPosition(x);
	    double layoutY = getYAxis().getDisplayPosition(y);

	    iv1.setLayoutX(layoutX);
	    iv1.setLayoutY(layoutY);

	    super.layoutPlotChildren();
	}
}
