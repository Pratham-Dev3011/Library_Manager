package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book[] Books = new Book[10];
        Member[] Members = new Member[10];

        int bCount = 0;
        int mCount = 0;


        while (true) {

            System.out.println("---Welcome to Library Manager---");
            System.out.println("1.Add Book");
            System.out.println("2.Add Member");
            System.out.println("3.View Books");
            System.out.println("4.View Member");
            System.out.println("5.Search Book by Title");
            System.out.println("6.Borrow Book");
            System.out.println("7.Return Book");
            System.out.println("8.Exit");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            System.out.println(" ");
            switch (choice) {
                case 1:
                    if (bCount > 10 || bCount < 0) {
                        System.out.println("Book limit reached. Cannot add more books.\n");
                        break;
                    } else {
                        System.out.print("Enter Book ID: ");
                        int bookId = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.print("Enter Book Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Book Genre (FICTION, NONFICTION, SCIENCE, HISTORY): ");
                        String genreInput = sc.nextLine().toUpperCase();
                        genre bookGenre;
                        try {
                            bookGenre = genre.valueOf(genreInput);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid genre. Setting to SCIENCE by default.");
                            bookGenre = genre.SCIENCE;
                        }
                        Books[bCount] = new Book(bookId, title, bookGenre);
                        bCount++;
                        System.out.println("Book added successfully!\n");
                        break;
                    }
                case 2:
                    if (mCount > 10 || mCount < 0) {
                        System.out.println("Member limit reached. Cannot add more members.\n");
                        break;
                    } else {
                        System.out.print("Enter Member ID: ");
                        int memberId = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.print("Enter Member Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Member Email: ");
                        String email = sc.nextLine();
                        if(email == null || !email.contains("@")){
                            System.out.println("Invalid Email\n");
                            break;
                        }
                        else {
                            Members[mCount] = new Member(memberId, name, email);
                            mCount++;
                            System.out.println("Member added successfully!\n");
                            break;
                        }
                    }
                case 3:
                    if (bCount == 0) {
                        System.out.println("No books available.\n");
                    } else {
                        System.out.println("---List of Books---");
                        for (Book book : Books) {
                            System.out.println(book);
                        }
                        System.out.println();
                    }
                    break;
                case 4:
                    if (mCount == 0) {
                        System.out.println("No members available.\n");
                    } else {
                        System.out.println("---List of Members---");
                        for (Member member : Members) {
                            System.out.println(String.format("Member{id=%d, name='%s', email='%s'}",
                                    member.getId(), member.getName(), member.getEmail()));
                        }
                        System.out.println();
                    }
                    break;
                case 5:
                    if (bCount == 0) {
                        System.out.println("No books available to search.\n");
                    } else {
                        System.out.print("Enter Book Title to search: ");
                        sc.nextLine(); // Consume newline
                        String searchTitle = sc.nextLine();
                        boolean found = false;
                        for (int i = 0; i < bCount; i++) {
                            if (Books[i].getTitle().equalsIgnoreCase(searchTitle)) {
                                System.out.println("Book found: " + Books[i] + "\n");
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Book not found.\n");
                        }
                    }
                    break;
                case 6: // Borrow Book
                    if (bCount == 0) {
                        System.out.println("No books available to borrow.\n");
                    } else {
                        System.out.print("Enter Book ID to borrow: ");
                        int borrowId = sc.nextInt();
                        boolean found = false;
                        for (int i = 0; i < bCount; i++) {
                            if (Books[i].getBookId() == borrowId) {
                                if (!Books[i].isBorrowed()) {
                                    Books[i].setBorrowed(true);
                                    System.out.println("Book borrowed successfully!\n");
                                } else {
                                    System.out.println("Book is already borrowed.\n");
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Book not found.\n");
                        }
                    }
                    break;
                case 7: // Return Book
                    if (bCount == 0) {
                        System.out.println("No books available to return.\n");
                    } else {
                        System.out.print("Enter Book ID to return: ");
                        int returnId = sc.nextInt();
                        boolean found = false;
                        for (int i = 0; i < bCount; i++) {
                            if (Books[i].getBookId() == returnId) {
                                if (Books[i].isBorrowed()) {
                                    Books[i].setBorrowed(false);
                                    System.out.println("Book returned successfully!\n");
                                } else {
                                    System.out.println("Book was not borrowed.\n");
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Book not found.\n");
                        }
                    }
                    break;





                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
}