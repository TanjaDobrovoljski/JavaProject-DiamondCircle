import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

public class Game extends JFrame implements Serializable {

    private JPanel mainPanel;
    private JTable matrix;
    private JButton startPauseButton;
    private JTextArea textArea1;
    private JEditorPane editorPane1;
    private JTextPane textPane1;
    private JPanel titleLabel;
    private JLabel title;
    private JButton button1;
    private JScrollPane figuresList;
    private JPanel imagePanel;

    public Game() throws IOException {
        super();
        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        slika();
    }

    public void slika() throws IOException {
        imagePanel.setLayout(new FlowLayout());
        BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\Dobrovoljski\\Documents\\Druga_godina\\JAVA\\java\\PROJEKAT_2022\\DiamondCircle\\src\\sample\\four.JPG"));
        File f =new File("src/sample");
        System.out.println(f.getAbsolutePath());
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        imagePanel.add(picLabel);


        // image = new JLabel(new ImageIcon("C:\\Users\\Dobrovoljski\\Documents\\Druga_godina\\JAVA\\java\\PROJEKAT_2022\\DiamondCircle\\src\\sample\\four.JPG"));
    }


}
