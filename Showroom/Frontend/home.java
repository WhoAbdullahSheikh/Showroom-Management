package Frontend;

import javax.swing.*;
import Backends.InventoryManagement;
import Backends.SalesManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class home extends JFrame {

    private JLabel dateTimeLabel;
    public home() {
        setTitle("Black Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the layout to null for absolute positioning
        setLayout(null);

        // Set the size and center the frame on the screen
        setSize(1797, 980);
        setLocationRelativeTo(null);

        // Create a JLabel with the background image
        ImageIcon backgroundImageIcon = new ImageIcon("pics/bg3.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Set the background label color to black
        backgroundLabel.setBackground(Color.BLACK);
        backgroundLabel.setOpaque(true);

        // Create buttons
        JButton button1 = new JButton("Inventory Management");
        button1.setBounds(100, 100, 200, 30);

        JButton button2 = new JButton("Sales Management");
        button2.setBounds(100, 150, 200, 30);

        JButton button3 = new JButton("Finance & Accounts");
        button3.setBounds(100, 200, 200, 30);

        // Add action listeners for the buttons
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to be executed when button 1 is clicked
                // ...

                InventoryManagement inventoryManagement = new InventoryManagement();
                inventoryManagement.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to be executed when button 2 is clicked
                // ...
                SalesManagement salesManagement = new SalesManagement();
                salesManagement.setVisible(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to be executed when button 3 is clicked
                // ...
            }
        });

        // Add hover color to the buttons
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button1.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button1.setBackground(null);
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button2.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button2.setBackground(null);
            }
        });

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button3.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button3.setBackground(null);
            }
        });

        dateTimeLabel = new JLabel();
        dateTimeLabel.setBounds(30, 10, 500, 30);
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Update the label with live date and time
        updateDateTime();

        // Create a Timer to update the date and time every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDateTime();
            }
        });
        timer.start();
        // Add the buttons to the background label
        backgroundLabel.add(button1);
        backgroundLabel.add(button2);
        backgroundLabel.add(button3);
        backgroundLabel.add(dateTimeLabel);

        // Add the background label to the content pane
        getContentPane().add(backgroundLabel);
    }

    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy   ||   HH:mm:ss a");
        String dateTime = dateFormat.format(new Date());
        dateTimeLabel.setText(dateTime);
    }
    
}
