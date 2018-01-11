package ercanduman.library;

public class MainActivity {

    public static void main(String[] args) {
        if (DatabaseHandler.establishConnection()) {
            DatabaseHandler.ListAllBooks();
//            DatabaseHandler.AddNewBook("The Magic of Thinking Big", "David J. Schwartz");
            DatabaseHandler.RemoveBook(29);
            DatabaseHandler.ListAllBooks();
        }
    }
}
