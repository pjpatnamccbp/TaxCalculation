package com.imaginnovate.taxapis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long employeeId;

    @NotNull(message = "firstName required")
    @Size(min = 3,message = "firstName should be min 3 letters")
    private String firstName;
    @NotNull(message = "lastName required")
    @Size(min = 3,message = "lastName should be min 3 letters")
    private String lastName;

    @NotNull(message = "email should be provided")
    @Email(message = "Enter valid email id")
    private String email;

    @NotNull(message = "mobile number mandatory")
    @Size(min = 10)
    private String mobile;

    @NotNull(message = "salary mandatory")
    @Positive(message = "Salary Should be Positive only")
    private Double salary;

    //@NotNull(message = "Date of joining mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate doj;

}
