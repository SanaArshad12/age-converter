import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

class Date {
    int year;
    int month;
    int day;
}

 class AgeCalculatorGUI {

    // Parses the input date string (YYYY-MM-DD) into a Date object
    public static Date parseDate(String dateStr) {
        Date date = new Date();
        String[] parts = dateStr.split("-");
        date.year = Integer.parseInt(parts[0]);
        date.month = Integer.parseInt(parts[1]);
        date.day = Integer.parseInt(parts[2]);

        return date;
    }

    // Calculates the age based on birthdate and current date
    public static int calculateAge(Date birthdate, Date currentDate) {
        int age = currentDate.year - birthdate.year;

        // Adjust age if birthdate hasn't occurred yet in the current year
        if (currentDate.month < birthdate.month ||
                (currentDate.month == birthdate.month && currentDate.day < birthdate.day)) {
            age--;
        }

        return age;
    }

    // Sets the current date from the system date
    public static void setCurrentDate(Date currentDate) {
        LocalDate today = LocalDate.now();
        currentDate.year = today.getYear();
        currentDate.month = today.getMonthValue();
        currentDate.day = today.getDayOfMonth();
    }

    // Creates the main window with enhanced styling
    public static void createAndShowGUI() {
        // Create the frame with a modern look and feel
        JFrame frame = new JFrame("Age Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(0, 0, 128)); // Navy blue background

        // Create and style labels and text field
        JLabel label = new JLabel("Enter your birthdate (YYYY-MM-DD): ");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField birthdateField = new JTextField(10);
        birthdateField.setFont(new Font("Arial", Font.PLAIN, 14));
        birthdateField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton calculateButton = new JButton("Calculate Age");
        calculateButton.setBackground(new Color(60, 179, 113)); // Custom button color (Sea Green)
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel resultLabel = new JLabel("Your age will appear here.");
        resultLabel.setForeground(new Color(173, 216, 230)); // Light blue color for result text
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Organize components using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(birthdateField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(calculateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(resultLabel, gbc);

        // Action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String birthdateStr = birthdateField.getText();

                    // Parse the birthdate entered by the user
                    Date birthdate = parseDate(birthdateStr);

                    // Get the current system date
                    Date currentDate = new Date();
                    setCurrentDate(currentDate);

                    // Calculate the age
                    int age = calculateAge(birthdate, currentDate);

                    // Display the result
                    resultLabel.setText("Your age is: " + age + " years.");
                } catch (Exception ex) {
                    // If input is invalid, show an error message
                    JOptionPane.showMessageDialog(frame, "Please enter a valid date in YYYY-MM-DD format.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Display the window
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Set a modern look and feel for the GUI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Schedule a job for the event dispatch thread (Swing)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
