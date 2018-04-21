package com.hbf.hy;

import com.hbf.jpa.BaseService;
import com.hbf.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/right")
public class RightController extends BaseController<Right,Right> {

    @Autowired
    private RightService rightService;

    @Override
    protected BaseService<Right> getBaseService() {
        return rightService;
    }

    @Override
    protected String getPackName() {
        return "hy";
    }

    @Override
    protected String getObjectName() {
        return "right";
    }
}
