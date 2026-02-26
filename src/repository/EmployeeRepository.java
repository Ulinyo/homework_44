package repository;

import com.sun.net.httpserver.HttpExchange;
import models.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {
    private Map<String, Employee> employers = new HashMap<>();

    public EmployeeRepository() {
        employers.put("ulukman@are", new Employee( "ulukman@are", "asdfjs"));
    }

    public boolean signUp(String password, String email) {
        if(!checkerEmployers(email)) {
            employers.put(email, new Employee(email, password));
            return true;
        }
        return false;
    }

    private boolean checkerEmployers(String email) {
        return employers.containsKey(email);
    }

    public void addEmployers(String email, Employee e) {
        employers.put(email, e);
    }

    public Map<String, Employee> getEmployers() {
        return employers;
    }
}
