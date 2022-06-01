package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employeeDao.getEmployeeById(id);
    }

    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    public void updateEmployee(Employee employee, Long id) {
        employeeDao.updateEmployee(employee, id);
    }

    public void deleteEmployee(Long id) {
        employeeDao.deleteEmployee(id);
    }
}
