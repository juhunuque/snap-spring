package org.training.controllers.exceptions;

public class DepartmentNotFoundException extends RuntimeException {

    private final Integer deptId;

    public DepartmentNotFoundException(Integer deptId) {
        super("The department " + deptId + " does not exist");
        this.deptId = deptId;
    }

    public Integer getDeptId() {
        return deptId;
    }
}
