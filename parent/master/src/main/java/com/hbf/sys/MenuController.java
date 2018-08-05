package com.hbf.sys;

import com.hbf.jpa.BaseService;
import com.hbf.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haobingfu on 2018/8/5.
 */

@Controller
@RequestMapping(value = "/sys/menu")
public class MenuController extends BaseController<Menu,String>{

    @Autowired
    private MenuService menuService;

    @Override
    protected BaseService<Menu> getBaseService() {
        return menuService;
    }

    @Override
    protected String getPackName() {
        return "sys";
    }

    @Override
    protected String getObjectName() {
        return "menu-list";
    }


}
