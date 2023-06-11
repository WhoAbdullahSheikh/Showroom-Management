package Frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Database.DatabaseManager;
public class login extends JFrame {

    private DatabaseManager databaseManager;

    public login() {
        setTitle("Black Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the layout to null for absolute positioning
        setLayout(null);

        // Set the size and center the frame on the screen
        setSize(626, 360);
        setLocationRelativeTo(null);

        // Create a JLabel with the background image
        ImageIcon backgroundImageIcon = new ImageIcon("pics/bg4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Create username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(350, 100, 100, 50);
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(Font.BOLD)); // Set label text to bold
        usernameLabel.setForeground(Color.white);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(440, 115, 150, 20);
        // Create password label and password field

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(350, 140, 100, 50);
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.BOLD)); // Set label text to bold
        passwordLabel.setForeground(Color.WHITE);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(440, 155, 150, 20);

        // Create submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(440, 200, 80, 30);

        // Create an instance of the DatabaseManager
        databaseManager = new DatabaseManager();

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Verify credentials against the database
                if (databaseManager.verifyCredentials(username, password)) {
                    // Credentials are valid
                    JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    home home = new home();
                    home.setVisible(true);
                    
                } else {
                    // Invalid credentials
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the components to the background label
        backgroundLabel.add(usernameLabel);
        backgroundLabel.add(usernameField);
        backgroundLabel.add(passwordLabel);
        backgroundLabel.add(passwordField);
        backgroundLabel.add(submitButton);

        // Add the background label to the content pane
        getContentPane().add(backgroundLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                login app = new login();
                app.setVisible(true);
            }
        });
    }
} 
    
