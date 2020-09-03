package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.AddressRepository;
import com.paypal.bfs.test.employeeserv.api.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.model.AddressEntity;
import com.paypal.bfs.test.employeeserv.model.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Employee employeeGetById(String id) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(Long.parseLong(id));
        EmployeeEntity employeeEntity = employeeEntityOptional.get();

        ModelMapper mapper= new ModelMapper();
        Employee employee=  mapper.map(employeeEntity,Employee.class);


        return employee;
    }

    @Override
    public void createEmployee(Employee emp) {

        employeeRepository.save(customEntityMapper(emp));

    }

    @Override
    public List<Employee> findAllEmployee() {
        List<Employee> lsEmployee = new ArrayList<>();
        List<EmployeeEntity> optionalList=    employeeRepository.findAll();
        ModelMapper mapper= new ModelMapper();
        lsEmployee= optionalList
                .stream()
                .map(emp -> mapper.map(emp, Employee.class))
                .collect(Collectors.toList());

        return lsEmployee;

    }

    public EmployeeEntity customEntityMapper(Employee emp)
    {
        EmployeeEntity employee = new EmployeeEntity();
        AddressEntity address = new AddressEntity();

        address.setLine1(emp.getAddress().getLine1());
        address.setLine2(emp.getAddress().getLine2());
        address.setCity(emp.getAddress().getCity());
        address.setCountry(emp.getAddress().getCountry());
        address.setZipCode(emp.getAddress().getZipCode());

        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        employee.setAddress(address);
        java.sql.Date sDate = new java.sql.Date(emp.getDateOfBirth().getTime());
        emp.setDateOfBirth(sDate);
        return employee;
    }


}
