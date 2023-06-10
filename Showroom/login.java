import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {

    private DatabaseManager databaseManager;

    public login() {
        setTitle("Black Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the layout to null for absolute positioning
        setLayout(null);

        // Set the size and center the frame on the screen
        setSize(808, 360);
        setLocationRelativeTo(null);

        // Create a JLabel with the background image
        ImageIcon backgroundImageIcon = new ImageIcon("pics/bg2.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Create username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 100, 100, 50);
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(Font.BOLD)); // Set label text to bold
        JTextField usernameField = new JTextField();
        usernameField.setBounds(210, 115, 150, 20);

        // Create password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 140, 100, 50);
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.BOLD)); // Set label text to bold
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(210, 155, 150, 20);

        // Create submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(210, 200, 80, 30);

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
