package by.markov.resumeapispringboot.repository;

import by.markov.resumeapispringboot.entity.Resume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends CrudRepository<Resume, Integer> {
}