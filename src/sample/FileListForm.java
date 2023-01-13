package sample;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileListForm extends JFrame implements Serializable {
    private JTable fileTable;
    private JPanel panel1;
    private Path path;

    public FileListForm(Path path)
    {
        super();
        setContentPane(panel1);
        setVisible(true);
        pack();
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.path=path;
        File[] files= path.toFile().listFiles();

        DefaultTableModel model= (DefaultTableModel) fileTable.getModel();
        model.setColumnIdentifiers(new String[]{"Files names"});
        Object[] row=new Object[1];
        for(int i=0;i<files.length;i++)
        {
            row[0]=files[i].getName();
            model.addRow(row);
        }


        fileTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = fileTable.getSelectedRow();
                try {
                    Desktop.getDesktop().open(files[row]);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
