package org.BartoszM.deskreservationapi;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "desks")
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numer biurka jest wymagany!")
    private String deskNumber;

    private boolean isAvailable;

    @NotBlank(message = "Imię pracownika nie może być puste!")
    private String employeeName;
}
