package com.team319.ui;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FieldChart extends ScatterChart<Number, Number> {

	ImageView iv1 = new ImageView(new Image("file:field.png", 648, 648, true, true));

	public FieldChart(NumberAxis xAxis, NumberAxis yAxis) {
		super(xAxis, yAxis);
		getPlotChildren().add(iv1);
		this.autosize();
	}

	@Override
	protected void layoutPlotChildren() {
		iv1.setFitHeight(this.getHeight() - 145);
		iv1.setFitWidth(this.getWidth() - 88);

		super.layoutPlotChildren();
	}
}
