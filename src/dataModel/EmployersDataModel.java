package dataModel;

import models.Employee;

import java.util.List;

public class EmployersDataModel {
    private List<Employee> employers;

    public EmployersDataModel(List<Employee> employers) {
        this.employers = employers;
    }

    public List<Employee> getEmployers() {
        return employers;
    }
}
