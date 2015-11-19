package org.training.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.training.domain.Department;

import java.util.List;

@Qualifier("best")
public interface BestDepartmentRepository extends CrudRepository<Department, Integer> {

    /**
     * Finds all departments whose name is the like name provided.
     *
     * @param name - the name expressions.
     * @return the found departments.
     */
    @Query(name = "DepartmentByNameLike")
    List<Department> findByNameLike(@Param("deptName") String name);

}
