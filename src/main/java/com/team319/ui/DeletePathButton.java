package com.team319.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JButton;

public class DeletePathButton extends JButton {

    private static final long serialVersionUID = 1L;
    private Color color = new Color(32, 32, 32);
    private Color pressedColor = new Color(64, 64, 64);
    private Stroke stroke = new BasicStroke(3);

    public DeletePathButton() {
        super();
        enableInputMethods(true);
        setContentAreaFilled(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gc = (Graphics2D) g;
        if (getModel().isPressed()) {
            gc.setColor(pressedColor);
        } else {
            gc.setColor(color);
        }
        gc.fillOval(0, 0, 50, 50);
        gc.setColor(Color.WHITE);
        gc.setStroke(stroke);
        gc.drawLine(20, 25, 30, 25);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    @Override
    public void paintBorder(Graphics g) { }
}
