package com.hbf.sys;

import com.hbf.jpa.BaseService;
import com.hbf.persistence.PageUtils;
import com.hbf.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by haobingfu on 2018/4/16.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController<Role,Role> {

    @Autowired
    private RoleService roleService;

    @Override
    protected BaseService<Role> getBaseService() {
        return roleService;
    }

    @Override
    protected String getPackName() {
        return "sys";
    }

    @Override
    protected String getObjectName() {
        return "role-list";
    }


    @RequestMapping(value = "index.shtml")
    public String list(@RequestParam(value = "pageNo",defaultValue = "0") int pageNo,
                       @RequestParam(value = "limit",defaultValue = "1") int limit,
                       @RequestParam(value = "sort",defaultValue = "") String sort,
            Model model, HttpServletRequest request){

        Pageable page = new PageRequest(pageNo,limit,new Sort(Sort.Direction.DESC,"srolename"));
        Page<Role> page1 = roleService.pageList(page);

        model.addAttribute("list",page1);
        return getPackName() +"/"+ getObjectName();
    }
}
