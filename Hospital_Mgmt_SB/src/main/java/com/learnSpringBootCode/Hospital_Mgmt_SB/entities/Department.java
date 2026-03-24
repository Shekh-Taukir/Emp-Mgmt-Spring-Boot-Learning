package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToOne()
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "head_doctor_id", foreignKey = @ForeignKey(name = "fk_headdoc_department_mst_sb_doctor_mst_sb"))
    @ToString.Exclude
    @JsonIgnore
    private Doctor headDoctor;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Doctor> doctors = new HashSet<>();

    @JsonProperty("headDoctorId")
    @ToString.Include(name = "headDoctorId")
    public Long getHeadDoctorId(){
        return headDoctor.getId();
    }

}
