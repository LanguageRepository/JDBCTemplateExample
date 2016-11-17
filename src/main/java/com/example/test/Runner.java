package com.example.test;

import com.example.dao.EmployeeDao;
import com.example.dao.usejdbctemplate.JDBCEmployeeDAO;
import com.example.model.Employee;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws SQLException {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        JDBCEmployeeDAO jdbcEmployeeDAO = (JDBCEmployeeDAO) context.getBean("jdbcEmployeeDao");
        //Employee employee3 = new Employee("Vasya", 25);
        //jdbcEmployeeDAO.insert(employee3);
        Employee employee4 = jdbcEmployeeDAO.findById(4);
        System.out.println(employee4);
        List<Employee> employees = jdbcEmployeeDAO.selectAllRow();
        employees.forEach(System.out::println);
        String result = jdbcEmployeeDAO.findNameById(6);
        System.out.println(result);
        System.out.println("~~~~~~~~~~~~~~~Batch~~~~~~~~~~~~~~~~");
        Employee employee1 = new Employee("Igoran", 35);
        Employee employee2 = new Employee("Pasha", 29);
        List<Employee> employeeList1 = new ArrayList<>();
        employeeList1.add(employee1);
        employeeList1.add(employee2);
        jdbcEmployeeDAO.insertBatch1(employeeList1);
        System.out.println("Find all employees: ");
        jdbcEmployeeDAO.selectAllRow().forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        jdbcEmployeeDAO.insertBatch2("UPDATE employee SET NAME='Mary'");
        System.out.println("Finded all after name update on Mary");
        jdbcEmployeeDAO.selectAllRow().forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        jdbcEmployeeDAO.update(new Employee(6, "Pavel", 34));
        jdbcEmployeeDAO.selectAllRow().forEach(System.out::println);
        context.close();
    }

}
