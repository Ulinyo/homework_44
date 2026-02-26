package repository;

import com.sun.net.httpserver.HttpExchange;
import models.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {
    private Map<String, Employee> employers = new HashMap<>();

    public boolean signUp(String p, String e) {
        String[] email = e.split("=");
        String[] password = p.split("=");
        if(checkerEmployers(email[1])) {
            employers.put(email[1], new Employee(email[1], password[1]));
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
