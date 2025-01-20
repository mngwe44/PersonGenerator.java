import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProductReader {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Product Data File");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String header = String.format("%-10s %-15s %-25s %-10s", "ID#", "Name", "Description", "Cost");
                String separator = "=".repeat(header.length());

                System.out.println(header);
                System.out.println(separator);

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",\s*"); // Split on commas with optional spaces
                    if (parts.length == 4) {
                        String formattedLine = String.format("%-10s %-15s %-25s %-10.2f",
                                parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
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
