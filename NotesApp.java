import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Java Notes Manager!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Write a note");
            System.out.println("2. Read notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    writeNote(scanner);
                    break;
                case "2":
                    readNotes();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Writes a note to the file in append mode.
     */
    private static void writeNote(Scanner scanner) {
        System.out.print("Enter your note (single line): ");
        String note = scanner.nextLine();

        // Use try-with-resources for automatic resource management and exception handling
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // true for append mode
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads all notes from the file and displays them.
     */
    private static void readNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No notes found yet.");
            return;
        }

        // Using BufferedReader for efficient reading, wrapped around FileReader
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n--- Your Notes ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("------------------");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}