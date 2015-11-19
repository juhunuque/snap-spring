package org.training.repositories;

import org.training.controllers.exceptions.DepartmentNotFoundException;
import org.training.domain.Department;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultDepartmentRepository implements DepartmentRepository {

    private AtomicInteger nextId = new AtomicInteger(0);
    private Map<Integer, Department> repository = new HashMap<>();

    @Override
    public Department findOne(Integer id) {
        if (!repository.containsKey(id)) {
            throw new DepartmentNotFoundException(id);
        }
        return repository.get(id);

    }

    @Override
    public Department save(Department department) {
        if(department.getId() == null) {
            Integer id = nextId.incrementAndGet();
            department.setId(id);
        }
        repository.put(department.getId(), department);
        return department;
    }

    @Override
    public void delete(Integer deptId) {
        repository.remove(deptId);
    }


}
