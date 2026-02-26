package dataModel;

import models.Book;
import models.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDataModel {
    private Employee employee;

    public EmployeeDataModel() {
        employee = new Employee(123, "Denis", "Colbasenko", "Tartarovich");
        employee.addListBook(new Book(12, "Herry Potter", "John Own"), employee.getPresentBooks());
        employee.addListBook(new Book(3, "Lion King", "Brony Dony"), employee.getPresentBooks());
        employee.addListBook(new Book(4, "Cat", "Devon Larrat"), employee.getPastBooks());
        employee.addListBook(new Book(7, "Wild street", "Micky Mouse"), employee.getPastBooks());
    }



    public Employee getEmployee() {
        return employee;
    }
}
