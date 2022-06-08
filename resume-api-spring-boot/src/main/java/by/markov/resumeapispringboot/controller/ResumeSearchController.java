package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for search resume with another fields
 *
 * @author markov_vadim
 */

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class ResumeSearchController {

    private final ResumeService resumeService;

    private static final Logger logger = LoggerFactory.getLogger(ResumeSearchController.class);

    /**
     * Search resume with location, experience or contacts
     *
     * @param location   - location of employee
     * @param contacts   - contacts of employee
     * @param experience - experience
     * @return - resume list
     */

    @GetMapping("/")
    public Page<Resume> findResumeByFieldOrField(@RequestParam(required = false) String location,
                                                 @RequestParam(required = false) String contacts,
                                                 @RequestParam(required = false) String experience,
                                                 @PageableDefault(size = 4, sort = {"user"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Resume> resumeList = null;
        try {
            resumeList = resumeService.findResumeByLocationContainsOrContactsContainsOrExperienceContains(location, contacts, experience, pageable);
            logger.info("Search resume by " + location + "" + contacts + "" + experience + "\nResume list: " + resumeList);
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
            logger.error("Resumes not found with " + location + contacts + experience);
        }
        return resumeList;
    }
}
