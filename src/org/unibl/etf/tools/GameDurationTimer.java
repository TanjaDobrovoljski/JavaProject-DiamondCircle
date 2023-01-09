package org.unibl.etf.tools;

import sample.Game;

import javax.swing.*;
import java.text.DecimalFormat;

public class GameDurationTimer  extends Thread{
   private int second,minute;

    private String ddSeconds,ddMinutes;
    private DecimalFormat dFormat=new DecimalFormat("00");
    private Game g;
    private boolean paused = false;

    public GameDurationTimer(Game g)
    {
        this.second=0;
        this.minute=0;
        this.g=g;
    }

    @Override
    public  void run()
    {
        while (true)
        {
            if (paused)
            {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            second++;
            ddSeconds=dFormat.format(second);
            ddMinutes=dFormat.format(minute);
            g.getGameTime().setText(ddMinutes+" : "+ddSeconds);
            if(second==60)
            {
                second=0;
                minute++;
                g.getGameTime().setText(ddMinutes+" : "+ddSeconds);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
    }


