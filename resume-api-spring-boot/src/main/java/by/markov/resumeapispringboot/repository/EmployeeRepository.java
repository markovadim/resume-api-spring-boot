package by.markov.resumeapispringboot.repository;

import by.markov.resumeapispringboot.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO level for work with main object
 *
 * @author markov_vadim
 */

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findEmployeeByEmail(String email);

    Page<Employee> findAll(Pageable pageable);
}
