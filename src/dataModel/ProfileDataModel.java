package dataModel;

import models.Employee;

public class ProfileDataModel {
    private String name;
    private String email;

    public ProfileDataModel() {
        this.name = "Некий пользователь";
        this.email = "guest@mail.com";
    }

    public ProfileDataModel(Employee employee) {
        this.name = employee.getFirstName();
        this.email = employee.getEmail();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
}
