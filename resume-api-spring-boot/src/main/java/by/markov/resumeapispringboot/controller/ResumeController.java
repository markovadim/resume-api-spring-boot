package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import by.markov.resumeapispringboot.service.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Main controller for CRUD resume
 *
 * @author markov_vadim
 */

@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeServiceImpl resumeService;

    private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

    private Resume resume;

    /**
     * Show all resumes from database
     *
     * @param pageable - pagination
     * @return page with 5 employees
     */
    @GetMapping
    public Page<Resume> showAllResumes(@PageableDefault(size = 5) Pageable pageable) {
        Page<Resume> resumePage = null;
        try {
            resumePage = resumeService.showResumeList(pageable);
            ResponseEntity.ok(resumePage);
        } catch (ResumeNotFoundException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return resumePage;
    }

    /**
     * Create new resume
     *
     * @param resume - main object with fields (user, location, experience, contacts)
     * @see Resume
     */
    @PostMapping
    public void createResume(@RequestBody Resume resume) {
        try {
            resumeService.additionResume(resume);
            ResponseEntity.ok("Resume was saved");
        } catch (ResumeAlreadyExistException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Remove resume by id
     *
     * @param id - id in database
     */
    @DeleteMapping("/{id}")
    public void deleteResumeById(@PathVariable Integer id) {
        try {
            resumeService.deleteResume(id);
            logger.info("DELETE method of resume with id:" + id);
        } catch (ResumeNotFoundException e) {
            logger.error("Resume with id:" + id + " not found.");
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Update resume
     *
     * @param id        - id in database
     * @param newResume - resume with new field (fields)
     * @return update resume (if id is right)
     * @throws ResumeNotFoundException (if id not found)
     * @see ResumeNotFoundException
     */
    @PutMapping("/{id}")
    public Resume updateResume(@PathVariable Integer id, @RequestBody Resume newResume) {
        try {
            resume = resumeService.updateResume(id, newResume);
            logger.info("PUT method by URL /resume/" + id + " with parameter new resume: " + newResume);
        } catch (ResumeNotFoundException e) {
            logger.error("Resume with id:" + id + " not found.");
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return resume;
    }

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
            logger.info("GET method with URL /resume/id (id:" + id + ")\nResponse: " + resume);
        } catch (ResumeNotFoundException e) {
            logger.error("Resume not found with id:" + id);
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
            logger.info("Search resume by " + user + location + "" + contacts + "" + experience + "\nResume list: " + resumeList);
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
            logger.error("Resumes not found with " + user + location + contacts + experience);
        }
        return resumeList;
    }
}
