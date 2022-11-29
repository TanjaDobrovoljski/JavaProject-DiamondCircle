package org.unibl.etf.game.figures;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class SpiralShape extends JPanel {

    private Paint color=Color.black;

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 5;
        int y = 7;

        // fill and stroke GeneralPath
        int xPoints[] = {x, 40, x, 40};
        int yPoints[] = {y, 40, 40, y};
        GeneralPath filledPolygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                xPoints.length);
        filledPolygon.moveTo(xPoints[0], yPoints[0]);
        for (int index = 1; index < xPoints.length; index++) {
            filledPolygon.lineTo(xPoints[index], yPoints[index]);
        }
        filledPolygon.closePath();
        g2.setPaint(getColor());
        g2.fill(filledPolygon);
        g2.draw(filledPolygon);
    }

}
