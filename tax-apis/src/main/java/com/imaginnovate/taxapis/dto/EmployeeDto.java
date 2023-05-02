package com.imaginnovate.taxapis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long employeeId;

    @NotNull(message = "must required")
    @Size(min = 3,message = "firstName should be min 3 letters")
    private String firstName;
    @NotNull(message = "must required")
    @Size(min = 3,message = "lastName should be min 3 letters")
    private String lastName;

    @NotNull(message = "email should be provided")
    @Email(message = "Enter valid email id")
    private String email;

    //@NotEmpty(message = "mobile number mandatory")
    private Long mobile;

    //@NotEmpty(message = "salary mandatory")
    private Double salary;

    @NotNull(message = "Date of joining mandatory")
    private LocalDate doj;

}
