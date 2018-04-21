package com.hbf.web;

import com.hbf.jpa.BaseService;
import com.hbf.persistence.PageUtils;
import com.hbf.utils.B;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.validation.Validator;
import java.util.Map;


/**
 * 控制器支持类
 * @author haobingfu
 * @version
 */
public abstract class BaseController<T,V> {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

    protected abstract BaseService<T> getBaseService();

    protected abstract String getPackName();

    protected abstract String getObjectName();

    public String getDefaultSort(){
        return "";
    }

    /**
     * list集合
     * @param start
     * @param limit
     * @param sort
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list.shtml")
    protected String dolist(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "limit", defaultValue = PageUtils.Limit) int limit,
            @RequestParam(value = "sort", defaultValue = "") String sort,Model model,
            ServletRequest request) {
        if(B.Y(sort))
            sort = getDefaultSort();
        checkPermissionQuery(request);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, model);
        model.addAttribute("list", list(start, limit, sort, searchParams));
        return getPackName()+"/"+getObjectName();
    }

    /**
     * 默认查询权限
     * @param request
     */
    protected void checkPermissionQuery(ServletRequest request) {
        SecurityUtils.getSubject().checkPermission(
                this.getObjectName() + ":" + "query");
    }

    public Page<T> list(int start, int limit, String sort, Map<String,Object> searchParams) {
        String[] s=new String[]{""};
        if(StringUtils.isNotEmpty(sort)){
            s=sort.split("\\,");
        }
        PageRequest page = PageUtils.page(start, limit,s);

        return getBaseService().listPage(page, searchParams);

    }

}
