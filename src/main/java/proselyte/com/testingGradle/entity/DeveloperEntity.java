package proselyte.com.testingGradle.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developer")
public class DeveloperEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String firstName;
    private String lastName;
    private String speciality;

    @Enumerated(EnumType.STRING)
    private Status status;


}
