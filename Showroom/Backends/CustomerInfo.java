package Backends;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class CustomerInfo extends JFrame {

    private JLabel dateTimeLabel;

    public CustomerInfo() {
        setTitle("Customer Infromation");
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

        // Create labels
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(700, 350, 100, 30);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(700, 400, 100, 30);
        contactLabel.setForeground(Color.WHITE);
        contactLabel.setFont(contactLabel.getFont().deriveFont(Font.BOLD));

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(700, 450, 100, 30);
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setFont(addressLabel.getFont().deriveFont(Font.BOLD));

        JLabel cnicLabel = new JLabel("CNIC:");
        cnicLabel.setBounds(700, 500, 100, 30);
        cnicLabel.setForeground(Color.WHITE);
        cnicLabel.setFont(cnicLabel.getFont().deriveFont(Font.BOLD));

        // Create text fields
        JTextField nameField = new JTextField();
        nameField.setBounds(800, 350, 200, 30);

        JTextField contactField = new JTextField();
        contactField.setBounds(800, 400, 200, 30);

        JTextField addressField = new JTextField();
        addressField.setBounds(800, 450, 200, 30);

        JTextField cnicField = new JTextField();
        cnicField.setBounds(800, 500, 200, 30);

        // Create buttons
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(850, 600, 100, 30);

        JButton backButton = new JButton("Back");
        backButton.setBounds(750, 600, 100, 30);

       
        backgroundLabel.add(nameLabel);
        backgroundLabel.add(contactLabel);
        backgroundLabel.add(addressLabel);
        backgroundLabel.add(cnicLabel);
        backgroundLabel.add(nameField);
        backgroundLabel.add(contactField);
        backgroundLabel.add(addressField);
        backgroundLabel.add(cnicField);
        backgroundLabel.add(saveButton);
        backgroundLabel.add(backButton);

      
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String contact = contactField.getText();
                String address = addressField.getText();
                String cnic = cnicField.getText();

                // Save the data to the database
                saveDataToDatabase(name, contact, address, cnic);
            }
        });
        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                SalesManagement salesManagement = new SalesManagement();
                salesManagement.setVisible(true);
            }
        });
    }

    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy   ||   HH:mm:ss a");
        String dateTime = dateFormat.format(new Date());
        dateTimeLabel.setText(dateTime);
    }

    private void saveDataToDatabase(String name, String contact, String address, String cnic) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/showroom";
        String username = "root";
        String password = "";

       
        String query = "INSERT INTO customerinfo (name, contact, address, cnic) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameter values
            statement.setString(1, name);
            statement.setString(2, contact);
            statement.setString(3, address);
            statement.setString(4, cnic);

            // Execute the query
            statement.executeUpdate();

            // Display a success message
            System.out.println("Data saved to the database.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }

   
}
