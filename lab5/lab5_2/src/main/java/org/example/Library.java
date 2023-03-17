package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    // find the book by author and title

    public Book findBook(String title, String author){
        for (Book book: books){
            if(book.getName().equals(title) && book.getAuthor().equals(author)){
                return book;
            }
        }
        System.out.print("The book does not exist");
        return null;
    }

    public void checkout(Book book){
        book.checkout();
    }

    public void checkin(Book book){
        book.checkin();
    }

    // check if a book is available or not

    public boolean isBookAvailable(String title,String author){
        Book book = findBook(title, author);
        return book != null && book.isState();
    }
}
