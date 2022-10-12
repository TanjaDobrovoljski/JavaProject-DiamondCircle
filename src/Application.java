import javax.swing.*;
import java.io.IOException;

public class Application  {


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

    public static void main(String args[]) {
        Application application = new Application();
        JFrame frame = new JFrame("Diamond Circle") ;

        frame.setVisible(true);
        frame.setContentPane(application.mainPanel);

        frame.pack();
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.runSimulationButton.addActionListener(listener->
        {
               try {
                   new Game();
               } catch (IOException e) {
                   e.printStackTrace();
               }

        });
    }
}
