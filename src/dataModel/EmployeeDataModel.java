package dataModel;

import models.Book;
import models.Employee;
import repository.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDataModel {
    private Employee employee;
    private List<Book> presentBooks;
    private List<Book> pastBooks;

    public EmployeeDataModel(Employee employee, BookRepository bookRepository) {
        this.employee = employee;
        presentBooks = employee.getPresentBooks()
                .stream()
                .map(bookRepository::findById)
                .toList();;
        pastBooks = employee.getPastBooks()
                .stream()
                .map(bookRepository::findById)
                .toList();
    }

    public List<Book> getPresentBooks() {
        return presentBooks;
    }

    public List<Book> getPastBooks() {
        return pastBooks;
    }

    public Employee getEmployee() {
        return employee;
    }
}
