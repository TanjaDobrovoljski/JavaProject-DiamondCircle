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
import java.util.ArrayList;

import org.unibl.etf.game.cards.Card;
import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.cards.OrdinaryCard;
import org.unibl.etf.game.cards.SpecialCard;
import org.unibl.etf.game.players.Player;
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
    private  JPanel imagePanel;
   // private JTable matrix;

    private JPanel middle;


    public static JPanel playField;


    public Game() throws Exception {
        super();
        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        playField=middle;
        DiamondShape d=new DiamondShape();
        Deck deck=new Deck();
        // Arrays.stream(DiamondShape.getButtons()).flatMap(Arrays::stream).forEach(x -> x.removeAll());  brise sve


        Diamond dd = new Diamond(0, 0);


       Player p=new Player("Simo",d);
        p.start();

       // d.removeDiamondsAndHoles();

    }

    public  void slika( Card s) throws IOException {
       imagePanel.setLayout(new FlowLayout());
        BufferedImage myPicture=null;
        if(s instanceof OrdinaryCard)
        myPicture = ImageIO.read(new File(s.getSlika()));
        else if(s instanceof SpecialCard)
        {
            myPicture = ImageIO.read(new File(s.getSlika()));
            ((SpecialCard) s).setHoles();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        imagePanel.add(picLabel);
  }

}

