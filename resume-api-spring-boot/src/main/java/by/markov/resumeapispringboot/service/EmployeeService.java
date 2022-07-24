package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Employee;
import by.markov.resumeapispringboot.exceptions.EmployeeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.EmployeeNotFoundException;

import java.util.List;

/**
 * Application service for CRUD operations
 *
 * @author markov_vadim
 * @see EmployeeServiceImpl - implemetnation this service
 */

public interface EmployeeService {
    List<Employee> findAll() throws EmployeeNotFoundException;

    void addEmployee(Employee employee) throws EmployeeAlreadyExistException;

    Employee findEmployeeById(Integer id) throws EmployeeNotFoundException;

    void deleteEmployee(Integer id) throws EmployeeNotFoundException;

    void updateEmployee(Integer id, Employee newEmployee) throws EmployeeNotFoundException;
}
