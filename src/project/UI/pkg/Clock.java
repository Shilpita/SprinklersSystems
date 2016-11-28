package project.UI.pkg;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.awt.Font;
import java.text.SimpleDateFormat;

public class Clock {
    public static final JLabel time = new JLabel();
    private final SimpleDateFormat sdf  = new SimpleDateFormat("hh:mm");
    private int currentSecond;
    private Calendar calendar;

    public static void main( String [] args ) {
        JFrame frame = new JFrame();
        Clock clock = new Clock();
        frame.add(clock.time);
        frame.setSize(100, 100);
        frame.pack();
        frame.setVisible( true );
        clock.start();
    }
    
    private void reset(){
        calendar = Calendar.getInstance();
        currentSecond = calendar.get(Calendar.SECOND);
    }
    
    public void start(){
        reset();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask(){
            public void run(){
                if( currentSecond == 60 ) {
                    reset();
                }
                time.setText( String.format("%s:%02d", sdf.format(calendar.getTime()), currentSecond ));
        		//time.setFont(new Font("Pristina", Font.BOLD, 20));
                currentSecond++;
            }
        }, 0, 1000 );
    }
}