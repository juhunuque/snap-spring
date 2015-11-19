package org.training.repositories;

import org.training.domain.Department;

public interface DepartmentRepository {

    Department findOne(Integer deptId);

    Department save(Department department);

    boolean exists(Integer deptId);

    void delete(Integer deptId);

}
