package org.training.services;

import org.training.domain.Department;

public interface DepartmentService {

    Department getDepartmentById(int deptId);
    Department createDepartment(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(int deptId);


}
