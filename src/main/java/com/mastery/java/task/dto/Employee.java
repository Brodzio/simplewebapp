package com.mastery.java.task.dto;

import lombok.Data;

@Data
public class Employee {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;

    public Employee() {}

    public Employee(Long employeeId, String firstName, String lastName, Long departmentId, String jobTitle){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
    }
}
