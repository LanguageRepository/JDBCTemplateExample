package com.example.dao;

import com.example.model.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {

    private DataSource dataSource;
    private Connection connection;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Employee employee) {
        String sql = "INSERT INTO employee " + "(NAME, AGE) VALUES (?, ?)";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public Employee findById(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "SELECT * FROM employee WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        Employee employee = null;
        if(rs.next()) {
            employee = new Employee(
                    rs.getInt("ID"),
                    rs.getString("NAME"),
                    rs.getInt("AGE")
            );
        }
        connection.close();
        preparedStatement.close();
        rs.close();
        return employee;
    }


}
