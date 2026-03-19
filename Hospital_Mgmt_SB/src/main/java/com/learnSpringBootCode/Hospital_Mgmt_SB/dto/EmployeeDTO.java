package com.learnSpringBootCode.Hospital_Mgmt_SB.dto;

import com.learnSpringBootCode.Hospital_Mgmt_SB.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Employee Name cannot be empty")
    @Size(min = 3, max = 20,message = "No. of Chars should be in the range of 3 to 20")
    private String name;

    @Email(message = "Email should be a valid email")
    @NotBlank(message = "Email of employee cannot be blank")
    private String email;

    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 18, message = "Age cannot be less than 18")
    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotBlank(message = "Role of employee cannot be blank")
//    @Pattern(regexp = "^(Admin|User)$", message = "Employee role can be Admin or user only")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary of employee should be positive")
    private Integer salary;

    @Digits(integer = 2, fraction = 2, message = "Increment should be in the form XX.YY format")
    @DecimalMax(value = "99.89", message = "")
    @DecimalMin(value = "0.89")
    private Double increment;

    @PastOrPresent(message = "Date of joining cannot be in future")
    private LocalDateTime dateOfJoining;

    @AssertTrue(message = "EMployee should be active profile")
    private Boolean isActive;
}
