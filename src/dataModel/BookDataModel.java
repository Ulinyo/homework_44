package dataModel;

import models.Book;
import models.Employee;

public class BookDataModel {
    private Book book;

    public BookDataModel() {
        this.book = new Book(123, "Harry Potniy", "Dominik Toretto");
        book.takeBook(new Employee( "kapuchino@gmail.com", "jdafjas"));
    }

    public Book getBook() {
        return book;
    }
}
