package by.markov.resumeapispringboot.controller;


import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
