package com.example.dao.usejdbctemplate;

import com.example.model.Employee;

import java.util.List;

public interface JDBCEmployeeDAO {

    void insert(Employee employee);
    Employee findById(Integer id);
    List<Employee> selectAllRow();
    String findNameById(Integer id);
    void insertBatch1(final List<Employee> employees);
    void insertBatch2(final String sql);
    void delete(int id);
    void update(Employee employee);

}
