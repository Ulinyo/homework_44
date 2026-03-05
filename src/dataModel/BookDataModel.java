package dataModel;

import models.Book;
import models.Employee;

public class BookDataModel {
    private Book book;

    public BookDataModel(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
