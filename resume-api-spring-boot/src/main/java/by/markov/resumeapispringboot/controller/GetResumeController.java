package by.markov.resumeapispringboot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetResumeController {

    @GetMapping("/resume{id}")
    public String getResume(@PathVariable String id){
        return id;
    }

    @GetMapping("/")
    public String getStartPage(){
        return "Start page";
    }
}
