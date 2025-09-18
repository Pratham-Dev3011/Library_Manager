//package org.example;
//
//public class Member {
//    private int id;
//    private String name;
//    private String email;
//
//    // Default constructor
//    public Member() {
//        this.id = 0;
//        this.name = "Unknown";
//        this.email = "unavailable";
//    }
//
//    // Parameterized constructor
//    public Member(int id, String name, String email) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//    }
//
//    // Getters
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    // Setters
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//}
package org.example;

public class Member extends Person implements Reportable {
    private String email;
    private int borrowedCount;

    public Member(int id, String name, String email) {
        super(id, name);
        this.email = email;
        this.borrowedCount = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void incrementBorrowed() {
        borrowedCount++;
    }

    public void decrementBorrowed() {
        if (borrowedCount > 0) borrowedCount--;
    }

    @Override
    public String role() {
        return "MEMBER";
    }

    @Override
    public String report() {
        return String.format("Member %s (ID: %d, Email: %s) has borrowed %d books.",
                name, id, email, borrowedCount);
    }
}
