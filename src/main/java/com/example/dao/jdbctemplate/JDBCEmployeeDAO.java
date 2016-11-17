package com.example.dao.jdbctemplate;

import com.example.model.Employee;

import java.util.List;

public interface JDBCEmployeeDAO {

    Employee insert(Employee employee);
    Employee get(int id);
    void update(Employee employee);
    void delete(int id);
    List<Employee> getAll();


}
