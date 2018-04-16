package com.hbf.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by haobingfu on 2018/4/16.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "list.shtml")
    public String list(Model model, HttpServletRequest request){
        model.addAttribute("list",roleService.list());
        return "sys/role_list";
    }

}
