package com.upc.springcloud.controller;

import com.upc.springcloud.dao.RoleMapper;
import com.upc.springcloud.entity.CommonResult;
import com.upc.springcloud.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoleController {
    @Autowired
    RoleMapper roleMapper;

    @GetMapping(value = "/role/{uId}")
    public CommonResult<Role> selectRole(@PathVariable(value = "uId") Integer uId) {
        Role role = roleMapper.selectByPrimaryKey(uId);

        return new CommonResult(200,"成功8001", role);
    }
}
