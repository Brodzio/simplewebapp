package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDao implements Dao<Employee> {

    private final JdbcTemplate jdbcTemplate;
    private static final String getAllQuery = "select * from employee";
    private static final String getSingleQuery="select * from employee where employee_id=?";
    private static final String insertQuery = "insert into employee (first_name, last_name, department_id, job_title) values (?, ?, ?, ?)";
    private static final String updateQuery = "update employee set first_name = ?, last_name = ?, department_id = ?, job_title = ? where employee_id = ?";
    private static final String deleteQuery = "delete from employee where employee_id = ?";

    RowMapper<Employee> employees = ((rs, row) -> {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getLong("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartmentId(rs.getLong("department_id"));
        employee.setJobTitle(rs.getString("job_title"));
        return employee;
    });

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Employee employee = null;
        try {
            employee = jdbcTemplate.queryForObject(getSingleQuery, new Object[]{id}, employees);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query(getAllQuery, employees);
    }

    @Override
    public void saveEmployee(Employee employee) {
        Object[] params = new Object[]{employee.getFirstName(),
                                        employee.getLastName(),
                                        employee.getDepartmentId(),
                                        employee.getJobTitle()};
        jdbcTemplate.update(insertQuery, params);
    }

    @Override
    public void updateEmployee(Employee employee, Long id) {
        jdbcTemplate.update(updateQuery,employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(), id);
    }

    @Override
    public void deleteEmployee(Long id) {
        jdbcTemplate.update(deleteQuery, id);
    }
}
