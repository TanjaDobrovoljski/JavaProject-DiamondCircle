package sample;

import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import org.unibl.etf.game.cards.OrdinaryCard;
import org.unibl.etf.game.cards.SpecialCard;
import org.unibl.etf.shape.*;
import org.unibl.etf.game.figures.*;

public class Game extends JFrame implements Serializable {

    private JPanel mainPanel;

    private JButton startPauseButton;
    private JTextArea textArea1;
    private JEditorPane editorPane1;
    private JTextPane textPane1;
    private JPanel titleLabel;
    private JLabel title;
    private JButton button1;
    private JScrollPane figuresList;
    private JPanel imagePanel;
   // private JTable matrix;

    private JPanel middle;


    public static JPanel playField;


    public Game() throws IOException {
        super();
        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        playField=middle;
        DiamondShape d=new DiamondShape();




        slika();

        Diamond dd = new Diamond(0, 0);
        OrdinaryCard o=new OrdinaryCard(1);


       /* int[][] counts;                             //integer array to store counts of each cell. Used as a back-end for comparisons.
        JButton[][] buttons;
        middle.setLayout(new GridLayout(5,5));
        buttons = new JButton[5][5];

        for(int a = 0; a < buttons.length; a++)
        {
            for(int b = 0; b < buttons[0].length; b++)
            {
                buttons[a][b] = new JButton();

                middle.add(buttons[a][b]);
            }
        }
        */



    }

    public void slika() throws IOException {
       imagePanel.setLayout(new FlowLayout());
        SpecialCard s=new SpecialCard();
        BufferedImage myPicture = ImageIO.read(new File(s.getSlika()));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        imagePanel.add(picLabel);
  }

}

