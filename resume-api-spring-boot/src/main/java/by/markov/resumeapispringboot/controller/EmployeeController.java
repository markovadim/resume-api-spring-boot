package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.entity.Employee;
import by.markov.resumeapispringboot.exceptions.EmployeeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.EmployeeNotFoundException;
import by.markov.resumeapispringboot.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    /**
     * Show all employees from database
     *
     * @param pageable - pagination
     * @param model    - model list for template
     * @return home page with 20 employees on page
     */
    @GetMapping
    public String findAll(@PageableDefault(sort = {"name"}) Pageable pageable, Model model){
        Page<Employee> employeePage = employeeService.findAll(pageable);
        model.addAttribute("employeePage", employeePage);
        model.addAttribute("currentPage", employeePage.getNumber());
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("totalElements", employeePage.getTotalElements());
        model.addAttribute("content", employeePage.getContent());
        return "index";
    }

    /**
     * Representing html form for new employee from templates package
     *
     * @param model - employee for template
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
     * @param employee      - main object with fields (user, location, experience, contacts)
     * @param bindingResult - object of incorrect input (validation of input data)
     * @return home page
     * @see Employee
     */
    @PostMapping
    public String createEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult) throws EmployeeAlreadyExistException {
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

    /**
     * @param keyword - input word for search employees
     * @param model   - model list for template
     * @return - template with search result
     */
    @GetMapping("/search")
    public String findEmployeesByKeyword(@PageableDefault(sort = {"name"}) Pageable pageable,
                                         @RequestParam(required = false) String keyword,
                                         Model model) {
        List<Employee> people = employeeService.findEmployeeByKeyword(keyword);
        model.addAttribute("people", people);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalElements", people.size());
        model.addAttribute("totalPages", (people.size()/10)+1);
        return "search_result";
    }
}
