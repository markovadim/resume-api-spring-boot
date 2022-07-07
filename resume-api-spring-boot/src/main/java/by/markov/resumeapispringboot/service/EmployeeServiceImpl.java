package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Employee;
import by.markov.resumeapispringboot.exceptions.EmployeeAlreadyExistException;
import by.markov.resumeapispringboot.repository.EmployeeRepository;
import by.markov.resumeapispringboot.exceptions.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation service interface
 *
 * @author markov_vadim
 */

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() throws EmployeeNotFoundException {
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) {
            throw new EmployeeNotFoundException();
        }
        return employeeList;
    }

    public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistException {
        if (employeeRepository.findEmployeeByEmail(employee.getEmail()) != null) {
            throw new EmployeeAlreadyExistException();
        }
        employeeRepository.save(employee);
        return employee;
    }

    public Employee findEmployeeById(Integer id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee deleteEmployee(Integer id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        employeeRepository.delete(employee);
        return employee;
    }

    public Employee updateEmployee(Integer id, Employee newEmployee) throws EmployeeNotFoundException {
        Optional<Employee> resumeInDataBase = employeeRepository.findById(id);
        if (resumeInDataBase.isEmpty()) {
            throw new EmployeeNotFoundException();
        } else {
            resumeInDataBase.get().setName(newEmployee.getName());
            resumeInDataBase.get().setAge(newEmployee.getAge());
            resumeInDataBase.get().setLocation(newEmployee.getLocation());
            resumeInDataBase.get().setEmail(newEmployee.getEmail());
            employeeRepository.save(resumeInDataBase.get());
        }
        return resumeInDataBase.get();
    }

    public List<Employee> findEmployeeByNameContainingOrAgeContainingOrLocationContainingOrEmailContaining(String keyword) {
        return employeeRepository.findAll().stream().filter(e -> e.toString().contains(keyword)).collect(Collectors.toList());
    }
}
