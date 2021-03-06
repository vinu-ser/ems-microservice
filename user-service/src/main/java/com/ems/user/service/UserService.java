package com.ems.user.service;

import com.ems.user.VO.Department;
import com.ems.user.VO.ResponseTemplateVO;
import com.ems.user.entity.User;
import com.ems.user.feign.DepartmentClient;
import com.ems.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DepartmentClient departmentClient;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        /*Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                        ,Department.class);*/
        Department department = departmentClient.getById(userId);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
}
