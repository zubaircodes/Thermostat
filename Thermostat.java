import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A very simple GUI (graphical user interface) for the clock display.
 * In this implementation, time runs at about 3 minutes per second, so that
 * testing the display is a little quicker.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Thermostat
{
    private JFrame frame;
    private JLabel labelClock, labelHeader, labelThermostat, labelTemperature;
    private ClockDisplay clock;
    private HeatDisplay heat;
    private int minTemperature = 10;
    private int maxTemperature = 30;
    private int increment = 1;
    private String c_or_f = "C";
    
    private boolean clockRunning = false;
    private TimerThread timerThread;
    // private JPanel heaterPanel, clockPanel, headerPanel;
    
    /**
     * Constructor for objects of class Thermostat
     */
    public Thermostat()
    {
        makeFrame();
        clock = new ClockDisplay();
        heat = new HeatDisplay(maxTemperature, minTemperature, increment);
    }
    
    /**
     * 
     */
    private void start()
    {
        clockRunning = true;
        timerThread = new TimerThread();
        timerThread.start();
    }
    
    /**
     * 
     */
    private void stop()
    {
        clockRunning = false;
    }
    
    /**
     * 
     */
    private void step()
    {
        String tempString;
        String displayString;
        
        clock.timeTick();
        labelClock.setText(clock.getTime()); 
        
        labelTemperature.setText(heat.getTemperature(c_or_f));
    }
    
    /**
     * 
     */
    private void up()
    {
        String unit;
        String tempString;
        String displayString;
        
        heat.warmerTemp();
        
        labelTemperature.setText(heat.getTemperature(c_or_f));
    }
    
    /**
     * 
     */
    private void down()
    {
        String unit;
        String tempString, displayString;
        
        heat.coolerTemp();
        
        labelTemperature.setText(heat.getTemperature(c_or_f));
    }
    
    private void togleCToF()
    {
        if (c_or_f.equals("C")) {
            c_or_f = "F";
        }
        else {
            c_or_f = "C";
        };
        
        labelTemperature.setText(heat.getTemperature(c_or_f));
    }
    
    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog (frame, 
                    "Clock Version 1.0\n" +
                    "A simple interface for the 'Objects First' clock display project",
                    "About Clock", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }

    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("Clock");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(1, 60, 1, 60));

        makeMenuBar(frame);
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(12, 12));
        
        // Create the header in the center
        JPanel headerPanel = new JPanel();
        labelHeader = new JLabel("COMP 2001 Thermostat", SwingConstants.CENTER);
        Font displayFontHeader = labelHeader.getFont().deriveFont(30.0f);
        labelHeader.setFont(displayFontHeader);
        //imagePanel.setBorder(new EtchedBorder());
        headerPanel.add(labelHeader);
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        
        
        // Create the clock pane in the center
        
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new GridLayout(2,0));
        
        //Create the Clock display
        JPanel clockPanel = new JPanel();
        labelClock = new JLabel("00:00", SwingConstants.CENTER);
        Font displayFontClock = labelClock.getFont().deriveFont(60.0f);
        labelClock.setFont(displayFontClock);
        //imagePanel.setBorder(new EtchedBorder());
        clockPanel.add(labelClock);
        
        timePanel.add(clockPanel);
        
        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(1, 0));
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> start());
        toolbar.add(startButton);
        
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stop());
        toolbar.add(stopButton);

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(e -> step());
        toolbar.add(stepButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);
        
        timePanel.add(flow);
        
        contentPane.add(timePanel, BorderLayout.CENTER);
        
        //contentPane.add(labelThermostat, BorderLayout.CENTER);
        
        
              
        // Create the thermostat pane in the center
        
        JPanel thermostatPanel = new JPanel();
        thermostatPanel.setLayout(new GridLayout(2,0));
        
        //Create the Clock display
        JPanel heatPanel = new JPanel();
        labelTemperature = new JLabel("20 C", SwingConstants.CENTER);
        Font displayFontTemperature = labelTemperature.getFont().deriveFont(60.0f);
        labelTemperature.setFont(displayFontTemperature);
        //imagePanel.setBorder(new EtchedBorder());
        heatPanel.add(labelTemperature);
        
        thermostatPanel.add(heatPanel);
        
        // Create the toolbar with the buttons
        JPanel toolbarHeat = new JPanel();
        toolbarHeat.setLayout(new GridLayout(1, 0));
        
        JButton upButton = new JButton("Up");
        upButton.addActionListener(e -> up());
        toolbarHeat.add(upButton);
        
        JButton downButton = new JButton("Down");
        downButton.addActionListener(e -> down());
        toolbarHeat.add(downButton);

        JButton unitButton = new JButton("C/F");
        unitButton.addActionListener(e -> togleCToF());
        toolbarHeat.add(unitButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flowHeat = new JPanel();
        flowHeat.add(toolbarHeat);
        
        thermostatPanel.add(flowHeat);
        
        //contentPane.add(labelThermostat, BorderLayout.CENTER);
        contentPane.add(thermostatPanel, BorderLayout.SOUTH);

        
        // building is done - arrange the components      
        frame.pack();
        
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     * 
     * @param frame   The frame that the menu bar should be added to.
     */
    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("About Clock...");
            item.addActionListener(e -> showAbout());
        menu.add(item);

        menu.addSeparator();
        
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> quit());
        menu.add(item);
    }
    
    class TimerThread extends Thread
    {
        public void run()
        {
            while (clockRunning) {
                step();
                pause();
            }
        }
        
        private void pause()
        {
            try {
                Thread.sleep(300);   // pause for 300 milliseconds
            }
            catch (InterruptedException exc) {
            }
        }
    }
}