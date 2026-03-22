package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department_mst_sb")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "head_doctor_id",
            foreignKey = @ForeignKey(name = "fk_headdoc_department_mst_sb_doctor_mst_sb")
    )
    private Doctor headDoctor;

    @ManyToMany
    private Set<Doctor> doctors = new HashSet<>();

}
