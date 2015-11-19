package org.training.services;

import org.training.domain.Department;

import java.util.List;

public interface DepartmentService {

    Department getDepartmentById(int deptId);
    Department createDepartment(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(int deptId);
    List<Department> getDepartmentsByName(String name);


}
