package by.markov.resumeapispringboot.repository;

import by.markov.resumeapispringboot.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO level for work with main object
 *
 * @author markov_vadim
 */

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findEmployeeByName(String name);

    Employee findEmployeeByEmail(String email);

    List<Employee> findAll();
}
