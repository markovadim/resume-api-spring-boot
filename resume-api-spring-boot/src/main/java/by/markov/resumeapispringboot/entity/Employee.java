package by.markov.resumeapispringboot.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    String name;

    @Min(value = 0, message = "Age should be greater than 0")
    Integer age;

    @NotEmpty(message = "Location should not be empty")
    @Size(min = 2, max = 20, message = "Location should be between 2 and 20 characters")
    String location;

    @Email(message = "Input email is not correct")
    @NotEmpty(message = "Contacts should not be empty")
    String email;
}
