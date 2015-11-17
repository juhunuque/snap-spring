package org.training.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.controllers.exceptions.DepartmentNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/department")
public class DepartmentController {


    private AtomicInteger nextId = new AtomicInteger(0);
    private Map<Integer, String> repository = new HashMap<>();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getDepartmentName(@PathVariable Integer id) {
        if (!repository.containsKey(id)) {
            return new ResponseEntity<>(
                String.format("Department %s does not exist", id),
                HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(repository.get(id), HttpStatus.OK);
    }


    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createDepartment(@PathVariable String name) {
        Integer id = nextId.incrementAndGet();
        repository.put(id, name);
        return id.toString();
    }

    // http://localhost:8080/api/department/1?name=Engineering

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateDepartment(@PathVariable Integer id, @RequestParam String name) {
        if (!repository.containsKey(id)) {
            throw new DepartmentNotFoundException(id);
        }
        repository.put(id, name);
        return name;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Integer id) {
        repository.remove(id);
    }



}
