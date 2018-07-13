package com.ferhat.pubsub.controller;

import com.ferhat.pubsub.entity.Department;
import com.ferhat.pubsub.model.DepartmentModel;
import com.ferhat.pubsub.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/department")
@Api(tags = "Department Controller", value = "Department Controller Operations")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation(value = "This method, Departments add to Database." +
            "\nTake List<Department>, HttpServletResponse in Parameters." +
            "\nReturn void.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/add")
    public void addDepartment(@RequestBody List<Department> departmentList, HttpServletResponse httpServletResponse) throws IOException {
        departmentService.addDepartments(departmentList, httpServletResponse);
    }

    @ApiOperation(value = "This method, Department edit and update in Database." +
            "\nTake Department,BindingResult and HttpServletResponse in Parameters." +
            "\nReturn void.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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

    @ApiOperation(value = "This method, All Departments see." +
            "\nTake no Parameters." +
            "\nReturn List.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getall")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @ApiOperation(value = "This method, Department see with UserId." +
            "\nTake URL Parameters. UserId must be in URL parameters." +
            "\nReturn Department.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getdepartmentbyuserid")
    public Department getDepartmentByUserId(@RequestParam(value = "userid") String userId) {
        return departmentService.getDepartmentByUserId(userId);
    }

    @ApiOperation(value = "This method, Department see with DepartmentId." +
            "\nTake URL Parameters. DepartmentId must be in URL Parameters." +
            "\nReturn Department.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getdepartmentbydepartmentid")
    public Department getDepartmentByDepartmentId(@RequestParam(value = "departmentid") String departmenId) {
        return departmentService.getDepartmentByDepartmentId(departmenId);
    }

    @ApiOperation(value = "This method, Departments see with DepartmentName." +
            "\nTake URL Parameters. DepartmentName must be in URL Parameters." +
            "\nReturn List<DepartmentModel>.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getdepartmentbydepartmentname")
    public List<DepartmentModel> getDepartmentModelByDepartmentName(@RequestParam(value = "departmentname") String departmentName) {
        return departmentService.getDepartmentModelByDepartmentName(departmentName);
    }

    @ApiOperation(value = "This method, All Departments delete in Database." +
            "\nTake no Parameters." +
            "\nReturn void.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/deleteall")
    public void deleteAllDepartment() {
        departmentService.deleteAllDepartment();
    }

}
