package org.training.repositories;

import org.training.domain.Department;

import javax.sql.DataSource;

public class JDBCDepartmentRepository implements DepartmentRepository {

    private final DataSource dataSource;

    //TODO: remove coupling
    public JDBCDepartmentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Department findOne(Integer deptId) {
        return null;
    }

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public void delete(Integer deptId) {

    }
}
