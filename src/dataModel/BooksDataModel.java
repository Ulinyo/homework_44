package dataModel;

import models.Book;
import models.Employee;

import java.util.List;

public class BooksDataModel {
    private List<Book> books;

    public BooksDataModel() {
        books = List.of(new Book(123, "Harry Potter", "Joan Roaring"),
                new Book(132, "Simba", "Noel Shrek"),
                new Book(12, "Elder Ring", "Miyamoto"));
        books.get(1).takeBook(new Employee("asdjfajl@lsjadla", "ashdfakh"));
    }

    public List<Book> getBooks() {
        return books;
    }
}
