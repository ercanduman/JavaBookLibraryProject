package ercanduman.library;

public class MainActivity {

    public static void main(String[] args) {
        if (DatabaseHandler.establishConnection()) {
            DatabaseHandler.ListAllBooks();
        }
    }
}
