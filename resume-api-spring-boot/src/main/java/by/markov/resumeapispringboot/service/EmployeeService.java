package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Employee;
import by.markov.resumeapispringboot.exceptions.EmployeeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.EmployeeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Application service for CRUD operations
 *
 * @author markov_vadim
 * @see EmployeeServiceImpl - implemetnation this service
 */

public interface EmployeeService {
    List<Employee> findAll() throws EmployeeNotFoundException;

    Employee addEmployee(Employee employee) throws EmployeeAlreadyExistException;

    Employee findEmployeeById(Integer id) throws EmployeeNotFoundException;

    Employee deleteEmployee(Integer id) throws EmployeeNotFoundException;

    Employee updateEmployee(Integer id, Employee newEmployee) throws EmployeeNotFoundException;

    Page<Employee> findEmployeeByNameContainsOrAgeContainsOrLocationContainsOrEmailContains(String name, Integer age, String location, String email, Pageable pageable);
}
