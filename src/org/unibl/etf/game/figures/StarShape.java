package org.unibl.etf.game.figures;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class StarShape extends JPanel
{

    public StarShape() {

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(200, 200, 200));
        setOpaque(false);
        setForeground(Color.black);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height) / 3;
        int x = width / 2;
        int y = height / 2;
        Star2D s = new Star2D(x, y, size / 2, size, 5);
        g2.setColor(getBackground());
        g2.fill(s);
        g2.setColor(getForeground());
        g2.fill(s);
        g2.dispose();
    }


    public class Star2D implements Shape {

        private Shape starShape;
        private double x;
        private double y;
        private double innerRadius;
        private double outerRadius;
        private int branchesCount;

        /**
         * <p>
         * Creates a new star whose center is located at the specified
         * <code>x</code> and <code>y</code> coordinates. The number of branches and
         * their length can be specified.</p>
         *
         * @param x the location of the star center
         * @param y the location of the star center
         * @param innerRadius the distance between the center of the star and the
         * origin of the branches
         * @param outerRadius the distance between the center of the star and the
         * tip of the branches
         * @param branchesCount the number of branches in this star; must be &gt;= 3
         * @throws IllegalArgumentException if <code>branchesCount<code> is < 3 or
         *   if <code>innerRadius</code> is &gt;= <code>outerRadius</code>
         */
        public Star2D(double x, double y,
                      double innerRadius, double outerRadius,
                      int branchesCount) {
            if (branchesCount < 3) {
                throw new IllegalArgumentException("The number of branches must"
                        + " be >= 3.");
            } else if (innerRadius >= outerRadius) {
                throw new IllegalArgumentException("The inner radius must be < "
                        + "outer radius.");
            }

            this.x = x;
            this.y = y;
            this.innerRadius = innerRadius;
            this.outerRadius = outerRadius;
            this.branchesCount = branchesCount;

            starShape = generateStar(x, y, innerRadius, outerRadius, branchesCount);
        }

        private  Shape generateStar(double x, double y,
                                          double innerRadius, double outerRadius,
                                          int branchesCount) {
            GeneralPath path = new GeneralPath();

            double outerAngleIncrement = 2 * Math.PI / branchesCount;

            double outerAngle = branchesCount % 2 == 0 ? 0.0 : -(Math.PI / 2.0);
            double innerAngle = (outerAngleIncrement / 2.0) + outerAngle;

            float x1 = (float) (Math.cos(outerAngle) * outerRadius + x);
            float y1 = (float) (Math.sin(outerAngle) * outerRadius + y);

            float x2 = (float) (Math.cos(innerAngle) * innerRadius + x);
            float y2 = (float) (Math.sin(innerAngle) * innerRadius + y);

            path.moveTo(x1, y1);
            path.lineTo(x2, y2);

            outerAngle += outerAngleIncrement;
            innerAngle += outerAngleIncrement;

            for (int i = 1; i < branchesCount; i++) {
                x1 = (float) (Math.cos(outerAngle) * outerRadius + x);
                y1 = (float) (Math.sin(outerAngle) * outerRadius + y);

                path.lineTo(x1, y1);

                x2 = (float) (Math.cos(innerAngle) * innerRadius + x);
                y2 = (float) (Math.sin(innerAngle) * innerRadius + y);

                path.lineTo(x2, y2);

                outerAngle += outerAngleIncrement;
                innerAngle += outerAngleIncrement;
            }

            path.closePath();
            return path;
        }

        /**
         * <p>
         * Sets the inner radius of the star, that is the distance between its
         * center and the origin of the branches. The inner radius must always be
         * lower than the outer radius.</p>
         *
         * @param innerRadius the distance between the center of the star and the
         * origin of the branches
         * @throws IllegalArgumentException if the inner radius is &gt;= outer
         * radius
         */
        public void setInnerRadius(double innerRadius) {
            if (innerRadius >= outerRadius) {
                throw new IllegalArgumentException("The inner radius must be <"
                        + " outer radius.");
            }

            this.innerRadius = innerRadius;
            starShape = generateStar(getX(), getY(), innerRadius, getOuterRadius(),
                    getBranchesCount());
        }

        /**
         * <p>
         * Sets location of the center of the star.</p>
         *
         * @param x the x location of the center of the star
         */
        public void setX(double x) {
            this.x = x;
            starShape = generateStar(x, getY(), getInnerRadius(), getOuterRadius(),
                    getBranchesCount());
        }

        /**
         * <p>
         * Sets the location of the center of the star.</p>
         *
         * @param y the x location of the center of the star
         */
        public void setY(double y) {
            this.y = y;
            starShape = generateStar(getX(), y, getInnerRadius(), getOuterRadius(),
                    getBranchesCount());
        }

        /**
         * <p>
         * Sets the outer radius of the star, that is the distance between its
         * center and the tips of the branches. The outer radius must always be
         * greater than the inner radius.</p>
         *
         * @param outerRadius the distance between the center of the star and the
         * tips of the branches
         * @throws IllegalArgumentException if the inner radius is &gt;= outer
         * radius
         */
        public void setOuterRadius(double outerRadius) {
            if (innerRadius >= outerRadius) {
                throw new IllegalArgumentException("The outer radius must be > "
                        + "inner radius.");
            }

            this.outerRadius = outerRadius;
            starShape = generateStar(getX(), getY(), getInnerRadius(), outerRadius,
                    getBranchesCount());
        }

        /**
         * <p>
         * Sets the number branches of the star. A star must always have at least 3
         * branches.</p>
         *
         * @param branchesCount the number of branches
         * @throws IllegalArgumentException if <code>branchesCount</code> is &lt;=2
         */
        public void setBranchesCount(int branchesCount) {
            if (branchesCount <= 2) {
                throw new IllegalArgumentException("The number of branches must"
                        + " be >= 3.");
            }

            this.branchesCount = branchesCount;
            starShape = generateStar(getX(), getY(), getInnerRadius(),
                    getOuterRadius(), branchesCount);
        }

        /**
         * <p>
         * Returns the location of the center of star.</p>
         *
         * @return the x coordinate of the center of the star
         */
        public double getX() {
            return x;
        }

        /**
         * <p>
         * Returns the location of the center of star.</p>
         *
         * @return the y coordinate of the center of the star
         */
        public double getY() {
            return y;
        }

        /**
         * <p>
         * Returns the distance between the center of the star and the origin of the
         * branches.</p>
         *
         * @return the inner radius of the star
         */
        public double getInnerRadius() {
            return innerRadius;
        }

        /**
         * <p>
         * Returns the distance between the center of the star and the tips of the
         * branches.</p>
         *
         * @return the outer radius of the star
         */
        public double getOuterRadius() {
            return outerRadius;
        }

        /**
         * <p>
         * Returns the number of branches of the star.</p>
         *
         * @return the number of branches, always &gt;= 3
         */
        public int getBranchesCount() {
            return branchesCount;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Rectangle getBounds() {
            return starShape.getBounds();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Rectangle2D getBounds2D() {
            return starShape.getBounds2D();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(double x, double y) {
            return starShape.contains(x, y);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(Point2D p) {
            return starShape.contains(p);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean intersects(double x, double y, double w, double h) {
            return starShape.intersects(x, y, w, h);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean intersects(Rectangle2D r) {
            return starShape.intersects(r);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(double x, double y, double w, double h) {
            return starShape.contains(x, y, w, h);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(Rectangle2D r) {
            return starShape.contains(r);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public PathIterator getPathIterator(AffineTransform at) {
            return starShape.getPathIterator(at);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public PathIterator getPathIterator(AffineTransform at, double flatness) {
            return starShape.getPathIterator(at, flatness);
        }
    }
   /* public StarShape()
    {
        setPreferredSize( new Dimension(400, 400) );
        setBackground( Color.white );
        setOpaque(false);
    }

    private void drawStar( Graphics gr, int x, int y, int size )
    {
        int endX, endY ;

        // Six lines radiating from (x,y)
        for ( int i = 0; i<6; i++ )
        {
            endX = x + (int)(size*Math.cos( (2*Math.PI/6)*i ));
            endY = y + (int)(size*Math.sin( (2*Math.PI/6)*i ));
            gr.drawLine( x, y, endX, endY );
        }
    }

    public void paintComponent ( Graphics gr )
    {
        int width  = getWidth();
        int height = getHeight();
        int min;

        super.paintComponent( gr );
        setBackground(Color.white);
        setOpaque(false);
        gr.setColor( Color.blue );

        if ( height > width )
            min = height;
        else
            min = width;

        drawStar( gr, width/2, height/2, min/2 );
    }*/
}