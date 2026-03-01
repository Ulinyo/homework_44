package repository;

import models.Employee;
import utils.FileUtilEmployers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepository {

    private Map<String, Employee> employers = new HashMap<>();

    public EmployeeRepository() {
        loadFromFile();
    }

    private void loadFromFile() {
        List<Employee> list = FileUtilEmployers.readFile();
        for (Employee e : list) {
            employers.put(e.getEmail(), e);
        }
    }

    private void saveToFile() {
        FileUtilEmployers.writeFile(List.copyOf(employers.values()));
    }

    public boolean signUp(String password, String email, String name) {
        if (!employers.containsKey(email)) {
            Employee em = new Employee(email, password);
            em.setFirstName(name);
            employers.put(email, em);
            saveToFile();
            return true;
        }
        return false;
    }

    public Map<String, Employee> getEmployers() {
        return employers;
    }

    public Employee findByEmailAndPassword(String email, String password) {
        Employee employee = employers.get(email);
        if (employee != null && employee.getPassword().equals(password)) return employee;
        return null;
    }
}