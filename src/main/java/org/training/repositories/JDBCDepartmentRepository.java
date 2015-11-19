package org.training.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.training.domain.Department;

import javax.sql.DataSource;

@Repository
@Qualifier("jdbc")
public class JdbcDepartmentRepository implements DepartmentRepository {

    private final DataSource dataSource;

    //TODO: remove coupling
    public JdbcDepartmentRepository(DataSource dataSource) {
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
