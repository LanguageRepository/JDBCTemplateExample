package com.example.dao;

import com.example.model.Employee;

import java.sql.Connection;
import java.sql.SQLException;

public interface EmployeeDao {

    void insert(Employee employee);
    Employee findById(int id) throws SQLException;

}
