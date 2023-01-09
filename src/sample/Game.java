package sample;

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


import org.unibl.etf.game.cards.Card;
import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.cards.SpecialCard;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.*;
import org.unibl.etf.game.figures.*;
import org.unibl.etf.tools.GameDurationTimer;
import org.unibl.etf.tools.Tuple;
import org.unibl.etf.tools.UnavaliableNameException;

public class Game extends JFrame implements Serializable {

    public static Hashtable<Figure, Tuple<Integer, Integer>> figureCoordinates = new Hashtable<>();
    private JPanel mainPanel;
    private java.util.Timer timer;
    private JButton startFromBeginningButton;
    private JTextArea moveDescription;
    private JEditorPane editorPane1;
    private JPanel titleLabel;
    private JLabel title;
    private JButton button1;
    private JScrollPane figuresList;
    private JPanel imagePanel;
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
    private JButton pauseButton;
    private JButton continueButton;
    private JLabel player1;
    private JLabel player2;
    private JLabel player3;
    private JLabel player4;
    private JPanel leftPanel;
    private JList list1;
    private JTable table1;

    public JLabel getGameTime() {
        return gameTime;
    }

    public void setGameTime(JLabel gameTime) {
        this.gameTime = gameTime;
    }

    public static JPanel playField;
    public static Deck playingDeck;
    private int currentPlayer, numOfPlayers = 0;
    private List<Player> startingNiz, finishingNiz = new ArrayList<>(), lostNiz = new ArrayList<>();
    public static DefaultListModel model = new DefaultListModel();



    public Game( List<String> niz) throws UnavaliableNameException {
        super();
        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        if(niz.size()>=2){
            playField = middle;
            DiamondShape d = new DiamondShape();

            startingNiz=new ArrayList<>();
            numOfPlayers = niz.size();
            Collections.shuffle(niz);
            for (int i=0;i<niz.size();i++)
            {
                Player p = new Player(niz.get(i), d);
                startingNiz.add(p);
            }

            player1.setText(startingNiz.get(0).getPlayersName());
            player1.setForeground(startingNiz.get(0).getColor());
            player2.setText(startingNiz.get(1).getPlayersName());
            player2.setForeground(startingNiz.get(1).getColor());
            if(numOfPlayers>2)
            {
                player3.setText(startingNiz.get(2).getPlayersName());
                player3.setForeground(startingNiz.get(2).getColor());
                if(numOfPlayers>3)
                {
                    player4.setText(startingNiz.get(3).getPlayersName());
                    player4.setForeground(startingNiz.get(3).getColor());
                }
            }

        Deck deck = new Deck();
        playingDeck = deck;
        text = moveDescription;
        // Arrays.stream(DiamondShape.getButtons()).flatMap(Arrays::stream).forEach(x -> x.removeAll());  brise sve

        currentPlayer = 0;
        GhostFigure g = new GhostFigure(d);
        g.start();





        timer = new java.util.Timer();



        list1.setModel(model);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               if(!e.getValueIsAdjusting())
               {
                   try {
                       new FigurePath(list1.getSelectedValue());
                   } catch (InterruptedException interruptedException) {
                       interruptedException.printStackTrace();
                   }
               }
            }
        });


        startFromBeginningButton.addActionListener(listener -> {


        });

        GameDurationTimer gameTimer = new GameDurationTimer(this);
        gameTimer.start();


       // DiamondShape.getButtons()[DiamondShape.movements().get(0).getItem1()][DiamondShape.movements().get(0).getItem2()].add(new Diamond());

        simulation();
        // gameTimer.stop();
        d.removeDiamonds();
        SpecialCard.removeHoles();
        if (finishingNiz.size() != 0) {
            int i = 1;
            for (Player p : finishingNiz) {
                System.out.println(i + " mjesto: " + p.toString());
            }
        }


        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }}


    public void slika(Card s) throws IOException, InterruptedException {
        imagePanel.setLayout(new FlowLayout());
        BufferedImage myPicture = null;
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
                while (numOfPlayers > 0) {
                    currentPlayer = 0;
                    for (; currentPlayer < startingNiz.size(); ) {
                        if (!(lostNiz.contains(startingNiz.get(currentPlayer))) && startingNiz.get(currentPlayer).isPlayerInTheGame()) {
                            try {
                                slika(playingDeck.image());
                                text.setText("Na potezu je " + startingNiz.get(currentPlayer).toString() + ", ");
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
                        } else {
                            if (startingNiz.get(currentPlayer).getFiguresFinishedGame() == 4)
                                if (!finishingNiz.contains(startingNiz.get(currentPlayer)))
                                    finishingNiz.add(startingNiz.get(currentPlayer));

                            if (!lostNiz.contains(startingNiz.get(currentPlayer))) {
                                System.out.println("igrac " + startingNiz.get(currentPlayer).getUniqueID() + " nije vise u igri");
                                lostNiz.add(startingNiz.get(currentPlayer));
                                numOfPlayers--;
                            }
                            currentPlayer++;
                        }
                    }

                }
            }
        }, 0, 1000);
    }



}
 /* public  void gameDurationTimer()
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
  }*/



