package models;

public class Book {
    private final int idenNum;
    private final String name;
    private final String author;
    private Employee employee;

    public Book(int idenNum, String name, String author) {
        this.idenNum = idenNum;
        this.name = name;
        this.author = author;
    }

    public void takeBook(Employee employee) {
        this.employee = employee;
    }

    public boolean isAvailable() {
        return employee == null;
    }

    public int getIdenNum() {
        return idenNum;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
