package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;


import org.unibl.etf.game.cards.Card;
import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.cards.SpecialCard;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.*;
import org.unibl.etf.game.figures.*;
import org.unibl.etf.tools.GameDurationTimer;
import org.unibl.etf.tools.GenLogger;
import org.unibl.etf.tools.Tuple;
import org.unibl.etf.tools.UnavaliableNameException;

public class Game extends JFrame implements Serializable {

    public static Hashtable<Figure, Tuple<Integer, Integer>> figureCoordinates = new Hashtable<>();
    private JPanel mainPanel;
    private java.util.Timer timer;
    public boolean pause=false;
    private JTextArea moveDescription;
    private JEditorPane editorPane1;
    private JPanel titleLabel;
    private JLabel title;
    private JButton showFilesButton;
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
    private AtomicBoolean isPaused;
    private boolean finished=false;
    private static String finishedTime="";


    public void pause() {
        isPaused.set(true);
    }

    public void resume() {
        isPaused.set(false);
    }


    public Game( List<String> niz) throws UnavaliableNameException {

        super();

        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        showFilesButton.setText("Prikaz liste\nfajlova sa\nrezultatima");
        showFilesButton.setEnabled(false);

        if(niz.size()>=2){
            try {
                editorPane1.setText("Trenutni broj\nodigranih igara je: " + String.valueOf(Files.walk(Paths.get("." + File.separator + "REZULTATI"))
                        .filter(p -> p.toString().endsWith(".txt"))
                        .count()));
            }catch (IOException e)
            {
                GenLogger.log(Game.class,e);
            }
            isPaused=new AtomicBoolean(false);
            playField = middle;
            DiamondShape d = new DiamondShape();
            startingNiz=new ArrayList<>();
            numOfPlayers = niz.size();


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

            GhostFigure g = new GhostFigure(d);
            g.start();
        Deck deck = new Deck();
        playingDeck = deck;
        text = moveDescription;
        // Arrays.stream(DiamondShape.getButtons()).flatMap(Arrays::stream).forEach(x -> x.removeAll());  brise sve

        currentPlayer = 0;

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
                       GenLogger.log(Game.class,interruptedException);
                   }
               }
            }
        });
            GameDurationTimer gameTimer = new GameDurationTimer(this);
            gameTimer.start();





       // DiamondShape.getButtons()[DiamondShape.movements().get(0).getItem1()][DiamondShape.movements().get(0).getItem2()].add(new Diamond());


            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                        simulation();
                        if(finished==true)
                        {
                            d.removeDiamonds();
                            SpecialCard.removeHoles();
                            g.stop();
                            gameTimer.stop();
                            moveDescription.setText("IGRA JE ZAVRŠENA!");
                            finishedTime=gameTimer.getDdMinutes()+":"+gameTimer.getDdSeconds();
                            try {
                                writeInFIle();
                                editorPane1.setText("");
                                editorPane1.setText("Trenutni broj\nodigranih igara je: "+String.valueOf(Files.walk(Paths.get("."+ File.separator+"REZULTATI"))
                                        .filter(p -> p.toString().endsWith(".txt"))
                                        .count()));
                                showFilesButton.setEnabled(true);
                                pauseButton.setEnabled(false);
                            } catch (IOException e) {
                                GenLogger.log(Game.class,e);
                            }
                        }
                }},0,1000);



        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pause();
                GhostFigure.pause=true;
                GameDurationTimer.paused=true;
                pauseButton.setEnabled(false);
                continueButton.setEnabled(true);
            }});

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                resume();
                GhostFigure.pause=false;
                GameDurationTimer.paused=false;
                pauseButton.setEnabled(true);
                continueButton.setEnabled(false);
            }

        });
    }
        showFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(Files.walk(Paths.get("."+ File.separator+"REZULTATI"))
                            .filter(p -> p.toString().endsWith(".txt"))
                            .count()!=0)
                    new FileListForm(Paths.get("."+ File.separator+"REZULTATI"));

                } catch (IOException ioException) {
                    GenLogger.log(Game.class,ioException);
                }
            }
        });
    }


    public void slika(Card s)  {
        imagePanel.setLayout(new FlowLayout());
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(s.getSlika()));
            imageLabel.setIcon(new ImageIcon(myPicture));
            if (s instanceof SpecialCard)
                Thread.sleep(1000);
        }catch (IOException | InterruptedException e)
        {
            GenLogger.log(Game.class,e);
        }


    }



    public void simulation() //modifikovati za 4 igraca i paziti ko je zavrsio
    {

        while (numOfPlayers > 0) {


            currentPlayer = 0;

                while(currentPlayer < startingNiz.size() && !isPaused.get()){
                    if(GhostFigure.pause==true && GameDurationTimer.paused==true)
                    {
                        GhostFigure.pause=false;
                        GameDurationTimer.paused=false;
                    }
                if (!(lostNiz.contains(startingNiz.get(currentPlayer))) && startingNiz.get(currentPlayer).isPlayerInTheGame()) {
                    slika(playingDeck.image());
                    text.setText("Na potezu je " + startingNiz.get(currentPlayer).toString() + ", ");
                    startingNiz.get(currentPlayer).playing();

                    if (!SpecialCard.getList().isEmpty()) {

                        for (Player p : startingNiz) {
                            p.figureOnHole();
                        }


                    }

                    currentPlayer++;
                } else {
                    if (startingNiz.get(currentPlayer).getFiguresFinishedGame() == 4)
                        if (!finishingNiz.contains(startingNiz.get(currentPlayer))) {
                            finishingNiz.add(startingNiz.get(currentPlayer));
                            pause();
                            GhostFigure.pause=true;
                            GameDurationTimer.paused=true;
                            JOptionPane.showMessageDialog(this, startingNiz.get(currentPlayer).toString()+" su sve figure stigle do cilja!", "Obavijest", JOptionPane.INFORMATION_MESSAGE);
                           }

                    if (!lostNiz.contains(startingNiz.get(currentPlayer))) {
                        lostNiz.add(startingNiz.get(currentPlayer));
                        numOfPlayers--;
                        pause();
                        GhostFigure.pause=true;
                        GameDurationTimer.paused=true;
                        JOptionPane.showMessageDialog(this, startingNiz.get(currentPlayer).toString()+" više nije u igri!", "Obavijest", JOptionPane.INFORMATION_MESSAGE);
}
                    currentPlayer++;
                }
                resume();
            }

        }
       finished=true;

}

public void writeInFIle() {
    timer.cancel();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("-HH_mm_ss_dd.MM.yyyy");

                String info = "", igrac = "", figura = "", kretanje = "",tmp="";
                for (Player p : startingNiz) {

                    igrac += p.toString() + "\n";
                    for (Figure f : p.getFigures()) {
                        if (f instanceof OrdinaryFigure) {
                            figura += "\t"+f.toString() + " " + p.getColorName() + ") - pređeni put ( ";
                            for (Tuple<Integer, Integer> it : f.getPassedMovements()) {
                                kretanje += DiamondShape.getButtons()[it.getItem1()][it.getItem2()].getText() + "-";

                            }
                            kretanje = kretanje.substring(0, kretanje.length() - 1);
                            figura += kretanje;
                            kretanje = "";
                            figura += ") - stigla da cilja? " + f.getFinished() + "\n";
                        }
                        else if (f instanceof LevitatingFigure) {
                            figura += "\t"+f.toString() + " " + p.getColorName() + ") - pređeni put ( ";
                            for (Tuple<Integer, Integer> it : f.getPassedMovements()) {
                                kretanje += DiamondShape.getButtons()[it.getItem1()][it.getItem2()].getText() + "-";

                            }
                            kretanje = kretanje.substring(0, kretanje.length() - 1);
                            figura += kretanje;
                            kretanje = "";
                            figura += ") - stigla da cilja? " + f.getFinished() + "\n";
                        }
                       else  if (f instanceof SuperFastFigure) {
                            figura += "\t"+f.toString() + " " + p.getColorName() + ") - pređeni put ( ";
                            for (Tuple<Integer, Integer> it : f.getPassedMovements()) {
                                kretanje += DiamondShape.getButtons()[it.getItem1()][it.getItem2()].getText() + "-";

                            }
                            kretanje = kretanje.substring(0, kretanje.length() - 1);
                            figura += kretanje;
                            kretanje = "";
                            figura += ") - stigla da cilja? " + f.getFinished() + "\n";
                        }

                       tmp+=figura;
                       figura="";
                    }
                    igrac += tmp;
                    info += igrac;
                    igrac = "";
                    tmp="";

                }
                info+="\nUkupno vrijeme trajanja igre: "+gameTime.getText();
    try {

        Files.writeString(Paths.get("."+File.separator+"REZULTATI\\IGRA" + simpleDateFormat.format(new Date()) + ".txt"), info);
    } catch (IOException ioException) {
        GenLogger.log(Game.class, ioException);
    }
                System.out.println("File written successfully!");

}
}




