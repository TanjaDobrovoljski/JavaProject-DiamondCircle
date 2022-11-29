package org.unibl.etf.game.figures;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class CircleShape extends JPanel {

    // class paint to fill color in circle.
    public void paint(Graphics g)
    {
        int width = 30;
        int height =30;
        int diameter = Math.min(width, height);
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2;
        g.fillOval(x, y, diameter, diameter);

    }
}
