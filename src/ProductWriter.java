import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {

    public static void main(String[] args) {
        ArrayList<String> productRecords = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean moreEntries = true;

        System.out.println("Welcome to ProductWriter!");

        while (moreEntries) {
            System.out.println("Enter details for a new product.");

            String id = SafeInput.getNonZeroLenString(in, "Enter Product ID (e.g., 000001):");
            String name = SafeInput.getNonZeroLenString(in, "Enter Product Name:");
            String description = SafeInput.getNonZeroLenString(in, "Enter Product Description:");
            double cost = SafeInput.getDouble(in, "Enter Product Cost:");

            String record = String.format("%s, %s, %s, %.2f", id, name, description, cost);
            productRecords.add(record);


            moreEntries = SafeInput.getYNConfirm(in, "Do you want to enter another product? (Y/N):");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the file name to save the data (e.g., ProductTestData.txt):");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String record : productRecords) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}