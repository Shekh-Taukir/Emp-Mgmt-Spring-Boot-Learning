package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.type.UserBloodGroupEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Table(name = "patient_mst_sb")
@Entity
@Builder
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

    @OneToOne //Owning side
    @JoinColumn(
            name = "insurance_id",
            foreignKey = @ForeignKey(name= "fk_patient_mst_sb_patient_insurance_mst_sb")
    )
    private PatientInsurance insurance;

    @OneToMany(mappedBy = "patient") //inverse Side
    private List<Appointment> apointments;
}
