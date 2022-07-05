package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.entity.Employee;
import by.markov.resumeapispringboot.exceptions.EmployeeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.EmployeeNotFoundException;
import by.markov.resumeapispringboot.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Main controller for CRUD resume
 *
 * @author markov_vadim
 */

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    private Employee employee;

    /**
     * Show all employees from database
     *
     * @param pageable - pagination
     * @return home page with 20 employees on page
     */
    @GetMapping
    public String showAllEmployees(@PageableDefault(sort = {"name"}) Pageable pageable, Model model) throws EmployeeNotFoundException {
        List<Employee> employeePage = employeeService.findAll();
        model.addAttribute("employeePage", employeePage);
        return "index";
    }

    /**
     * Representing html form for new employee from templates package
     *
     * @param model - employee
     * @return html form for new resume
     */
    @GetMapping("/new_employee")
    public String getCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new_employee_form";
    }

    /**
     * Saving new employee
     *
     * @param employee - main object with fields (user, location, experience, contacts)
     * @return home page
     * @see Employee
     */
    @PostMapping
    public String createEmployee(@ModelAttribute("resume") @Valid Employee employee, BindingResult bindingResult) throws EmployeeAlreadyExistException {

        if (bindingResult.hasErrors()) {
            return "new_employee_form";
        }
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    /**
     * Remove employee by id
     *
     * @param id - id in database
     * @return home page
     * @throws EmployeeNotFoundException (if id not found)
     */
    @GetMapping("/remove/{id}")
    public String removeEmployeeById(@PathVariable Integer id) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    /**
     * Getting html form for update employee by id
     *
     * @param id    - id in database
     * @param model - model of new employee
     * @return home page
     * @throws EmployeeNotFoundException (if id not found)
     * @see EmployeeNotFoundException
     */
    @GetMapping("/{id}/edit")
    public String getUpdateForm(@PathVariable Integer id, Model model) throws EmployeeNotFoundException {
        model.addAttribute("currentEmployee", employeeService.findEmployeeById(id));
        return "update_employee_form";
    }


    /*
        This controller part for search resumes by id, username, location, experience and contacts.
        These methods for REST API part from master branch.
     */

    /**
     * Search employee by id
     *
     * @param id - id in database
     * @return resume (if id was found)
     * @throws EmployeeNotFoundException (if id not found)
     * @see EmployeeNotFoundException
     */
    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable Integer id) {
        try {
            employee = employeeService.findEmployeeById(id);
        } catch (EmployeeNotFoundException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return employee;
    }

    /**
     * Search employee with name, age, location and email.
     *
     * @param name     - username for search
     * @param age      - age of employee
     * @param location - location of employee
     * @param email    - contact email
     * @return - employee list
     */
    @GetMapping("/")
    public Page<Employee> findResumeByFieldOrField(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Integer age,
                                                   @RequestParam(required = false) String location,
                                                   @RequestParam(required = false) String email,
                                                   @PageableDefault(size = 4, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Employee> employeeList = null;
        try {
            employeeList = employeeService.findEmployeeByNameContainsOrAgeContainsOrLocationContainsOrEmailContains(name, age, location, email, pageable);
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return employeeList;
    }
}
