package models;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int idenNum;
    private String firstName;
    private String middleName;
    private String lastName;
    List<Book> presentBooks = new ArrayList<>();
    List<Book> pastBooks = new ArrayList<>();

    public Employee(int idenNum, String firstName, String middleName, String lastName){
        this.idenNum = idenNum;
        this.firstName =firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public void addListBook(Book book, List<Book> books) {
        books.add(book);
    }

    public void removePresentBook(Book book, List<Book> books) {
        books.remove(book);
    }

    public int getIdenNum() {
        return idenNum;
    }

    public void setIdenNum(int idenNum) {
        this.idenNum = idenNum;
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
