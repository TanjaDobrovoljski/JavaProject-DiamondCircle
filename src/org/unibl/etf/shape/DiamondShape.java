package org.unibl.etf.shape;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.unibl.etf.tools.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import static sample.Game.playField;


public class DiamondShape implements Serializable {
   private  JButton[][] buttons;
    private  List<Tuple<Integer, Integer>> movementsOdd; //neparni
    private  List<Tuple<Integer, Integer>> movementsEven; //parni
    private static int matrixSize;
    public static int random=0;
    private static int movementSize=0;

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public static int getMatrixSize() {
        return matrixSize;
    }

    public DiamondShape() {

        int min = 7;
        int max =10;

        if(random==0)
            random=(int)Math.floor(Math.random()*(max-min+1)+min);
        random=7;
        playField.setLayout(new GridLayout(random,random));

        buttons = new JButton[random][random];

        int k=1;
        for (int y = 0; y < random; y++) {
            for (int x = 0; x < random; x++) {

                // Create a new TextField in each Iteration
                JTextField tf = new JTextField();
                tf.setText(String.valueOf(k));
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setEditable(false);
                buttons[x][y] = new JButton();
                buttons[x][y].setText(tf.getText());
                //buttons[x][y].setEnabled(false);
                buttons[x][y].setPreferredSize(new Dimension(100,100));
                playField.add(buttons[x][y]);
                k++;
            }
            playField.setEnabled(false);
        }


        matrixSize=random;

        diamondSpiral();

    }

    public List<Tuple<Integer, Integer>> getmovements()
    {
        if (matrixSize % 2 == 0)
            return getMovementsEven();
        else
            return getMovementsOdd();
    }



    public  List<Tuple<Integer, Integer>> getMovementsOdd() {
        return movementsOdd;
    }

    public void setMovementsOdd(List<Tuple<Integer, Integer>> movementsOdd) {
        this.movementsOdd = movementsOdd;
    }

    public  List<Tuple<Integer, Integer>> getMovementsEven() {
        return movementsEven;
    }

    public void setMovementsEven(List<Tuple<Integer, Integer>> movementsEven) {
        this.movementsEven = movementsEven;
    }

    public void spiralDiamondViewOdd(JPanel matrix,
                                     int x, int y, int m, int n, int k, List<Tuple<Integer,Integer>> movements) { //7x7

        Diamond d=new Diamond();

        Tuple<Integer,Integer> movement;
        //Get middle column
        int midCol = y + ((n - y) / 2);
        int midRow = midCol;

        for (int i = midCol, j = 0;
             i < n && k > 0; ++i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems(x + j, i);

            movements.add( movement);

            Rectangle r=new Rectangle();
           // buttons[x+j][i].setIcon(new ImageIcon(d.getDiamondImage()));
            //buttons[x+j][i].setDisabledIcon(new ImageIcon(d.getDiamondImage()));
           // addCirclesToGridPane(circles, x + j, i);// matrix[x + j][i]
        }

        for (int i = n, j = 0;
             i >= midCol && k > 0; --i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems((midRow) + j, i);

            movements.add( movement);
            //buttons[midRow+j][i].setIcon(new ImageIcon(d.getDiamondImage()));
            // addCirclesToGridPane(matrix, circles, (midRow) + j, i);// matrix[(midRow) + j][i]);
        }


        for (int i = midCol - 1, j = 1;
             i >= y && k > 0; --i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems( (n) - j, i);

            movements.add( movement);
            //buttons[n-j][i].setIcon(new ImageIcon(d.getDiamondImage()));
            //addCirclesToGridPane(matrix, circles, (n) - j, i);//matrix[(n) - j][i]);
        }

        for (int i = y + 1, j = 1;
             i < midCol && k > 0; ++i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems((midRow) - j, i);

            movements.add( movement);
           // buttons[midRow-j][i].setIcon(new ImageIcon(d.getDiamondImage()));
            //addCirclesToGridPane(matrix, circles, (midRow) - j, i);//matrix[(midRow) - j][i]);
        }
        if (x + 1 <= m - 1 && k > 0) {
            // Recursive call
            spiralDiamondViewOdd(matrix, x + 1,
                    y + 1, m - 1, n - 1, k,movements);
        }
    }

    public void spiralDiamondViewEven(JPanel matrix,
                                      int x, int y, int m, int n, int k,List<Tuple<Integer,Integer>> movements,int size) //8x8
    {

        Tuple<Integer,Integer> movement;
        // Get middle column
        int midCol = y + ((n - y) / 2);
        int midRow = midCol;

        for (int i = midCol, j = 0;
             i <= n && k > 0 ; ++i, k--, j++) {
            movement = new Tuple<>();
            movement.setItem1(x+j);
            movement.setItem2(i);

            movements.add(movement); //addCirclesToGridPane(circles, x + j, i);// matrix[x + j][i]
        }

        for (int i = n, j = 0;
             i-1 > midCol && k > 0 ; --i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems((midRow) + j+2, i-1);

            movements.add( movement);
            //addCirclesToGridPane(matrix, circles, (midRow) + j+2, i-1);// matrix[(midRow) + j][i]);
        }

        for (int i = midCol -1, j = 1;
             i+1 >= y && k > 0 && movements.size()<size; --i, k--, j++) { ///ovdje pogledaj
            movement = new Tuple<>();
            movement.setItems((n) - j, i+1);

            movements.add(movement);
            //addCirclesToGridPane(matrix, circles, (n) - j, i+1);//matrix[(n) - j][i]);
        }

        for (int i = y + 1, j = 1;
             i < midCol && k > 0 ; ++i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems((midRow) - j, i);

            movements.add(movement);
            //addCirclesToGridPane(matrix, circles, (midRow) - j, i);//matrix[(midRow) - j][i]);
        }
        if (x +1 <= m -1 && k > 0 ) {
            // Recursive call
            spiralDiamondViewEven(matrix, x +1,
                    y +1, m - 1, n - 1, k+9,movements,size);
        }


    }

   /* public static TextField getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for(Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return (TextField) result;
    }

   /* public boolean hasDiamond( int row,  int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for(Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {

                result = node;
                break;
            }
        }
        return (TextField) result;
    }*/




    public void diamondSpiral()
    {
        // Get the size
        int row = random;
        int col = random; //matrix[0].length;
        movementSize= random*col/2;
        if (row != col )
        {
            System.out.println("\nNot  odd square matrix");
            return;
        }
        if(row%2==0)
        {
            movementsEven=new LinkedList<>();
            spiralDiamondViewEven(playField, 0, 0, row - 1,
                    col - 1, (row * col) - ((col + 1) / 2) * 4,movementsEven,movementSize);}
        else {
            movementsOdd=new LinkedList<>();
            spiralDiamondViewOdd(playField, 0, 0, row - 1,
                    col - 1, (row * col) - ((col + 1) / 2) * 4,movementsOdd);
        }


    }

    public static int getMovementSize() {
        return movementSize;
    }

    public static void setMovementSize(int movementSize) {
        DiamondShape.movementSize = movementSize;
    }



   /* private static int c=0;
    public static  void addCirclesToGridPane(List<Ellipse2D> circles, int a, int b) //dodavanje figura kao krugova
    {
        playField.add(circles.get(c), b,a);
        c++;
    }*/

    /*public static synchronized void drawMatrix(int xCoordinate,int yCoordinate)
    {
        //playField.getChildren().clear();
        int k=1;
        for (int y = 0; y < random; y++) {
            for (int x = 0; x < random; x++) {

                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setPrefHeight(170);
                tf.setPrefWidth(170);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(true);
                tf.setText(String.valueOf(k));
                playField.setRowIndex(tf, y);
                playField.setColumnIndex(tf, x);
                playField.add(tf, x, y);
                k++;
            }
        }
        playField.add(circles.get(0),yCoordinate,xCoordinate);
        c=0;

    }*/


    /*private static int r=0;
    public void addRectangleToGridPane(JTable gridPane, Graphics g,int a, int b) //dodavanje rupa
    {
        gridPane.add(g.drawRect(50, 35, 150, 150),a,b);
        r++;

    }*/



}
