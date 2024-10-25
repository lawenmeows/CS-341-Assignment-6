import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class JMCSCataloger extends JFrame {
    private JTextField filePathField;  // Shows the selected file path
    private JTextArea catalogArea;     // Displays the catalog output

    public JMCSCataloger() {
        setTitle("JMCS Cataloger");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Top panel for selecting a file
        JPanel topPanel = new JPanel(new FlowLayout());
        filePathField = new JTextField(30);
        JButton browseButton = new JButton("Browse");
        topPanel.add(filePathField);
        topPanel.add(browseButton);

        // Center panel for showing the catalog
        catalogArea = new JTextArea();
        catalogArea.setEditable(false);  // User can't edit the catalog
        JScrollPane scrollPane = new JScrollPane(catalogArea);

        // Bottom panel for buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton generateButton = new JButton("Generate Catalog");
        JButton clearButton = new JButton("Clear");
        bottomPanel.add(generateButton);
        bottomPanel.add(clearButton);

        // Add everything to the main window
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        browseButton.addActionListener(e -> browseFile());
        generateButton.addActionListener(e -> generateCatalog());
        clearButton.addActionListener(e -> catalogArea.setText(""));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Open a file browser to select a Java file
    private void browseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    // Generate the catalog (will call a helper function)
    private void generateCatalog() {
        String filePath = filePathField.getText();
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a Java file.");
        } else {
            String catalog = JavaParserUtils.parseJavaFile(filePath);
            catalogArea.setText(catalog);
        }
    }

    public static void main(String[] args) {
        new JMCSCataloger();
    }
}
