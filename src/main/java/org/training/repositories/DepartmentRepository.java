package org.training.repositories;

import org.training.domain.Department;

public interface DepartmentRepository {

    Department findOne(Integer deptId);

    Department save(Department department);

    void delete(Integer deptId);

}
