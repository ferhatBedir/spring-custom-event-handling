package com.ferhat.PubSub.controller;

import com.ferhat.PubSub.entity.Department;
import com.ferhat.PubSub.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    public void addDepartment(@RequestBody List<Department> departmentList, HttpServletResponse httpServletResponse) throws IOException {
        departmentService.addDepartment(departmentList, httpServletResponse);
    }

    @GetMapping("/getall")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/getdepartmentbyuserid")
    public Department getDepartmentByUserId(@RequestParam(value = "id") String userId) {
        return departmentService.getDepartmentByUserId(userId);
    }

}
