package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.type.UserBloodGroupEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "patient_mst_sb")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private String gender;

    @Enumerated(value = EnumType.STRING)
    private UserBloodGroupEnum bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) //Owning side
    @JoinColumn(
            name = "insurance_id",
            foreignKey = @ForeignKey(name= "fk_patient_mst_sb_patient_insurance_mst_sb")
    )
    private PatientInsurance insurance;

    //here we have to add cascading, because we don't want the system like when we delete appointment, the it should delete patient, but when patient is
    // deleted, then it should delete all the appointments as well.
    //thats why have to add cascading here in the inverse side of this relation
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE})
    @ToString.Exclude//inverse Side
    private List<Appointment> apointments;
}
