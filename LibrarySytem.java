import java.util.*;

class Book {
    int id;
    String title;
    boolean isIssued;

    Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    void display() {
        System.out.println("ID: " + id + ", Title: " + title + ", Issued: " + isIssued);
    }
}

public class LibrarySystem {
    static HashMap<Integer, Book> books = new HashMap<>();

    public static void addBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        books.put(id, new Book(id, title));
        System.out.println("Book added successfully!");
    }

    public static void issueBook(Scanner sc) {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        Book b = books.get(id);
        if (b == null) {
            System.out.println("Book not found!");
        } else if (b.isIssued) {
            System.out.println("Already issued!");
        } else {
            b.isIssued = true;
            System.out.println("Book issued!");
        }
    }

    public static void returnBook(Scanner sc) {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        Book b = books.get(id);
        if (b == null) {
            System.out.println("Book not found!");
        } else {
            b.isIssued = false;
            System.out.println("Book returned!");
        }
    }

    public static void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book b : books.values()) {
            b.display();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: addBook(sc); break;
                case 2: issueBook(sc); break;
                case 3: returnBook(sc); break;
                case 4: displayBooks(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
