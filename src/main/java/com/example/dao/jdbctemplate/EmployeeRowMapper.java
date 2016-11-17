package com.example.dao.jdbctemplate;

import com.example.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper{

    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("ID"));
        employee.setName(resultSet.getString("NAME"));
        employee.setAge(resultSet.getInt("AGE"));
        return employee;
    }

}
