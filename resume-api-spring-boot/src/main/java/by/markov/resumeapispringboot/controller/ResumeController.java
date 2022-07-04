package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import by.markov.resumeapispringboot.service.ResumeServiceImpl;
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
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeServiceImpl resumeService;

    private Resume resume;

    /**
     * Show all resumes from database
     *
     * @param pageable - pagination
     * @return home page with 20 employees on page
     */
    @GetMapping
    public String showAllResumes(@PageableDefault(sort = {"user"}) Pageable pageable, Model model) throws ResumeNotFoundException {
        List<Resume> resumePage = resumeService.findAll();
        model.addAttribute("resumePage", resumePage);
        return "index";
    }

    /**
     * Representing html form for resume from templates package
     *
     * @param model - resume
     * @return html form for new resume
     */
    @GetMapping("/new_employee")
    public String getCreateForm(Model model) {
        model.addAttribute("resume", new Resume());
        return "new_employee_form";
    }

    /**
     * Create new resume
     *
     * @param resume - main object with fields (user, location, experience, contacts)
     * @return home page
     * @see Resume
     */
    @PostMapping
    public String createResume(@ModelAttribute("resume") @Valid Resume resume, BindingResult bindingResult) throws ResumeAlreadyExistException {

        if (bindingResult.hasErrors()) {
            return "new_employee_form";
        }
        resumeService.addResume(resume);
        return "redirect:/resumes";
    }

    /**
     * Remove resume by id
     *
     * @param id - id in database
     * @return home page
     * @throws ResumeNotFoundException (if id not found)
     */
    @GetMapping("/remove/{id}")
    public String removeResumeById(@PathVariable Integer id) throws ResumeNotFoundException {
        resumeService.deleteResume(id);
        return "redirect:/resumes";
    }

    /**
     * Getting html form for update resume by id
     *
     * @param id    - id in database
     * @param model - model of new resume
     * @return home page
     * @throws ResumeNotFoundException (if id not found)
     * @see ResumeNotFoundException
     */
    @GetMapping("/{id}/edit")
    public String getUpdateForm(@PathVariable Integer id, Model model) throws ResumeNotFoundException {
        model.addAttribute("currentResume", resumeService.findResumeById(id));
        return "update_employee_form";
    }


    /*
        This controller part for search resumes by id, username, location, experience and contacts.
        These methods for REST API part from master branch.
     */

    /**
     * Search resume by id
     *
     * @param id - id in database
     * @return resume (if id was found)
     * @throws ResumeNotFoundException (if id not found)
     * @see ResumeNotFoundException
     */
    @GetMapping("/{id}")
    public Resume findResumeById(@PathVariable Integer id) {
        try {
            resume = resumeService.findResumeById(id);
        } catch (ResumeNotFoundException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return resume;
    }

    /**
     * Search resume with user, location, experience or contacts
     *
     * @param user       - username for search
     * @param location   - location of employee
     * @param contacts   - contacts of employee
     * @param experience - experience
     * @return - resume list
     */
    @GetMapping("/")
    public Page<Resume> findResumeByFieldOrField(@RequestParam(required = false) String location,
                                                 @RequestParam(required = false) String contacts,
                                                 @RequestParam(required = false) String experience,
                                                 @RequestParam(required = false) String user,
                                                 @PageableDefault(size = 4, sort = {"user"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Resume> resumeList = null;
        try {
            resumeList = resumeService.findResumeByUserContainsOrLocationContainsOrContactsContainsOrExperienceContains(user, location, contacts, experience, pageable);
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return resumeList;
    }
}
