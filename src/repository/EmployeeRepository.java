package repository;

import models.Employee;
import utils.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepository {

    private Map<String, Employee> employers = new HashMap<>();

    public EmployeeRepository() {
        loadFromFile();
    }

    private void loadFromFile() {
        List<Employee> list = FileUtil.readFile();
        for (Employee e : list) {
            employers.put(e.getEmail(), e);
        }
    }

    private void saveToFile() {
        FileUtil.writeFile(List.copyOf(employers.values()));
    }

    public boolean signUp(String password, String email) {
        if (!employers.containsKey(email)) {
            System.out.println(password);
            employers.put(email, new Employee(email, password));
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean login(String email, String password) {
        if (!employers.containsKey(email)) {
            return false;
        }

        return employers.get(email).getPassword().equals(password);
    }

    public Map<String, Employee> getEmployers() {
        return employers;
    }
}