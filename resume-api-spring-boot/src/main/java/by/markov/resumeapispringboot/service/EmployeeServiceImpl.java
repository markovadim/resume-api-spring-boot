package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Employee;
import by.markov.resumeapispringboot.exceptions.EmployeeAlreadyExistException;
import by.markov.resumeapispringboot.repository.EmployeeRepository;
import by.markov.resumeapispringboot.exceptions.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation service interface
 *
 * @author markov_vadim
 */

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
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
        Employee currentEmployee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        currentEmployee.setName(newEmployee.getName());
        currentEmployee.setAge(newEmployee.getAge());
        currentEmployee.setLocation(newEmployee.getLocation());
        currentEmployee.setEmail(newEmployee.getEmail());
        return employeeRepository.save(currentEmployee);
    }

    public List<Employee> findEmployeeByKeyword(String keyword) {
        List<Employee> people = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> {
            if (e.toString().contains(keyword)){
                people.add(e);
            };
        });
        return people;
    }
}
