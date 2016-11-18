package com.example.test;

import com.example.dao.jdbctemplate.JDBCEmployeeDAO;
import com.example.model.Employee;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        JDBCEmployeeDAO jdbcEmployeeDAO = (JDBCEmployeeDAO) context.getBean("jdbcEmployeeDao");
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee[]{
                new Employee(1, "Tina", 19),
                new Employee(2, "Vasya", 23),
                new Employee(3, "Oleg", 34),
                new Employee(4, "Tanya", 24),
                new Employee(5, "Sasha", 45),
                new Employee(6, "Alexey", 30)
        }));
        jdbcEmployeeDAO.insert(employees.get(4));
        jdbcEmployeeDAO.get(1);
        jdbcEmployeeDAO.update(employees.get(4));
        jdbcEmployeeDAO.delete(4);
        jdbcEmployeeDAO.getAll().forEach(System.out::println);
    }

}
