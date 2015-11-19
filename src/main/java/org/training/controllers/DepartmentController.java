package org.training.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training.controllers.exceptions.InvalidPayloadException;
import org.training.domain.Department;
import org.training.services.DefaultDepartmentService;
import org.training.services.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    //TODO: remove coupling
    private DepartmentService departmentService = new DefaultDepartmentService();

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = {"application/json", "application/xml"}
    )
    public Department getDepartment(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    // POST http://localhost:8080/api/department/ {body: JSON/XML}

    @RequestMapping(
        method = RequestMethod.POST,
        produces = {"application/json", "application/xml"},
        consumes = {"application/json", "application/xml"}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody Department department) {
        if (department.getId() != null) {
            throw new InvalidPayloadException(department);
        }
        return departmentService.createDepartment(department);
    }

    // PUT http://localhost:8080/api/department/1 {body JSON/XML}

    @RequestMapping(
        path = "/{id}",
        method = RequestMethod.PUT,
        produces = {"application/json", "application/xml"},
        consumes = {"application/json", "application/xml"}
    )
    @ResponseStatus(HttpStatus.OK)
    public Department updateDepartment(@PathVariable Integer id, @RequestBody Department department) {
        if(!id.equals(department.getId())){
            throw new InvalidPayloadException(department);
        }
        return departmentService.updateDepartment(department);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
    }


}
