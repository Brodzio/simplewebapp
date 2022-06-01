package com.mastery.java.task.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> getEmployeeById(Long id);

    List<T> getAllEmployees();

    void saveEmployee(T t);

    void updateEmployee(T t, Long id);

    void deleteEmployee(Long id);
}
