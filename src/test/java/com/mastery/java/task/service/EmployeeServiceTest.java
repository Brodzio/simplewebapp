package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeServiceTest {

    private Employee empOne;
    private Employee empTwo;
    private Employee empThree;

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeDao employeeDao;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        empOne = new Employee(1L, "Jan", "Jankowski", 1L, "tragarz");
        empTwo = new Employee(2L, "Michał", "Michalski", 4L, "aktor");
        empThree = new Employee(3L, "Kasia", "Kasialska", 3L, "architekt");
    }

    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void getAllEmployeesTest()
    {
        List<Employee> list = new ArrayList<>();

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeDao.getAllEmployees()).thenReturn(list);

        List<Employee> empList = employeeService.getEmployees();

        assertEquals(3, empList.size());
        verify(employeeDao, times(1)).getAllEmployees();
    }

    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void getEmployeeByIdTest()
    {
        when(employeeDao.getEmployeeById(1L)).thenReturn(Optional.of(empOne));

        Employee emp = employeeDao.getEmployeeById(1L).get();

        assertThat(emp).isNotNull().isEqualTo(empOne);
    }

    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void saveEmployeeTest()
    {
        Employee emp = new Employee(4L, "Stanisław", "Stachowiak", 5L, "inżynier");

        employeeDao.saveEmployee(emp);

        verify(employeeDao, times(1)).saveEmployee(emp);
    }

    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void deleteEmployeeTest()
    {
        willDoNothing().given(employeeDao).deleteEmployee(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeDao, times(1)).deleteEmployee(1L);
    }

    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void updateEmployeeTest(){
        // given - precondition or setup
        willDoNothing().given(employeeDao).updateEmployee(empOne, 1L);
        empOne.setFirstName("Krzysztof");
        empOne.setLastName("Krychowiak");

        employeeService.updateEmployee(empOne, 1L);

        assertThat(empOne.getFirstName()).isEqualTo("Krzysztof");
        assertThat(empOne.getLastName()).isEqualTo("Krychowiak");
    }
}
