package com.hbf.sys;

import com.hbf.jpa.BaseService;
import com.hbf.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haobingfu on 2018/4/16.
 */
@Controller
@RequestMapping(value = "/operator")
public class OperatorController extends BaseController<Operator,String>{

    @Autowired
    private OperatorService operatorService;

    @Override
    protected BaseService<Operator> getBaseService() {
        return operatorService;
    }

    @Override
    protected String getPackName() {
        return "";
    }

    @Override
    protected String getObjectName() {
        return "";
    }
}
