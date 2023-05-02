package com.imaginnovate.taxapis.controllers;

import com.imaginnovate.taxapis.dto.EmployeeDto;
import com.imaginnovate.taxapis.dto.TaxResponseDto;
import com.imaginnovate.taxapis.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    // Rest API end point for saving employee details
    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("/tax/{id}")
    public ResponseEntity<TaxResponseDto> calculateTax(@PathVariable("id") Long employeeId){
        TaxResponseDto taxResposeDto = employeeService.calculateTax(employeeId);
        return new ResponseEntity<>(taxResposeDto, HttpStatus.OK);
    }

}
