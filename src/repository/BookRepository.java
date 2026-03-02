package repository;

import models.Book;
import utils.FileUtilBooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private List<Book> books;

    public BookRepository() {
        books = FileUtilBooks.readFile();
    }

    public void saveToFile() {
        FileUtilBooks.writeFile(books);
    }

    public Book findById(int id) {
        for(Book b : books) {
            if(b.getIdenNum() == id) {
                return b;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
