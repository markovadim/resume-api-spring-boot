package by.markov.resumeapispringboot.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Main object of application with fields
 *
 * @author markov_vadim
 */

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    String user;

    @NotEmpty(message = "Location should not be empty")
    @Size(min = 2, max = 20, message = "Location should be between 2 and 20 characters")
    String location;

    String experience;

    @NotEmpty(message = "Contacts should not be empty")
    String contacts;
}
