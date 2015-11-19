package org.training.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.domain.Department;
import org.training.repositories.BestDepartmentRepository;

import java.util.List;

@Service
public class DefaultDepartmentService implements DepartmentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("best")
    private BestDepartmentRepository departmentRepository;

    @Override
    public Department getDepartmentById(int deptId) {
        logger.trace("Finding department {}", deptId);
        return departmentRepository.findOne(deptId);
    }

    @Override
    @Transactional
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

    @Override
    public List<Department> getDepartmentsByName(String name) {
        return departmentRepository.findByNameLike(String.format("%s%%", name));
    }

    /*
    public DepartmentRepository getDepartmentRepository() {
        return departmentRepository;
    }

    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    */
}
