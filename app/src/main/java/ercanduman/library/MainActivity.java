package ercanduman.library;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ercanduman.library.DatabaseHandler.AddNewBook2Database;
import static ercanduman.library.DatabaseHandler.ListAllBooks;
import static ercanduman.library.DatabaseHandler.RemoveBookFromDatabse;
import static ercanduman.library.DatabaseHandler.establishConnection;
import static ercanduman.library.GlobalConfigs.LOG_FAILURE_PREFIX;
import static ercanduman.library.GlobalConfigs.LOG_SUCCESS_PREFIX;

public class MainActivity {

    private static final String selectionText = LOG_SUCCESS_PREFIX + "Please select below option to continue!\n" +
                "1 - List all book in library\n" +
                "2 - Add new book\n" +
                "3 - Delete an existing book from library\n" +
                "4 - Quit!\n" +
                "Enter input!";

    public static void main(String[] args) {
        if (establishConnection()) {
            System.out.println(LOG_SUCCESS_PREFIX + "Database connecntion is successful!");

            System.out.println(LOG_SUCCESS_PREFIX + "Welcome to JavaLibrary Project!");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println(selectionText);
                String i = scanner.nextLine();
                if (i.equals("4")) {
                    System.out.println("Exited from the program!");
                    break;
                }
                System.out.println(i + " selected");
                switch (i) {
                    case "1":
                        System.out.println("ListAllBooks method is running");
                        ListAllBooks();
                        break;
                    case "2":
                        addNewBook();
                        break;
                    case "3":
                        deleteABook();
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
            }
        } else {
            System.out.println(LOG_FAILURE_PREFIX + "Cannot connect to database!");
        }
    }

    private static void deleteABook() {
        System.out.println("deletedBook method is running");

        Scanner scannerForID = new Scanner(System.in);
        System.out.println("Please enter id number: ");
        int id;
        try {
            id = scannerForID.nextInt();
            System.out.println("ID : " + id);
            RemoveBookFromDatabse(id);
            ListAllBooks();
        } catch (InputMismatchException e) {
            System.out.println(LOG_FAILURE_PREFIX + "Invalid number! ID should be a number!");
        }
    }

    private static void addNewBook() {
        System.out.println("addNewBook method is running");

        Scanner scannerForName = new Scanner(System.in);
        System.out.println("Please enter book name: ");
        String bookName = scannerForName.nextLine();

        Scanner scannerForAuthor = new Scanner(System.in);
        System.out.println("Please enter author name: ");
        String author = scannerForAuthor.nextLine();

        System.out.println("Book name : " + bookName);
        System.out.println("Author    : " + author);
        AddNewBook2Database(bookName, author);
        ListAllBooks();
    }
}
