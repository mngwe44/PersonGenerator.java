import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {

    public static void main(String[] args) {
        ArrayList<String> personRecords = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean moreEntries = true;

        System.out.println("Welcome to PersonGenerator!");

        while (moreEntries) {
            System.out.println("Enter details for a new person.");

            String id = SafeInput.getNonZeroLenString(in, "Enter ID (e.g., 000001):");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name:");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name:");
            String title = SafeInput.getNonZeroLenString(in, "Enter Title (e.g., Mr., Mrs., Dr., etc.):");
            int yearOfBirth = SafeInput.getInt(in, "Enter Year of Birth:");

            String record = String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth);
            personRecords.add(record);

            moreEntries = SafeInput.getYNConfirm(in, "Do you want to enter another person? (Y/N):");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the file name to save the data (e.g., PersonTestData.txt):");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String record : personRecords) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
