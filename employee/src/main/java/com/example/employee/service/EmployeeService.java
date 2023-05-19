package com.example.employee.service;

import com.example.employee.entity.EmployeeEnitity;
import com.example.employee.mapper.EmployeeMapper;
import com.example.employee.model.EmployeeRequest;
import com.example.employee.model.EmployeeResponse;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private final RestTemplate restTemplate;

    String url="http://localhost:8090";

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.restTemplate = restTemplate;
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        EmployeeEnitity employeeEnitity = employeeMapper.modelToEnitity(employeeRequest);
        employeeRepository.save(employeeEnitity);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(employeeEnitity.getEmployeeId());
        return employeeResponse;
    }

    public List<EmployeeRequest> findAll() {
        List<EmployeeEnitity> employeeEnitity = employeeRepository.findAll();
        List<EmployeeRequest> employeeRequests=employeeMapper.enitityToModels(employeeEnitity);
       return employeeRequests;
    }
    public EmployeeRequest getEmployee(Long employeeId) {
        EmployeeEnitity employeeEnitity=employeeRepository.findById(employeeId).get();
        EmployeeRequest employeeRequest= employeeMapper.enitityToModel(employeeEnitity);
        return employeeRequest;

    }
}


//        HttpEntity<EmployeeRequest> entity = new HttpEntity<>(employeeRequest);
//        restTemplate.exchange(url + "/employees", HttpMethod.POST, entity, EmployeeRequest.class).getBody();


