package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import by.markov.resumeapispringboot.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/list")
    public ResponseEntity showAllResumes() {
        try {
            return ResponseEntity.ok(resumeService.showResumeList());
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addResume(@RequestBody Resume resume) {
        try {
            resumeService.additionResume(resume);
            return ResponseEntity.ok("Resume was saved");
        } catch (ResumeAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error.");
        }
    }

    @GetMapping
    public ResponseEntity getResume(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(resumeService.getResume(id));
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResumeById(@PathVariable Integer id) throws ResumeNotFoundException {
        try {
            return ResponseEntity.ok(resumeService.deleteResume(id));
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateResume(@PathVariable Integer id, @RequestBody Resume resume) {
        try {
            return ResponseEntity.ok(resumeService.updateResume(id, resume));
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
