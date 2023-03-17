package org.example;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String name;
    private List<Book> borrowedBooks;

    public LibraryMember(String name) {
        this.name = name;
        borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.checkout();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.checkin();
    }

    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }
}
