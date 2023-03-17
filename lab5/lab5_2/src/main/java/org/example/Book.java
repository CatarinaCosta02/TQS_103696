package org.example;

public class Book {

    private String name;
    private boolean state;
    private String author;

    public Book(String name, boolean state, String author) {
        this.name = name;
        this.state = state;
        this.author = author;
    }

    public String getName() {
        return name;
    }


    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isState(){
        return state;
    }

    public void checkout() {
        state = false;
    }

    public void checkin() {
        state = true;
    }

}
