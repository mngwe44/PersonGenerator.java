import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PersonReader {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Person Data File");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            // Read and display the file content
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String header = String.format("%-10s %-15s %-15s %-10s %-5s", "ID#", "Firstname", "Lastname", "Title", "YOB");
                String separator = "=".repeat(header.length());

                System.out.println(header);
                System.out.println(separator);

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",\s*"); // Split on commas with optional spaces
                    if (parts.length == 5) {
                        String formattedLine = String.format("%-10s %-15s %-15s %-10s %-5s",
                                parts[0], parts[1], parts[2], parts[3], parts[4]);
                        System.out.println(formattedLine);
                    } else {
                        System.out.println("Skipping malformed line: " + line);
                    }
                }

            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        } else {
            System.out.println("No file was selected.");
        }
    }
}
