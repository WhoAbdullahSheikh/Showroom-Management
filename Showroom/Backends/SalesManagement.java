package Backends;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Frontend.home;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesManagement extends JFrame {

    private JLabel dateTimeLabel;

    public SalesManagement() {
        setTitle("Inventory Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the layout to null for absolute positioning
        setLayout(null);

        // Set the size and center the frame on the screen
        setSize(1797, 980);
        setLocationRelativeTo(null);

        // Create a JLabel with the background image
        ImageIcon backgroundImageIcon = new ImageIcon("pics/bg5.jpeg");
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Set the background label color to black
        backgroundLabel.setBackground(Color.BLACK);
        backgroundLabel.setOpaque(true);

        // Add the background label to the content pane
        getContentPane().add(backgroundLabel);

        dateTimeLabel = new JLabel();
        dateTimeLabel.setBounds(30, 10, 500, 30);
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setFont(dateTimeLabel.getFont().deriveFont(Font.BOLD, 16));

        // Update the label with live date and time
        updateDateTime();

        // Create a Timer to update the date and time every second
        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        // Add the components to the background label
        backgroundLabel.add(dateTimeLabel);

        // Create buttons
        JButton button1 = new JButton("Customer Information");
        button1.setBounds(100, 350, 200, 30);

        JButton button2 = new JButton("Sales Order");
        button2.setBounds(100, 400, 200, 30);

        JButton button3 = new JButton("Invoices");
        button3.setBounds(100, 450, 200, 30);

        JButton button4 = new JButton("Back");
        button4.setBounds(150, 550, 90, 30);

        /*JButton button5 = new JButton("Home");
        button5.setBounds(200, 550, 90, 30);*/

        button4.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                home home = new home();
                home.setVisible(true);
            }
        });

        // Add buttons to the background label
        backgroundLabel.add(button1);
        backgroundLabel.add(button2);
        backgroundLabel.add(button3);
        backgroundLabel.add(button4);
        //backgroundLabel.add(button5);
    }

    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy   ||   HH:mm:ss a");
        String dateTime = dateFormat.format(new Date());
        dateTimeLabel.setText(dateTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SalesManagement app = new SalesManagement();
            app.setVisible(true);
        });
    }
}
