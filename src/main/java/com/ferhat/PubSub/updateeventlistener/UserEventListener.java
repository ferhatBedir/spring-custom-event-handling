package com.ferhat.PubSub.updateeventlistener;

import com.ferhat.PubSub.entity.Department;
import com.ferhat.PubSub.entity.User;
import com.ferhat.PubSub.publisher.UserEvent;
import com.ferhat.PubSub.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener implements ApplicationListener<UserEvent> {


    private DepartmentRepository departmentRepository;

    @Autowired
    public UserEventListener(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void onApplicationEvent(UserEvent event) {
        User user = event.getUser();
        updateUser(user);
    }

    private void updateUser(User user) {
        if (user == null) {
            System.out.println("User is null or empty.");
        } else {
            Department department = departmentRepository.findByUserUserId(user.getUserId());
            if (department == null) {
                System.out.println("Department is null or empty.");
            } else {
                department.setUser(user);
                departmentRepository.save(department);
                System.out.println("User updated in Department.");
            }
        }
    }
}

