package org.unibl.etf.shape;


import java.awt.*;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.unibl.etf.tools.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import static sample.Game.playField;


public class DiamondShape implements Serializable {


    private JTable matrix;
    private  List<Tuple<Integer, Integer>> movementsOdd; //neparni
    private  List<Tuple<Integer, Integer>> movementsEven; //parni
    public static int matrixSize;
    public static int random=0;
    private static int movementSize=0;

    public DiamondShape() {

        int min = 7;
        int max =10;

        if(random==0)
            random=(int)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println(random);
        DefaultTableModel data = new DefaultTableModel(random , random);// rows, cols
        playField.setModel(data);
        int k=1;
        for (int y = 0; y < random; y++) {
            for (int x = 0; x < random; x++) {

                // Create a new TextField in each Iteration
                JTextField tf = new JTextField();
                tf.setText(String.valueOf(k));
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setEditable(false);
                playField.setValueAt(tf.getText(),y,x);
               playField.setRowHeight(60);
                k++;
            }
            playField.setEnabled(false);
        }
        /*playField.setAlignment(Pos.CENTER);
        matrixSize=playField.getColumnCount();
        matrix=playField;
        diamondSpiral(playField);*/
       // playField.setSize(1500,1500);
        //playField.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
/*
    public List<Tuple<Integer, Integer>> getmovements()
    {
        if (matrixSize % 2 == 0)
            return getMovementsEven();
        else
            return getMovementsOdd();
    }

    public GridPane getMatrix() {
        return matrix;
    }

    public void setMatrix(GridPane matrix) {
        this.matrix = matrix;
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

    public void spiralDiamondViewOdd(GridPane matrix,
                                     int x, int y, int m, int n, int k, List<Tuple<Integer,Integer>> movements) { //7x7



        Tuple<Integer,Integer> movement;
        //Get middle column
        int midCol = y + ((n - y) / 2);
        int midRow = midCol;

        for (int i = midCol, j = 0;
             i < n && k > 0; ++i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems(x + j, i);

            movements.add( movement);
            //addCirclesToGridPane(circles, x + j, i);// matrix[x + j][i]
        }

        for (int i = n, j = 0;
             i >= midCol && k > 0; --i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems((midRow) + j, i);

            movements.add( movement);
            // addCirclesToGridPane(matrix, circles, (midRow) + j, i);// matrix[(midRow) + j][i]);
        }


        for (int i = midCol - 1, j = 1;
             i >= y && k > 0; --i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems( (n) - j, i);

            movements.add( movement);
            //addCirclesToGridPane(matrix, circles, (n) - j, i);//matrix[(n) - j][i]);
        }

        for (int i = y + 1, j = 1;
             i < midCol && k > 0; ++i, k--, j++) {
            movement = new Tuple<>();
            movement.setItems((midRow) - j, i);

            movements.add( movement);
            //addCirclesToGridPane(matrix, circles, (midRow) - j, i);//matrix[(midRow) - j][i]);
        }
        if (x + 1 <= m - 1 && k > 0) {
            // Recursive call
            spiralDiamondViewOdd(matrix, x + 1,
                    y + 1, m - 1, n - 1, k,movements);
        }
    }

    public void spiralDiamondViewEven(GridPane matrix,
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

    public static TextField getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
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



/*
    public void diamondSpiral(GridPane matrix)
    {
        // Get the size
        int row = playField.getRowCount();
        int col = playField.getColumnCount(); //matrix[0].length;
        movementSize=playField.getColumnCount()*col/2;
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

    private static int c=0;
    public static  void addCirclesToGridPane(List<Circle> circles, int a, int b) //dodavanje figura kao krugova
    {

        playField.add(circles.get(c), b,a);
        c++;
    }

    public static synchronized void drawMatrix(int xCoordinate,int yCoordinate)
    {
        List<Circle> circles = new ArrayList(); //prikaz figura kao krugova
        for (int j = 0; j < 1; j++) {
            circles.add(new Circle(10, Color.BLACK));
        }
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

    }


    private static int r=0;
    public void addRectangleToGridPane(GridPane gridPane, Rectangle rects,int a, int b) //dodavanje rupa
    {
        gridPane.add(rects,a,b);
        r++;

    }
*/
}
