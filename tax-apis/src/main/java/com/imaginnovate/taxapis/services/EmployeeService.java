package com.imaginnovate.taxapis.services;

import com.imaginnovate.taxapis.dto.EmployeeDto;
import com.imaginnovate.taxapis.dto.TaxResponseDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    TaxResponseDto calculateTax(Long employeeId);

}
