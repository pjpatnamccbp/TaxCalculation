package com.imaginnovate.taxapis.services.impl;

import com.imaginnovate.taxapis.dto.EmployeeDto;
import com.imaginnovate.taxapis.dto.TaxResponseDto;
import com.imaginnovate.taxapis.entites.Employee;
import com.imaginnovate.taxapis.exceptions.EmployeeNotFoundException;
import com.imaginnovate.taxapis.repositories.EmployeeRepository;
import com.imaginnovate.taxapis.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        //converting employee dto object to employee entity object to save in db by using model mapper object
        Employee employee = modelMapper.map(employeeDto,Employee.class);

        //saving and returning employee entity object as dto object by wrapping the code
        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    @Override
    public TaxResponseDto calculateTax(Long employeeId) {
        //checking employee details with given id and throwing custom exception that employee not found
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException(String.format("employee with id %d not found in db",employeeId))
        );

        //Assuming that tax calculation for 2022-2023 financial year
        LocalDate startDate = LocalDate.of(2022,3,31);
        LocalDate endDate = LocalDate.of(2023,3,31);
        LocalDate doj = employee.getDoj();

        //getting months from end of year from date of joining
        int monthsWorked = Period.between(doj,endDate).getMonths() + 1;
        System.out.println(monthsWorked);
        //getting lop days between year start to date of joining
        int lop = Period.between(startDate,doj).getDays();
        System.out.println(lop);

        //monthly salary from employee
        Double monthlySalary = employee.getSalary();
        Double daySalary = monthlySalary/30;


        Double yearlySalary,tax = 0d;

        //calculating yearly salry based on no of working months and deducting lops
        yearlySalary = monthlySalary*monthsWorked - lop*daySalary;

        if(yearlySalary > 1000000){
            tax = 200000+(yearlySalary-1000000)*0.2;
        }else if(yearlySalary>500000){
            tax = 200000+(yearlySalary-500000)*0.1;
        }else if(yearlySalary>250000) {
            tax = (yearlySalary-250000)*0.05;
        }

        Double cess = 0d;

        if(yearlySalary>2500000){
            cess = tax*0.02;
        }

        TaxResponseDto taxResponseDto = new TaxResponseDto();
        taxResponseDto.setEmployeeId(employeeId);
        taxResponseDto.setFirstName(employee.getFirstName());
        taxResponseDto.setLastName(employee.getLastName());
        taxResponseDto.setYearlySalary(yearlySalary);
        taxResponseDto.setCess(cess);
        taxResponseDto.setTax(tax);

        return taxResponseDto;

    }
}
