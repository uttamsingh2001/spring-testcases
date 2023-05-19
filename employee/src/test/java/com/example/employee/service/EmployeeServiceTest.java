package com.example.employee.service;

import com.example.employee.entity.EmployeeEnitity;
import com.example.employee.mapper.EmployeeMapper;
import com.example.employee.model.EmployeeRequest;
import com.example.employee.model.EmployeeResponse;
import com.example.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;
    RestTemplate restTemplate;
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeMapper = mock(EmployeeMapper.class);
        restTemplate = mock(RestTemplate.class);
        employeeService = new EmployeeService(employeeRepository, employeeMapper, restTemplate);
    }

    @Test
    void createEmployeeTest() {
        EmployeeEnitity employeeEnitity = new EmployeeEnitity();
        employeeEnitity.setEmployeeId(1L);
        EmployeeRequest employeeRequest = new EmployeeRequest();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(1L);

        when(employeeMapper.modelToEnitity(employeeRequest)).thenReturn(employeeEnitity);
        when(employeeRepository.save(employeeEnitity)).thenReturn(employeeEnitity);

        EmployeeResponse response = employeeService.createEmployee(employeeRequest);

        assertNotNull(response);
        assertEquals(employeeResponse, response.getEmployeeId());


    }

    @Test
    void testGetEmployee() {
        Long employeeId = 1L;
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("ABC");
        employeeRequest.setEmail("@XYZ");
        employeeRequest.setDob("2001-10-12");
        employeeRequest.setSalary(20000D);

        EmployeeEnitity employeeEnitity = new EmployeeEnitity();


        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEnitity));
        when(employeeMapper.enitityToModel(employeeEnitity)).thenReturn(employeeRequest);

        EmployeeRequest response= employeeService.getEmployee(employeeId);

        assertNotNull(response);
        assertEquals(employeeRequest.getName(),response.getName());



    }

    @Test
    void findAllEmployeeTest(){

        List<EmployeeEnitity> employeeEnitities=new ArrayList<>();
        List<EmployeeRequest> employeeRequest=new ArrayList<>();

        when(employeeRepository.findAll()).thenReturn(employeeEnitities);
        when(employeeMapper.enitityToModels(employeeEnitities)).thenReturn(employeeRequest);

        List<EmployeeRequest> response =employeeService.findAll();

        assertNotNull(response);
        assertEquals(employeeRequest.size(),response.size());

   }


}
