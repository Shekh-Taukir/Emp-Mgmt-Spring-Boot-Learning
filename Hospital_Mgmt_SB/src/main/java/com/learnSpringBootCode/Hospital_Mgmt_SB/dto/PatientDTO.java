package com.learnSpringBootCode.Hospital_Mgmt_SB.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDTO {
    private Long id;

    @NotEmpty(message = "Name is required field")
    private String name;

    @NotNull(message = "BirthDate is required field")
    private LocalDate birthDate;

    @Email(message = "Provide valid email address")
    @NotEmpty(message = "Email is required field")
    private String email;

    @NotEmpty(message = "Gender is required field")
    private String gender;
}
