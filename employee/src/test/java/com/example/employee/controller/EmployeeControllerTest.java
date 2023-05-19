package com.example.employee.controller;

import com.example.employee.model.EmployeeRequest;
import com.example.employee.model.EmployeeResponse;
import com.example.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private EmployeeController employeeController;
    private EmployeeService sut;


    @BeforeEach
    void setUp() {
        sut = mock(EmployeeService.class);
        employeeController = new EmployeeController(sut);
    }


    @Test
    void testFindAll() {

        EmployeeRequest employee1 = getEmployeeRequest();

        List<EmployeeRequest> employeeRequestList = List.of(employee1);

        when(sut.findAll()).thenReturn(employeeRequestList);

        ResponseEntity<List<EmployeeRequest>> response = employeeController.findAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeRequestList, response.getBody());

    }

    @Test
    void createEmployeeTest(){

        EmployeeRequest employeeRequest=new EmployeeRequest();
        EmployeeResponse employeeResponse=new EmployeeResponse();
        employeeResponse.setEmployeeId(1L);

        when(sut.createEmployee(employeeRequest)).thenReturn(employeeResponse);

        ResponseEntity<EmployeeResponse> response =employeeController.createEmployee(employeeRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(employeeResponse,response.getBody());

    }

    @Test
    void getEmployeeTest(){

        Long employeeId=1L;
        EmployeeRequest employeeRequest= getEmployeeRequest();

        when(sut.getEmployee(employeeId)).thenReturn(employeeRequest);

        ResponseEntity<EmployeeRequest> response=employeeController.getEmployee(employeeId);

        verify(sut).getEmployee(employeeId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(employeeRequest,response.getBody());



    }

    private EmployeeRequest getEmployeeRequest() {
        EmployeeRequest request = new EmployeeRequest();
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Uttam");
        employeeRequest.setSalary(100D);
        employeeRequest.setEmail("XYZ@gmail.com");
        employeeRequest.setDob("23/09/2022");
        return employeeRequest;

    }


}
