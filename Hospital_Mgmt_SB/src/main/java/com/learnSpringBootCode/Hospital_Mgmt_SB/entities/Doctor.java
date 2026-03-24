package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor_mst_sb")
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Email
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Appointment> appointments;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Department> departments = new HashSet<>();
}
