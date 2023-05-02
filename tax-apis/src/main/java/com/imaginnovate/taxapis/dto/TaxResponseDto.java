package com.imaginnovate.taxapis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaxResponseDto {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Double yearlySalary;
    private Double cess;
    private Double tax;
}
