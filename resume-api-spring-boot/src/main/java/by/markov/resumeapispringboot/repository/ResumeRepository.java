package by.markov.resumeapispringboot.repository;

import by.markov.resumeapispringboot.entity.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends CrudRepository<Resume, Integer> {
}
