import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaParserUtils {

    // This method reads a Java file and returns a catalog of methods and control structures as a String
    public static String parseJavaFile(String filePath) {
        StringBuilder catalog = new StringBuilder();  // Store the final output
        List<String> lines = readFile(filePath);      // Read all lines from the file

        if (lines.isEmpty()) {
            return "Error: Could not read the file or the file is empty.";
        }

        // Add methods to the catalog
        catalog.append("Methods:\n");
        for (String line : lines) {
            if (isMethod(line)) {
                catalog.append(line.trim()).append("\n");
            }
        }

        // Add control structures to the catalog
        catalog.append("\nControl Structures:\n");
        for (String line : lines) {
            if (isControlStructure(line)) {
                catalog.append(line.trim()).append("\n");
            }
        }

        return catalog.toString();  // Return the complete catalog
    }

    // Reads the Java file line by line and returns a list of lines
    private static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    // Checks if a line contains a method signature by looking for common patterns
    private static boolean isMethod(String line) {
        return line.contains("(") && line.contains(")") && line.endsWith("{");
    }

    // Checks if a line contains a common control structure keyword
    private static boolean isControlStructure(String line) {
        return line.trim().startsWith("if") || 
               line.trim().startsWith("else") || 
               line.trim().startsWith("for") || 
               line.trim().startsWith("while") || 
               line.trim().startsWith("switch") || 
               line.trim().startsWith("try") || 
               line.trim().startsWith("catch");
    }
}
