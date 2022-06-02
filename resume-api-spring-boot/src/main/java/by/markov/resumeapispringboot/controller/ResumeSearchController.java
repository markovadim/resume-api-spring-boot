package by.markov.resumeapispringboot.controller;

import by.markov.resumeapispringboot.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class ResumeSearchController {

    private final ResumeService resumeService;

    @GetMapping("/fieldOrField")
    public ResponseEntity findResumeByFieldOrField(@RequestParam(required = false) String location,
                                                   @RequestParam(required = false) String contacts,
                                                   @RequestParam(required = false) String experience) {
        return ResponseEntity.ok(resumeService.findResumeByLocationContainsOrContactsContainsOrExperienceContains(location, contacts, experience));
    }

    @GetMapping("/fieldAndField")
    public ResponseEntity findResumeByFieldAndField(@RequestParam String location,
                                                    @RequestParam String contacts,
                                                    @RequestParam String experience) {
        return ResponseEntity.ok(resumeService.findResumeByLocationContainsAndContactsContainsAndExperienceContains(location, contacts, experience));
    }
}
