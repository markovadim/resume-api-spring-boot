package by.markov.resumeapispringboot.model.entity;

import by.markov.resumeapispringboot.model.enumfield.Contact;
import by.markov.resumeapispringboot.model.enumfield.Experience;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

    Long id;
    User user;
    String location;
    Experience experience;
    String[] skills;
    String[] languages;
    Map<Contact, String> contacts;
}
