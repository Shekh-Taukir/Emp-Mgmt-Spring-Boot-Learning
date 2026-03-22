package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment_mst_sb")
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne //owning side
    @JoinColumn(
            name="patient_id",
            foreignKey = @ForeignKey(name = "fk_appointment_mst_sb_patient_mst_sb"),
            nullable = false
    )
    private Patient patient;

    @ManyToOne //owning side
    @JoinColumn(
            name = "doctor_id",
            foreignKey = @ForeignKey(name = "fk_appointment_mst_sb_doctor_mst_sb"),
            nullable = false
    )
    private Doctor doctor;
}
