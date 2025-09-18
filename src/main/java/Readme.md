





































































import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* Single-file Library Manager demo covering:
* - Basic: classes, constructors, enums, arrays, search (case-insensitive)
* - Intermediate: borrow/return with exceptions, parsing with custom ParseException
*
* Compile: javac LibraryApp.java
* Run:     java LibraryApp
  */
  public class LibraryApp {
  public static void main(String[] args) {
  LibraryManager mgr = new LibraryManager();

       // --- Basic: create sample books & members (using arrays inside manager)
       mgr.addBook(new Book(1, "Dune", "Frank Herbert", Genre.SCIENCE, true));
       mgr.addBook(new Book(2, "History of India", "K. Sharma", Genre.HISTORY, true));
       mgr.addBook(new Book(3, "Clean Code", "Robert C. Martin", Genre.NONFICTION, true));

       mgr.addMember(new Member(101, "Asha", "asha@example.com"));
       mgr.addMember(new Member(102, "Rohit", "rohit@example.com"));

       System.out.println("=== Books in library ===");
       for (Book b : mgr.getAllBooks()) System.out.println(b);

       System.out.println("\n=== Search by title 'du' (case-insensitive contains) ===");
       List<Book> found = mgr.searchByTitle("du");
       for (Book b : found) System.out.println(b);

       // --- Intermediate: borrow/return with exceptions (demonstrate success + failures)
       System.out.println("\n=== Borrow/Return demo ===");
       try {
           mgr.borrowBook(101, 1); // success
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }

       try {
           mgr.borrowBook(101, 1); // should throw IllegalStateException ("Book already borrowed")
       } catch (Exception e) {
           System.out.println("Expected failure: " + e.getClass().getSimpleName() + ": " + e.getMessage());
       }

       try {
           mgr.returnBook(101, 1); // success
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }

       try {
           mgr.returnBook(101, 1); // should throw IllegalStateException ("Book is not borrowed")
       } catch (Exception e) {
           System.out.println("Expected failure: " + e.getClass().getSimpleName() + ": " + e.getMessage());
       }

       // --- Parsing / Validation demo
       System.out.println("\n=== Parsing demo (valid and invalid) ===");
       try {
           Member parsed = LibraryManager.parseMember("103|Meera|meera@example.com");
           System.out.println("Parsed member: " + parsed);
       } catch (ParseException pe) {
           System.out.println("ParseException: " + pe.getMessage());
       }

       try {
           // invalid email -> should throw ParseException
           Member parsedBad = LibraryManager.parseMember("104|BadEmail|bademail.example.com");
           System.out.println("Parsed member: " + parsedBad);
       } catch (ParseException pe) {
           System.out.println("Expected parse failure: " + pe.getMessage());
       }

       try {
           // invalid genre -> should throw ParseException
           Book parsedBookBad = LibraryManager.parseBook("10|Mystery Book|X.Y.|MYSTERY");
           System.out.println("Parsed book: " + parsedBookBad);
       } catch (ParseException pe) {
           System.out.println("Expected parse failure: " + pe.getMessage());
       }

       System.out.println("\n=== End of demo ===");
  }
  }

/* ------------------------
Supporting classes/types
------------------------ */

enum Genre { FICTION, NONFICTION, SCIENCE, HISTORY }

class Book {
private int id;
private String title;
private String author;
private Genre genre;
private boolean available;

    public Book() {}

    public Book(int id, String title, String author, Genre genre, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
    }

    // getters / setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Genre getGenre() { return genre; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean v) { this.available = v; }

    @Override
    public String toString() {
        return String.format("Book{id=%d, title='%s', author='%s', genre=%s, available=%b}",
                id, title, author, genre, available);
    }

    // equals/hashCode by id only (per spec)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book b = (Book) o;
        return id == b.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Member {
private int id;
private String name;
private String email;

    public Member() {}

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Member{id=%d, name='%s', email='%s'}", id, name, email);
    }
}

class ParseException extends Exception {
public ParseException(String message) { super(message); }
}

/* LibraryManager uses arrays internally (max 10) and methods asked in the assignment */
class LibraryManager {
private final Book[] books = new Book[10];
private final Member[] members = new Member[10];
private int bookCount = 0;
private int memberCount = 0;

    // Basic: add book/member (array-backed)
    public void addBook(Book b) {
        if (bookCount >= books.length) throw new IllegalStateException("Book storage full (max 10)");
        books[bookCount++] = b;
    }

    public void addMember(Member m) {
        if (memberCount >= members.length) throw new IllegalStateException("Member storage full (max 10)");
        members[memberCount++] = m;
    }

    // Helper to return snapshot list of all books (for demo)
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < bookCount; i++) list.add(books[i]);
        return list;
    }

    // Basic: searchByTitle (case-insensitive contains)
    public List<Book> searchByTitle(String query) {
        List<Book> out = new ArrayList<>();
        if (query == null) return out;
        String q = query.toLowerCase();
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().toLowerCase().contains(q)) out.add(books[i]);
        }
        return out;
    }

    // Intermediate: borrowBook and returnBook with specified exceptions
    public void borrowBook(int memberId, int bookId) {
        Member m = findMemberById(memberId);
        if (m == null) throw new IllegalArgumentException("Member with id " + memberId + " not found");
        Book b = findBookById(bookId);
        if (b == null) throw new IllegalArgumentException("Book with id " + bookId + " not found");
        if (!b.isAvailable()) throw new IllegalStateException("Book already borrowed");
        b.setAvailable(false);
        System.out.println("Book borrowed: " + b.getTitle() + " by member " + m.getName());
    }

    public void returnBook(int memberId, int bookId) {
        Member m = findMemberById(memberId);
        if (m == null) throw new IllegalArgumentException("Member with id " + memberId + " not found");
        Book b = findBookById(bookId);
        if (b == null) throw new IllegalArgumentException("Book with id " + bookId + " not found");
        if (b.isAvailable()) throw new IllegalStateException("Book is not borrowed");
        b.setAvailable(true);
        System.out.println("Book returned: " + b.getTitle() + " by member " + m.getName());
    }

    // helper finders
    private Member findMemberById(int id) {
        for (int i = 0; i < memberCount; i++) if (members[i].getId() == id) return members[i];
        return null;
    }

    private Book findBookById(int id) {
        for (int i = 0; i < bookCount; i++) if (books[i].getId() == id) return books[i];
        return null;
    }

    // Intermediate: parsing utilities
    // parseMember: "101|Asha|asha@example.com"
    public static Member parseMember(String line) throws ParseException {
        if (line == null) throw new ParseException("Input is null");
        String[] parts = line.split("\\|");
        if (parts.length != 3) throw new ParseException("Member string must have 3 parts: id|name|email");
        int id;
        try { id = Integer.parseInt(parts[0].trim()); } catch (NumberFormatException nfe) {
            throw new ParseException("Invalid member id: " + parts[0]);
        }
