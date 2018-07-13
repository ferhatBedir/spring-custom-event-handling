package com.ferhat.pubsub.service;

import com.ferhat.pubsub.entity.Department;
import com.ferhat.pubsub.model.DepartmentModel;
import com.ferhat.pubsub.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

    public void addDepartments(List<Department> departmentList, HttpServletResponse httpServletResponse) throws IOException {
        if (departmentList == null || departmentList.isEmpty()) {
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

    public Department getDepartmentByDepartmentId(String departmentId) {
        return departmentRepository.findByDepartmentId(departmentId);
    }

    public List<DepartmentModel> getDepartmentModelByDepartmentName(String departmentName) {
        List<Department> departments = departmentRepository.findOneByDepartmentNameIgnoreCase(departmentName);
        if (departments == null) {
            throw new NullPointerException("Department List is null or Empty");
        }
        List<DepartmentModel> departmentModels = new ArrayList<>();
        departments.forEach(department -> departmentModels.add(convertToModel(department)));
        return departmentModels;
    }

    public void editDepartment(Department newDepartment) {
        departmentRepository.save(newDepartment);
        System.out.println("Department updated.");
    }

    public void deleteAllDepartment() {
        departmentRepository.deleteAll();
        System.out.println("All Departments deleted.");
    }

    private DepartmentModel convertToModel(Department entity) {
        DepartmentModel model = new DepartmentModel();
        model.setDepartmentName(entity.getDepartmentName());
        return model;
    }

    private void checkData(Department department, HttpServletResponse httpServletResponse) throws IOException {
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
