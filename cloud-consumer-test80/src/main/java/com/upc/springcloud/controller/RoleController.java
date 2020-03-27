package com.upc.springcloud.controller;

import com.upc.springcloud.entity.CommonResult;
import com.upc.springcloud.entity.Role;
import org.apache.catalina.mbeans.RoleMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RoleController {
    @Autowired
    RestTemplate restTemplate;

    private static final String BASR_URL = "http://CLOUD-TEST-SERVICE";

    @GetMapping(value = "/role")
    public CommonResult<Role> getRole() {
        return restTemplate.getForObject(BASR_URL+"/role/1",CommonResult.class);
    }

}
