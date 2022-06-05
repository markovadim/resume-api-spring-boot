package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import by.markov.resumeapispringboot.service.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeServiceImpl resumeService;

    private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

    @GetMapping
    public ResponseEntity showAllResumes() {
        try {
            return ResponseEntity.ok(resumeService.showResumeList());
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping
    public void createResume(@RequestBody Resume resume) {
        try {
            resumeService.additionResume(resume);
            ResponseEntity.ok("Resume was saved");
        } catch (ResumeAlreadyExistException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error.");
        }
    }

    @DeleteMapping("/{id}")
    public Resume deleteResumeById(@PathVariable Integer id) throws ResumeNotFoundException {
        Resume resume = null;
        try {
            resume = resumeService.deleteResume(id);
            logger.info("DELETE method of resume with id:" + id);
        } catch (ResumeNotFoundException e) {
            logger.error("Resume with id:" + id + " not found.");
            ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error.");
        }
        return resume;
    }

    @PutMapping("/{id}")
    public Resume updateResume(@PathVariable Integer id, @RequestBody Resume newResume) throws ResumeNotFoundException {
        Resume resume = null;
        try {
            resume = resumeService.updateResume(id, newResume);
            logger.info("PUT method by URL /resume/" + id + " with parameter new resume: " + newResume);
        } catch (ResumeNotFoundException e) {
            logger.error("Resume with id:" + id + " not found.");
            ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error.");
        }
        return resume;
    }

    @GetMapping("/id/{id}")
    public Resume findResumeById(@PathVariable Integer id) throws ResumeNotFoundException {
        Resume resume = null;
        try {
            resume = resumeService.findResumeById(id);
            logger.info("GET method with URL /resume/id (id:" + id + ")\nResponse: " + resume);
            return resume;
        } catch (ResumeNotFoundException e) {
            logger.error("Resume not found with id:" + id);
            ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error.");
        }
        return resume;
    }

    @GetMapping("/user/{user}")
    public Resume findResumeByUser(@PathVariable String user) throws ResumeNotFoundException {
        Resume resume = null;
        try {
            resume = resumeService.findResumeByUser(user);
            logger.info("GET method with URL /resume/user (user:" + user + ")\nResponse: " + resume);
        } catch (ResumeNotFoundException e) {
            logger.error("Resume not found with user:" + user);
            ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error.");
        }
        return resume;
    }
}
