package dataModel;

import models.Book;
import models.Employee;
import repository.BookRepository;

import java.util.List;

public class ProfileDataModel {
    private String name;
    private String email;
    private List<Book> presentBooks;
    private List<Book> pastBooks;

    public ProfileDataModel(Employee employee, BookRepository bookRepository) {
        this.name = employee.getFirstName();
        this.email = employee.getEmail();
        this.presentBooks = employee.getPresentBooks()
                .stream()
                .map(bookRepository::findById)
                .toList();
        this.pastBooks = employee.getPastBooks()
                .stream()
                .map(bookRepository::findById)
                .toList();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }

    public List<Book> getPresentBooks() {
        return presentBooks;
    }

    public List<Book> getPastBooks() {
        return pastBooks;
    }
}
