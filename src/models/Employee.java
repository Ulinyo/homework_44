package models;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    List<Integer> presentBooks = new ArrayList<>();
    List<Integer> pastBooks = new ArrayList<>();

    public Employee(String email, String password){
        this.email = email;
        this.password = password;
        firstName = "userFirstName";
        middleName = "userMiddleName";
        lastName = "userLastName";
    }

    public boolean limitPresentBooks() {
        return presentBooks.size() < 2;
    }

    public void addListBook(int book, List<Integer> books) {
        books.add(book);
    }

    public void removeListBook(int book, List<Integer> books) {
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

    public List<Integer> getPresentBooks() {
        return presentBooks;
    }

    public void setPresentBooks(List<Integer> presentBooks) {
        this.presentBooks = presentBooks;
    }

    public List<Integer> getPastBooks() {
        return pastBooks;
    }

    public void setPastBooks(List<Integer> pastBooks) {
        this.pastBooks = pastBooks;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
