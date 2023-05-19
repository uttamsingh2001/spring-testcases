package com.example.employee.controller;

import com.example.employee.entity.EmployeeEnitity;
import com.example.employee.model.EmployeeRequest;
import com.example.employee.model.EmployeeResponse;
import com.example.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse=employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }



    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeRequest>> findAll() {
        List<EmployeeRequest> employeeRequests = employeeService.findAll();
        return new ResponseEntity<>(employeeRequests, HttpStatus.OK);
    }

    @GetMapping(path = "/employees/{employeeId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeRequest>getEmployee( @PathVariable Long employeeId ){
        EmployeeRequest employeeRequest=employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employeeRequest,HttpStatus.OK);
    }




//
//    @DeleteMapping(path = "/employees/{employeeId}",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void>deleteEmployee(@PathVariable Long employeeId){
//        employeeService.deleteEmployee(employeeId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/employees/{empId}/department/{deptId}")
//    public ResponseEntity<EmployeeRequest> assignDepartmentToEmployee(@PathVariable Long empId,@PathVariable Long deptId)
//    {
//        EmployeeRequest employeeRequest=employeeService.assignDepartmentToEmployee(empId,deptId);
//        return new ResponseEntity<>(employeeRequest,HttpStatus.OK);
//    }

}



