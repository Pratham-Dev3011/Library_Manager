// src/main/java/org/example/Person.java
package org.example;

public abstract class Person {
    protected int id;
    protected String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract String role();

    public int getId() { return id; }
    public String getName() { return name; }
}
