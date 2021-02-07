package com.team319.ui;

import java.awt.Color;

import javax.swing.JTabbedPane;

public class FieldTabs extends JTabbedPane {

    private static final long serialVersionUID = 1L;

    public FieldTabs() {
        setTabPlacement(JTabbedPane.LEFT);
        setUI(new CustomTabbedPaneUI());
        setForeground(Color.WHITE);
        setBackground(new Color(50, 50, 50));
    }
}
