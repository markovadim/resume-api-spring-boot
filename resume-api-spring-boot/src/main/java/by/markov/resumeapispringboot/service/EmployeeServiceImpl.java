package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Employee;
import by.markov.resumeapispringboot.exceptions.EmployeeAlreadyExistException;
import by.markov.resumeapispringboot.repository.EmployeeRepository;
import by.markov.resumeapispringboot.exceptions.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void addEmployee(Employee employee) throws EmployeeAlreadyExistException {
        if (employeeRepository.findEmployeeByEmail(employee.getEmail()) != null) {
            throw new EmployeeAlreadyExistException();
        }
        employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Integer id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        employeeRepository.delete(employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new));
    }

    public void updateEmployee(Integer id, Employee newEmployee) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        employee.setName(newEmployee.getName());
        employee.setAge(newEmployee.getAge());
        employee.setLocation(newEmployee.getLocation());
        employee.setEmail(newEmployee.getEmail());
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeeByNameContainingOrAgeContainingOrLocationContainingOrEmailContaining(String keyword) {
        return employeeRepository.findAll()
                .stream()
                .filter(e -> e.toString().contains(keyword))
                .collect(Collectors.toList());
    }
}
