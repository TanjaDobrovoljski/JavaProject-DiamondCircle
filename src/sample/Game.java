package sample;

import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


import org.unibl.etf.game.cards.Card;
import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.cards.OrdinaryCard;
import org.unibl.etf.game.cards.SpecialCard;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.*;
import org.unibl.etf.game.figures.*;

public class Game extends JFrame implements Serializable {

    private JPanel mainPanel;
    private java.util.Timer timer;
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
    private JLabel imageLabel;


    public static JPanel playField;
    public static Deck playingDeck;
    private int currentPlayer;
    private List<Player> niz;



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
        playingDeck=deck;
        // Arrays.stream(DiamondShape.getButtons()).flatMap(Arrays::stream).forEach(x -> x.removeAll());  brise sve

        currentPlayer=0;
        GhostFigure g=new GhostFigure(d);
        Diamond dd = new Diamond(0, 0);
        niz =new ArrayList<>();
       Player p1=new Player("A",d);
        Player p2=new Player("B",d);
        niz.add(p1);
        niz.add(p2);


      // g.start();
        timer=new java.util.Timer();
        simulation();

    }


    public void slika( Card s) throws IOException, InterruptedException {
       imagePanel.setLayout(new FlowLayout());
        BufferedImage myPicture=null;
        myPicture = ImageIO.read(new File(s.getSlika()));
        imageLabel.setIcon(new ImageIcon(myPicture));
        if(s instanceof SpecialCard)
            Thread.sleep(1000);
  }

  public void simulation() //modifikovati za 4 igraca i paziti ko je zavrsio
  {
      timer.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
      if(currentPlayer==0)
      {
          if(niz.get(0).isPlayerInTheGame())
          { try {
              slika(playingDeck.image());
              niz.get(0).playing();
              currentPlayer=1;
          } catch (IOException ioException) {
              ioException.printStackTrace();
          } catch (InterruptedException interruptedException) {
              interruptedException.printStackTrace();
          }
          }
          else
          {
              System.out.println("igrac "+ niz.get(0).getUniqueID()+" nije vise u igri");
              currentPlayer=1;
          }

      }
      else if(currentPlayer==1)
      {
          if(niz.get(1).isPlayerInTheGame()){
              try {
                  slika(playingDeck.image());
                  niz.get(1).playing();
                  currentPlayer=0;
              } catch (IOException ioException) {
                  ioException.printStackTrace();
              } catch (InterruptedException interruptedException) {
                  interruptedException.printStackTrace();
              }
          }}
          }

      },0,1000);
  }
}

