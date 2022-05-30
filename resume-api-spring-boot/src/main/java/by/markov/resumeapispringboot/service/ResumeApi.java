package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Resume;

public interface ResumeApi {
    Resume findResumeByUserName(String user);
    Resume findResumeByLocation(String location);
    Resume findResumeByContacts(String contacts);
    Resume findResumeByExperience(String experience);
}
