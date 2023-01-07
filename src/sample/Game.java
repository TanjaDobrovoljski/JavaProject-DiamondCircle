package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;


import org.unibl.etf.game.cards.Card;
import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.cards.SpecialCard;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.*;
import org.unibl.etf.game.figures.*;
import org.unibl.etf.tools.Tuple;

public class Game extends JFrame implements Serializable {

    public static Hashtable<Figure, Tuple<Integer, Integer>> figureCoordinates=new Hashtable<>();
    private JPanel mainPanel;
    private java.util.Timer timer;
    private JButton startPauseButton;
    private JTextArea moveDescription;
    private JEditorPane editorPane1;
    private JPanel titleLabel;
    private JLabel title;
    private JButton button1;
    private JScrollPane figuresList;
    private  JPanel imagePanel;
   // private JTable matrix;
    public static JTextArea text;

    public JTextArea getMoveDescription() {
        return moveDescription;
    }

    public void setMoveDescription(JTextArea moveDescription) {
        this.moveDescription = moveDescription;
    }

    private JPanel middle;
    private JLabel imageLabel;
    private JLabel gameTime;



    public static JPanel playField;
    public static Deck playingDeck;
    private int currentPlayer,numOfPlayers=0;
    private List<Player> startingNiz,finishingNiz=new ArrayList<>(),lostNiz=new ArrayList<>();
    int second=0;
    int minute=0;
    Timer gameTimer;
    String ddSeconds,ddMinutes;
    DecimalFormat dFormat=new DecimalFormat("00");



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
        text=moveDescription;
        // Arrays.stream(DiamondShape.getButtons()).flatMap(Arrays::stream).forEach(x -> x.removeAll());  brise sve

        currentPlayer=0;
        GhostFigure g=new GhostFigure(d);
        Diamond dd = new Diamond(0, 0);
        startingNiz =new ArrayList<>();
       Player p1=new Player("A",d);
        Player p2=new Player("B",d);
        Player p3=new Player("C",d);
        Player p4=new Player("D",d);
        startingNiz.add(p1);
        startingNiz.add(p2);
        startingNiz.add(p3);
        startingNiz.add(p4);
        numOfPlayers=startingNiz.size();

        g.start();
        timer=new java.util.Timer();


        gameDurationTimer();
        gameTimer.start();


       simulation();
       gameTimer.stop();
       d.removeDiamonds();
       SpecialCard.removeHoles();
        if(finishingNiz.size()!=0)
        {
            int i=1;
            for (Player p:finishingNiz)
            {
                System.out.println(i+" mjesto: "+p.toString());
            }
        }

    }


    public void slika( Card s) throws IOException, InterruptedException {
       imagePanel.setLayout(new FlowLayout());
        BufferedImage myPicture=null;
        myPicture = ImageIO.read(new File(s.getSlika()));
        imageLabel.setIcon(new ImageIcon(myPicture));
        if (s instanceof SpecialCard)
            Thread.sleep(1000);
  }


  public void simulation() //modifikovati za 4 igraca i paziti ko je zavrsio
  {

      timer.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
              while (numOfPlayers>0)
              {
                  currentPlayer=0;
                  for(;currentPlayer<startingNiz.size();)
                  {
                      if (!(lostNiz.contains(startingNiz.get(currentPlayer))) && startingNiz.get(currentPlayer).isPlayerInTheGame()) {
                          try {
                              slika(playingDeck.image());
                              text.setText("Na potezu je "+startingNiz.get(currentPlayer).toString()+", ");
                              startingNiz.get(currentPlayer).playing();
                              if (!SpecialCard.getList().isEmpty()) {

                                  for (Player p : startingNiz) {
                                      p.figureOnHole();
                                  }


                              }
                              currentPlayer++;
                          } catch (IOException ioException) {
                              ioException.printStackTrace();
                          } catch (InterruptedException interruptedException) {
                              interruptedException.printStackTrace();
                          }
                      }
                      else {
                          if(startingNiz.get(currentPlayer).getFiguresFinishedGame()==4)
                              if(!finishingNiz.contains(startingNiz.get(currentPlayer)))
                                  finishingNiz.add(startingNiz.get(currentPlayer));

                          if(!lostNiz.contains(startingNiz.get(currentPlayer))) {
                              System.out.println("igrac " + startingNiz.get(currentPlayer).getUniqueID() + " nije vise u igri");
                              lostNiz.add(startingNiz.get(currentPlayer));
                              numOfPlayers--;
                          }
                          currentPlayer++;
                      }
                  }
              }}},0,1000);

  }
  public  void gameDurationTimer()
  {
      gameTimer=new Timer(1000, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              second++;
              ddSeconds=dFormat.format(second);
              ddMinutes=dFormat.format(minute);
              gameTime.setText(ddMinutes+" : "+ddSeconds);
              if(second==60)
              {
                  second=0;
                  minute++;
                  gameTime.setText(ddMinutes+" : "+ddSeconds);
              }
          }
      });
  }

}

