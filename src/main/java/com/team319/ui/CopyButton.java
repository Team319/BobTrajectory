package com.team319.ui;

import javax.swing.*;
import java.awt.*;

public class CopyButton extends JButton {

    private static final long serialVersionUID = 1L;
    private Color color = new Color(32, 32, 32);
    private Color pressedColor = new Color(64, 64, 64);
    private Stroke stroke = new BasicStroke(5);
    private Font font = new Font("times", Font.PLAIN, 40);

    public CopyButton() {
        super();
        enableInputMethods(true);
        setContentAreaFilled(false);
        this.setToolTipText("Copy the current path to a new tab");
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
        gc.setFont(font);
        gc.drawString(Character.toString((char)9112), 12, 40);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    @Override
    public void paintBorder(Graphics g) { }

}
