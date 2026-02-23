package models;

public class BookDataModel {
    private Book book;

    public BookDataModel() {
        this.book = new Book(123, "Harry Potniy", "Dominik Toretto");
        book.takeBook(new Employee(1, "Danil", " Colbasenko", "Colbasemkovich"));
    }

    public Book getBook() {
        return book;
    }
}
