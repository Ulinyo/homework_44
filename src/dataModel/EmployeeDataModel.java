package dataModel;

import models.Book;
import models.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDataModel {
    private Employee employee;

    public EmployeeDataModel() {
        employee = new Employee("ashdfkjah@sakdhfsa", "sadfaead");
    }



    public Employee getEmployee() {
        return employee;
    }
}
