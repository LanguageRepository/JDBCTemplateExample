package com.example.dao.jdbctemplate;

import com.example.model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCEmployeeDAOImpl implements JDBCEmployeeDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Employee insert(Employee employee) {
        String sql = "INSERT INTO employee " + "(ID, NAME, AGE) VALUES(?, ?, ?)";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, new Object[]{employee.getId(), employee.getName(), employee.getAge()});
        System.out.println("Employee: " + employee + " was inserting");
        return employee;
    }

    @Override
    public Employee get(int id) {
        String sql = "SELECT * FROM employee WHERE ID = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Employee employee = (Employee) jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                new BeanPropertyRowMapper(Employee.class));
        return employee;
    }

    @Override
    public List<Employee> getAll() {
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
