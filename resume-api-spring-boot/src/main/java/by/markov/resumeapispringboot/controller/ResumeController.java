package by.markov.resumeapispringboot.controller;


import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeRepository resumeRepository;

    @PostMapping("/addResume")
    public ResponseEntity addResume(@RequestBody Resume resume) {
        try {
            resumeRepository.save(resume);
            return ResponseEntity.ok("Resume was saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity printResumeById(@PathVariable Integer id) {
        try {
            Optional<Resume> resume = resumeRepository.findById(id);
            if (!resume.isEmpty()) {
                return ResponseEntity.ok(resume);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found with id:" + id);
        }
        return ResponseEntity.ok("User with id:" + id + " was found");
    }
}
