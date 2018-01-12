package ercanduman.library;

import java.util.Scanner;

public class MainActivity {


    public static void main(String[] args) {
        String selectionText = GlobalConfigs.LOG_SUCCESS_PREFIX + "Please select below option to continue!\n" +
                    "1 - List all book in library\n" +
                    "2 - Add new book\n" +
                    "3 - Delete an existing book from library\n" +
                    "4 - Quit!\n" +
                    "Enter input!";

        if (DatabaseHandler.establishConnection()) {
            System.out.println(GlobalConfigs.LOG_SUCCESS_PREFIX + "Database connecntion is successful!");


            System.out.println(GlobalConfigs.LOG_SUCCESS_PREFIX + "Welcome to JavaLibrary Project!");
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
//                    DatabaseHandler.ListAllBooks();
                        break;
                    case "2":
                        AddNewBook();
                        break;
                    case "3":
//                    DatabaseHandler.RemoveBook(29);
//                    DatabaseHandler.ListAllBooks();
                        DeletedBook();
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }

            }
        } else {
            System.out.println(GlobalConfigs.LOG_FAILURE_PREFIX + "Cannot connect to database!");
        }
    }

    private static void DeletedBook() {
        System.out.println("DeletedBook method is running");
    }

    private static void AddNewBook() {
        System.out.println("AddNewBook method is running");
        Scanner scannerForName = new Scanner(System.in);
        System.out.println("Please enter book name:");
        String bookName = scannerForName.nextLine();

        Scanner scannerForAuthor = new Scanner(System.in);
        System.out.println("Please enter author name:");
        String author = scannerForAuthor.nextLine();

        System.out.println("Name: " + bookName);
        System.out.println("Author: " + author);
//        DatabaseHandler.AddNewBook(bookName, author);
//        DatabaseHandler.ListAllBooks();
    }
}
