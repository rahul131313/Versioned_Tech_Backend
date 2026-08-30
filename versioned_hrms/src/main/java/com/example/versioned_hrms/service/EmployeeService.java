package com.example.versioned_hrms.service;

import com.example.versioned_hrms.dto.request.EmployeeRequest;
import com.example.versioned_hrms.dto.response.EmployeeResponse;
import com.example.versioned_hrms.entity.Employee;
import com.example.versioned_hrms.exception.EmployeeNotFoundException;
import com.example.versioned_hrms.repositary.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse create(EmployeeRequest request) {

        log.info(
                "Creating employee with email={}",
                request.getEmail()
        );

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());

        Employee saved =
                employeeRepository.save(employee);

        log.info(
                "Employee created successfully id={}",
                saved.getId()
        );

        return new EmployeeResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getDepartment()
        );
    }


    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(id));
    }

    public Employee update(Long id, Employee employee) {

        Employee existing = getById(id);

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());

        return employeeRepository.save(existing);
    }

    public void delete(Long id) {

        Employee employee = getById(id);

        employeeRepository.delete(employee);
    }
}
