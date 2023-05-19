package com.example.employee.mapper;

import com.example.employee.entity.EmployeeEnitity;
import com.example.employee.model.EmployeeRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface EmployeeMapper {

    EmployeeEnitity modelToEnitity(EmployeeRequest employeeRequest);
    EmployeeRequest enitityToModel(EmployeeEnitity employeeEnitity);

    List<EmployeeRequest> enitityToModels(List<EmployeeEnitity> employeeEnitities);


}
