package sample;

import org.unibl.etf.tools.GenLogger;
import org.unibl.etf.tools.NotEnoughArguments;
import org.unibl.etf.tools.UnavaliableNameException;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Application  {


    private static String p1,p2,p3,p4;
    private JTextField player1Name;
    private JTextField player2Named;
    private JTextField player3Named;
    private JTextField player4Named;
    private JButton runSimulationButton;
    private JLabel player1;
    private JLabel player2;
    private JLabel player3;
    private JLabel player4;
    private JPanel mainPanel;
    private List<String> startingNiz = new ArrayList<>();
    int numOfPlayers=0;


    public static void main(String args[]) {

        Application application = new Application();
        JFrame frame = new JFrame("Diamond Circle") ;
        AtomicInteger numOfPlayers= new AtomicInteger();

        frame.setVisible(true);
        frame.setContentPane(application.mainPanel);


        frame.pack();
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        application.runSimulationButton.addActionListener(listener->
        {

            if(!application.player1Name.getText().equals("") && !application.player1Name.getText().equals(" "))
            {
                p1=application.player1Name.getText();
                numOfPlayers.incrementAndGet();
                application.startingNiz.add(p1);
            }
            if(!application.player2Named.getText().equals("") && !application.player2Named.getText().equals(" "))
            {
                p2=application.player2Named.getText();
                numOfPlayers.incrementAndGet();
                application.startingNiz.add(p2);
            }
            if(!application.player3Named.getText().equals("") && !application.player3Named.getText().equals(" "))
            {
                p3=application.player3Named.getText();
                numOfPlayers.incrementAndGet();
                application.startingNiz.add(p3);
            }
            if(!application.player4Named.getText().equals("") && !application.player4Named.getText().equals(" "))
            {
                p4=application.player4Named.getText();
                numOfPlayers.incrementAndGet();
                application.startingNiz.add(p4);
            }
            if(numOfPlayers.get()<2){
                try {
                    throw  new NotEnoughArguments();
                } catch (NotEnoughArguments e) {
                    GenLogger.log(Application.class, e);
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

                }
            }
            else {


            try {
                new Game(application.startingNiz);
            } catch (UnavaliableNameException e) {
                GenLogger.log(Application.class, e);
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                System.exit(0);

            }


        }});
    }



}
