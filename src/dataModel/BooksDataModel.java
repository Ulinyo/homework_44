package dataModel;

import models.Book;
import models.Employee;

import java.util.ArrayList;
import java.util.List;

public class BooksDataModel {
    private List<Book> books = new ArrayList<>();
    private Employee currentEmployee;

    public BooksDataModel(List<Book> books, Employee employee) {
        this.books = List.copyOf(books);
        currentEmployee = employee;
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public List<Book> getBooks() {
        return books;
    }
}
