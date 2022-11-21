package com.uob.client;

import java.util.Timer;
import java.util.TimerTask;

public class Time extends TimerTask{
	static int second;
	static int minutes;
	static String timer;
	public void run() {
        second++;
        if(second==60)
        {
            minutes++;
            second-=60;
        }
       timer=minutes+":"+second;
       System.out.println("here");
       GameRoundGUI.txtTime.setText(timer);
       if(GameRound2.txtTime !=null)
       {
       GameRound2.txtTime.setText(timer);
       }
    }
}
