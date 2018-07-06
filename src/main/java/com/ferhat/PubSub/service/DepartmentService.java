package com.ferhat.PubSub.service;

import com.ferhat.PubSub.entity.Department;
import com.ferhat.PubSub.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private SmartValidator smartValidator;


    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, SmartValidator smartValidator) {
        this.departmentRepository = departmentRepository;
        this.smartValidator = smartValidator;
    }

    public void addDepartment(List<Department> departmentList, HttpServletResponse httpServletResponse) throws IOException {
        if (departmentList != null || departmentList.isEmpty()) {
            System.out.println("DepartmentList is empty.");
        }
        for (Department newDepartment : departmentList) {
            Department department = departmentRepository.findByDepartmentId(newDepartment.getDepartmentId());
            if (department == null) {
                checkData(newDepartment, httpServletResponse);
                System.out.println("Department registered to Database.");
            } else {
                System.out.println("Department already register in database.");
            }
        }
    }

    public Department getDepartmentByUserId(String userId) {
        return departmentRepository.findByUserUserId(userId);
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public void checkData(Department department, HttpServletResponse httpServletResponse) throws IOException {
        DataBinder binder = new DataBinder(department);
        binder.validate();
        binder.setValidator(smartValidator);
        BindingResult bindingResult = binder.getBindingResult();

        if (bindingResult.hasErrors()) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please check parameters.");
        } else {
            departmentRepository.save(department);
        }
    }


}
