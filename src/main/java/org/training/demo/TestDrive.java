package org.training.demo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.training.services.DepartmentService;

public class TestDrive {

    public static void main(String[] args) {

        try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:demo.xml")){

            DepartmentService deptService = context.getBean("departmentService", DepartmentService.class);
            deptService.getDepartmentById(1);

        }


    }
}
