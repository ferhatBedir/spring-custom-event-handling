package com.ferhat.pubsub.controller;

import com.ferhat.pubsub.entity.Department;
import com.ferhat.pubsub.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/department")
@Api(value = "Department Class Controller", description = "CRUD Operations")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation(value = "Add Department by this method")
    @PostMapping("/add")
    public void addDepartment(@RequestBody List<Department> departmentList, HttpServletResponse httpServletResponse) throws IOException {
        departmentService.addDepartments(departmentList, httpServletResponse);
    }

    @ApiOperation(value = "Edit Department by this method")
    @PostMapping("/edit")
    public void editDepartment(@RequestBody Department department,
                               BindingResult bindingResult,
                               HttpServletResponse httpServletResponse) throws IOException {
        if (bindingResult.hasErrors()) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please check parameters.");
        } else {
            departmentService.editDepartment(department);
        }
    }

    @ApiOperation(value = "All see Department by this method")
    @GetMapping("/getall")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @ApiOperation(value = "See Department with UserId by this method")
    @GetMapping("/getdepartmentbyuserid")
    public Department getDepartmentByUserId(@RequestParam(value = "userid") String userId) {
        return departmentService.getDepartmentByUserId(userId);
    }

    @ApiOperation(value = "See Department with DepartmentId by this method")
    @GetMapping("/getdepartmentbydepartmentid")
    public Department getDepartmentByDepartmentId(@RequestParam(value = "departmentid") String departmenId) {
        return departmentService.getDepartmentByDepartmentId(departmenId);
    }

    @ApiOperation(value = "All delete Department by this method")
    @DeleteMapping("/deleteall")
    public void deleteAllDepartment() {
        departmentService.deleteAllDepartment();
    }

}
