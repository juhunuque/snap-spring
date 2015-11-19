package org.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.training.controllers.exceptions.InvalidPayloadException;
import org.training.domain.Department;
import org.training.services.DepartmentService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

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
    public Department createDepartment(@RequestBody @Valid Department department, BindingResult binding) {
        if (department.getId() != null) {
            throw new InvalidPayloadException(department);
        }
        if(binding.hasErrors()) {
            throw new InvalidPayloadException(getValidationMessage(binding));
        }
        return departmentService.createDepartment(department);
    }

    private static List<String> getValidationMessage(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors()
                .stream()
                .map(DepartmentController::getValidationMessage)
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static String getValidationMessage(ObjectError error) {
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            String className = fieldError.getObjectName();
            String property = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("%s.%s %s, but it was %s", className, property, message, invalidValue);
        }
        return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
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
