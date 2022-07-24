package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import by.markov.resumeapispringboot.service.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * Show all resumes from database
     *
     * @param pageable - pagination
     * @return page with 5 employees
     */
    @GetMapping
    public Page<Resume> showAllResumes(@PageableDefault(size = 5) Pageable pageable) throws ResumeNotFoundException {
        return resumeService.findAll(pageable);
    }

    /**
     * Create new resume
     *
     * @param resume - main object with fields (user, location, experience, contacts)
     * @see ResumeAlreadyExistException
     * @see Resume
     */
    @PostMapping
    public Resume createResume(@RequestBody Resume resume) throws ResumeAlreadyExistException {
        resumeService.addResume(resume);
        return resume;
    }

    /**
     * Remove resume by id
     *
     * @param id - id in database
     * @return response entity with OK status
     * @throws ResumeNotFoundException (if id not found)
     * @see ResumeNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResumeById(@PathVariable Integer id) throws ResumeNotFoundException {
        resumeService.deleteResume(id);
        return ResponseEntity.ok().body("Resume Deleted");
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
    public Resume updateResume(@PathVariable Integer id, @RequestBody Resume newResume) throws ResumeNotFoundException {
        return resumeService.updateResume(id, newResume);
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
    public Resume findResumeById(@PathVariable Integer id) throws ResumeNotFoundException {
        return resumeService.findResumeById(id);
    }

    /**
     * Search resume with user, location, experience or contacts
     *
     * @param keyword - keyword for search
     * @return - resume list
     */
    @GetMapping("/search")
    public List<Resume> findResumeByKeyword(@RequestParam(required = false) String keyword) {
        return resumeService.findResumeByKeyword(keyword);
    }
}
