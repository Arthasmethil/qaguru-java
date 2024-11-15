package guru.qa.lesson10.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "department")
public class Department {
    private int id;
    private String name;
    private String manager;
    private String location;
    private List<Employee> employees;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}