package dataModel;

import models.Book;
import models.Employee;
import repository.BookRepository;

import java.util.List;

public class ProfileDataModel {
    private Employee employee;
    private List<Book> presentBooks;
    private List<Book> pastBooks;

    public ProfileDataModel(Employee employee, BookRepository bookRepository) {
        this.employee = employee;
        this.presentBooks = employee.getPresentBooks()
                .stream()
                .map(bookRepository::findById)
                .toList();
        this.pastBooks = employee.getPastBooks()
                .stream()
                .map(bookRepository::findById)
                .toList();
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<Book> getPresentBooks() {
        return presentBooks;
    }

    public List<Book> getPastBooks() {
        return pastBooks;
    }
}
