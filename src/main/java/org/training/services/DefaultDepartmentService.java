package org.training.services;

import org.training.domain.Department;
import org.training.repositories.DepartmentRepository;

public class DefaultDepartmentService implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public Department getDepartmentById(int deptId) {
        return departmentRepository.findOne(deptId);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(int deptId) {
        departmentRepository.delete(deptId);
    }

    public DepartmentRepository getDepartmentRepository() {
        return departmentRepository;
    }

    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
}
