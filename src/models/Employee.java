package models;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private final String email;
    private final String passwrod;
    List<Book> presentBooks = new ArrayList<>();
    List<Book> pastBooks = new ArrayList<>();

    public Employee(String email, String passwrod){
        this.email = email;
        this.passwrod = passwrod;
        firstName = "userFirstName";
        middleName = "userMiddleName";
        lastName = "userLastName";
    }

    public void addListBook(Book book, List<Book> books) {
        books.add(book);
    }

    public void removePresentBook(Book book, List<Book> books) {
        books.remove(book);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getPresentBooks() {
        return presentBooks;
    }

    public void setPresentBooks(List<Book> presentBooks) {
        this.presentBooks = presentBooks;
    }

    public List<Book> getPastBooks() {
        return pastBooks;
    }

    public void setPastBooks(List<Book> pastBooks) {
        this.pastBooks = pastBooks;
    }
}
