package models;

import repository.EmployeeRepository;

public class Book {
    private int idenNum;
    private String name;
    private String author;
    private String employee = "";
    private String description;
    private String imgUrl = "images/";

    public Book(int idenNum, String name, String author) {
        this.idenNum = idenNum;
        this.name = name;
        this.author = author;
    }

    public void returnBook(Employee em) {
        employee = "";
        em.addListBook(idenNum, em.getPastBooks());
        em.removeListBook(idenNum, em.getPresentBooks());
    }

    public void takeBook(Employee employee) {
        this.employee = employee.getEmail();
        employee.addListBook(idenNum, employee.getPresentBooks());
    }

    public boolean isAvailable() {
        return employee == null || employee.isBlank();
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
