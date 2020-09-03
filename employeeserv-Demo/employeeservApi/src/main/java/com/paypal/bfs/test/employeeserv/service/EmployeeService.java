package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

import java.util.List;

public interface EmployeeService {

   Employee employeeGetById(String id);

    public void createEmployee(Employee emp);

    public List<Employee> findAllEmployee();


}
