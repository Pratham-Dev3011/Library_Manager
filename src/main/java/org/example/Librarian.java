// src/main/java/org/example/Librarian.java
package org.example;

public class Librarian extends Person {
    public Librarian(int id, String name) {
        super(id, name);
    }

    @Override
    public String role() {
        return "LIBRARIAN";
    }
}
