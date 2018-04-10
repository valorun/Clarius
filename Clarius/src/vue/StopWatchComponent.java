package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class StopWatchComponent extends JComponent {

    private JLabel label;
    private long lastTickTime;
    private Timer timer;

    public StopWatchComponent() {
    	super();
    	this.setLayout(new BorderLayout()); 
        label = new JLabel(String.format("%02d.%03d", 0, 0));

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long runningTime = System.currentTimeMillis() - lastTickTime;
                Duration duration = Duration.ofMillis(runningTime);
                long millis = duration.toMillis();
                long seconds = millis / 1000;
                millis -= (seconds * 1000);
                label.setText(String.format("%02d.%03d", seconds, millis));
            }
        });
        this.setBorder(new TitledBorder(" Timer "));
        this.setMinimumSize(new Dimension(120, 50));
        add(label);

    }
    public void start() {
    	if (!timer.isRunning()) {
            lastTickTime = System.currentTimeMillis();
            timer.start();
        }
    }
    public void stop() {
    	timer.stop();
    }

}