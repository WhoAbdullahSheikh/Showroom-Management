package Backends;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import Frontend.home;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InventoryManagement extends JFrame {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/showroom";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private JLabel dateTimeLabel;
    public InventoryManagement() {
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

        // Add inventory management features
        // ...

        // Create text fields
        JLabel makeLabel = new JLabel("Make:");
        makeLabel.setBounds(50, 300, 100, 30);
        makeLabel.setForeground(Color.WHITE);
        makeLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD));
        

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(50, 350, 100, 30);
        modelLabel.setForeground(Color.WHITE);
        modelLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD));

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(50, 400, 100, 30);
        yearLabel.setForeground(Color.WHITE);
        yearLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD));


        JLabel colorLabel = new JLabel("Color:");
        colorLabel.setBounds(50, 450, 100, 30);
        colorLabel.setForeground(Color.WHITE);
        colorLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD));

        JLabel vinLabel = new JLabel("VIN:");
        vinLabel.setBounds(50, 500, 100, 30);
        vinLabel.setForeground(Color.WHITE);
        vinLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD));

        JLabel pricingLabel = new JLabel("Pricing:");
        pricingLabel.setBounds(50, 550, 100, 30);
        pricingLabel.setForeground(Color.WHITE);
        pricingLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD));

        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setBounds(50, 600, 100, 30);
        availabilityLabel.setForeground(Color.WHITE);
        availabilityLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD));

        JTextField makeField = new JTextField();
        makeField.setBounds(160, 300, 200, 30);
     

        JTextField modelField = new JTextField();
        modelField.setBounds(160, 350, 200, 30);

        JTextField yearField = new JTextField();
        yearField.setBounds(160, 400, 200, 30);

        JTextField colorField = new JTextField();
        colorField.setBounds(160, 450, 200, 30);

        JTextField vinField = new JTextField();
        vinField.setBounds(160, 500, 200, 30);

        JTextField pricingField = new JTextField();
        pricingField.setBounds(160, 550, 200, 30);

        JTextField availabilityField = new JTextField();
        availabilityField.setBounds(160, 600, 200, 30);

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(160, 700, 100, 30);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(50, 700, 100, 30);

        // Add action listeners for the buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle home button action
               home home = new home();
               home.setVisible(true);

            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the data from the fields
                String make = makeField.getText();
                String model = modelField.getText();
                String year = yearField.getText();
                String color = colorField.getText();
                String vin = vinField.getText();
                String price = pricingField.getText();
                String availability = availabilityField.getText();

                // Save the data in the database
                try {
                    // Establish a connection to the database
                    Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    // Create a PreparedStatement to insert the data
                    String sql = "INSERT INTO inventory (make, model, year, color, vin, price, availability) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, make);
                    statement.setString(2, model);
                    statement.setString(3, year);
                    statement.setString(4, color);
                    statement.setString(5, vin);
                    statement.setString(6, price);
                    statement.setString(7, availability);

                    // Execute the insert statement
                    statement.executeUpdate();

                    // Close the statement and connection
                    statement.close();
                    connection.close();

                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Data saved successfully.");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occurred while saving data.");
                }
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

        // Add the components to the background label
        backgroundLabel.add(makeLabel);
        backgroundLabel.add(modelLabel);
        backgroundLabel.add(yearLabel); 
        backgroundLabel.add(colorLabel);
        backgroundLabel.add(vinLabel);
        backgroundLabel.add(pricingLabel);
        backgroundLabel.add(availabilityLabel);
        backgroundLabel.add(makeField);
        backgroundLabel.add(modelField);
        backgroundLabel.add(yearField);
        backgroundLabel.add(colorField);
        backgroundLabel.add(vinField);
        backgroundLabel.add(pricingField);
        backgroundLabel.add(availabilityField);
        backgroundLabel.add(homeButton);
        backgroundLabel.add(saveButton);
        backgroundLabel.add(dateTimeLabel);
    }
    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy   ||   HH:mm:ss a");
        String dateTime = dateFormat.format(new Date());
        dateTimeLabel.setText(dateTime);
    }

}
