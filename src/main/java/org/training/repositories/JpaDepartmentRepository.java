package org.training.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.training.domain.Department;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Qualifier("jpa")
public class JpaDepartmentRepository implements DepartmentRepository {

    @PersistenceContext(unitName = "training")
    private EntityManager entityManager;

    @Override
    public Department findOne(Integer deptId) {
        return entityManager.find(Department.class, deptId);
    }

    @Override
    public boolean exists(Integer deptId) {
        return entityManager.find(Department.class, deptId) != null;
    }

    @Override
    public Department save(Department department) {
        return entityManager.merge(department);
    }

    @Override
    public void delete(Integer deptId) {
        Department dept = entityManager.find(Department.class, deptId);
        if (dept != null) {
            entityManager.remove(dept);
        }
    }
}
