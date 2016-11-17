package com.example.dao.usejdbctemplate;

import com.example.model.Employee;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCEmployeeDAOImpl implements JDBCEmployeeDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Employee employee) {
        String sql = "INSERT INTO employee " + "(NAME, AGE) VALUES(?, ?)";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, new Object[]{employee.getName(), employee.getAge()});
    }

    public Employee findById(Integer id) {
        String sql = "SELECT * FROM employee WHERE ID = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Employee.class));
        return employee;
    }

    public List<Employee> selectAllRow() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<Employee>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(String.valueOf(row.get("ID"))));
            employee.setName((String) row.get("NAME"));
            employee.setAge(Integer.parseInt(String.valueOf(row.get("AGE"))));
            employees.add(employee);
        }
        return employees;
    }

    public String findNameById(Integer id) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT NAME FROM employee WHERE ID = ?";
        String result = (String) jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
        return result;
    }

    @Override
    public void insertBatch1(List<Employee> employees) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO employee " +
                "(NAME, AGE) VALUES(?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Employee employee = employees.get(i);
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setInt(2, employee.getAge());
            }

            @Override
            public int getBatchSize() {
                return employees.size();
            }
        });
    }

    @Override
    public void insertBatch2(String sql) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.batchUpdate(new String[]{sql});
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE ID = ?";
        int result = jdbcTemplate.update(sql, id);
        if(result != 0) {
            System.out.println("Employee was delete");
        } else {
            System.out.println("Employee wasn't delete");
        }
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employee SET NAME = ?, AGE = ? WHERE ID = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = {employee.getName(), employee.getAge(), employee.getId()};
        int result = jdbcTemplate.update(sql, args);
        if(result != 0) {
            System.out.println("Object with id: " + employee.getId() + " was update");
        } else {
            System.out.println("Objects with id " + employee.getId() + " was not update");
        }
    }

}
