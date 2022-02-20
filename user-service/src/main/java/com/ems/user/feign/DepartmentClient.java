package com.ems.user.feign;

import com.ems.user.VO.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "DEPARTMENT-SERVICE",
        path = "/departments")
public interface DepartmentClient {

    @GetMapping("/{id}")
    public Department getById(@PathVariable long id);

}
