package sample;

import org.unibl.etf.game.figures.*;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.Timer;

import javax.swing.*;
import static sample.Game.playField;

public class FigurePath extends JFrame implements Serializable {
    private JPanel panel1;
    private JLabel figurePathName;
    private JPanel matrix;
    private  JButton[][] buttons;
    private Figure f;
    private List<Tuple<Integer,Integer>> movements;

    public FigurePath(Object figure ) throws InterruptedException {
        super();
        setContentPane(panel1);
        setVisible(true);
        pack();
        setSize(900, 600);
        setLocationRelativeTo(null);

        this.figurePathName.setText(figure.toString());


        matrix.setLayout(new GridLayout(DiamondShape.random,DiamondShape.random));

        buttons = new JButton[DiamondShape.random][DiamondShape.random];
        int k=1;
        for (int x = 0; x < DiamondShape.random; x++) {
            for (int y = 0; y <  DiamondShape.random; y++) {

                // Create a new TextField in each Iteration
                JTextField tf = new JTextField();
                tf.setText(String.valueOf(k));
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setEditable(false);
                buttons[x][y] = new JButton();
                buttons[x][y].setText(tf.getText());
                buttons[x][y].setEnabled(false);
                // buttons[x][y].setActionCommand("empty");
                buttons[x][y].setPreferredSize(new Dimension(100,100));
                matrix.add(buttons[x][y]);
                k++;
            }

        }

        //Arrays.stream(buttons).flatMap(Arrays::stream).forEach(x -> x.removeAll());
        if(figure instanceof Figure)
        {
            drawMatrix((Figure) figure);
        }

    }

    public   void drawMatrix(Figure f) throws InterruptedException {
        movements= (f).getPassedMovements();
            if ( f instanceof OrdinaryFigure) {
                for (Tuple<Integer,Integer> it:movements
                     ) {
                    CircleShape s=new CircleShape();
                    s.setForeground(((OrdinaryFigure) f).getShape().getForeground());
                    buttons[it.getItem1()][it.getItem2()].add(s);
                }

            }
            else if ( f instanceof LevitatingFigure) {
                for (Tuple<Integer,Integer> it:movements
                ) {StarShape s=new StarShape();
                s.setForeground(((LevitatingFigure) f).getShape().getForeground());
                    buttons[it.getItem1()][it.getItem2()].add(s);
                }
            }
            else if ( f instanceof SuperFastFigure) {
                    for (Tuple<Integer,Integer> it:movements
                    ) {SpiralShape s=new SpiralShape();
                s.setColor(((SuperFastFigure) f).getShape().getColor());
               buttons[it.getItem1()][it.getItem2()].add(s);
            }}
            matrix.revalidate();
            matrix.repaint();

    }
}
