package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Employee;
import by.markov.resumeapispringboot.exceptions.EmployeeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.EmployeeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Application service for CRUD operations
 *
 * @author markov_vadim
 * @see EmployeeServiceImpl - implemetnation of this service
 */

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    Employee addEmployee(Employee employee) throws EmployeeAlreadyExistException;

    Employee findEmployeeById(Integer id) throws EmployeeNotFoundException;

    Employee deleteEmployee(Integer id) throws EmployeeNotFoundException;

    Employee updateEmployee(Integer id, Employee newEmployee) throws EmployeeNotFoundException;
}
