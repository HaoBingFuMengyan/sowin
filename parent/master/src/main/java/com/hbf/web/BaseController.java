package com.hbf.web;

import com.hbf.exception.ServiceException;
import com.hbf.ext.ExtResult;
import com.hbf.jpa.BaseService;
import com.hbf.persistence.PageUtils;
import com.hbf.utils.B;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Map;


/**
 * 控制器支持类
 *
 * @author haobingfu
 */
public abstract class BaseController<T, V> {
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

    public String getDefaultSort() {
        return "";
    }

    @ModelAttribute
    public void prepareSave(
            @RequestParam(value = "id", defaultValue = "") String id,
            Model model) {

        if (StringUtils.isNotBlank(id)) {
            T product = getBaseService().findOne(id);
            if (product != null) {
                try {
                    model.addAttribute("oriObj", BeanUtils.cloneBean(product));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                model.addAttribute("model", poToVo(product));
            }

        }
    }

    /**
     * list集合
     *
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
            @RequestParam(value = "sort", defaultValue = "") String sort, Model model,
            ServletRequest request) {
        if (B.Y(sort))
            sort = getDefaultSort();
//        checkPermissionQuery(request);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, model);
        model.addAttribute("list", list(start, limit, sort, searchParams));
        return getPackName() + "/" + getObjectName();
    }

    /**
     * 默认查询权限
     *
     * @param request
     */
    protected void checkPermissionQuery(ServletRequest request) {
        SecurityUtils.getSubject().checkPermission(
                this.getObjectName() + ":" + "query");
    }

    public Page<T> list(int start, int limit, String sort, Map<String, Object> searchParams) {
        String[] s = new String[]{""};
        if (StringUtils.isNotEmpty(sort)) {
            s = sort.split("\\,");
        }
        PageRequest page = PageUtils.page(start, limit, s);

        return getBaseService().listPage(page, searchParams);

    }

    /**
     * 用于增，改，查的详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "detail.shtml")
    public String detail(String id, Model model, ServletRequest request) {
        try {
            Map<String, Object> params = Servlets.getParametersStartingWith(
                    request, "param_");
            if (B.N(id)) {  //修改或查询
                model.addAttribute("data", getBaseService().findOne(id));
            } else {//新增
                model.addAttribute("data", this.getBaseService().getDomainClass().newInstance());
            }
            model.addAllAttributes(params);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.error(model, "操作失败");
        }
        return getPackName() + "/" + getObjectName() + "-detail";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "dosave.shtml")
    protected String dosave(
            @RequestParam(value = "id", defaultValue = "") String id, Model model, RedirectAttributes rmodel,
            @Valid @ModelAttribute("model") V obj, ServletRequest reqeust) {

        return save(id, model, rmodel, obj, reqeust);
    }

    public String save(String id, Model model, RedirectAttributes rmodel, V obj, ServletRequest request) {
        String r = "";
        try {

            if (B.Y(id))
                r = this.getObjectName() + ":" + "add";
            if (B.N(id))
                r = this.getObjectName() + ":" + "edit";
//            SecurityUtils.getSubject().checkPermission(r);
            getBaseService().BaseSave(voToPo(obj), id);
            Msg.success(model, "保存成功", 1);
            return Layer.close();
        } catch (ServiceException ex) {
            Msg.error(model, ex.getMessage());
        } catch (AuthorizationException ex) {
            Msg.error(model, "您没有权限:" + r);
        } catch (Exception ex) {
            ex.printStackTrace();
            Msg.error(model, "操作出错，请重试或联系管理员");
        }
        model.addAttribute("data", obj);
        return getPackName() + "/" + getObjectName() + "-detail";
    }

    /**
     * Async删除
     */
    @RequestMapping(value = "delete.json")
    @ResponseBody
    protected ExtResult dodelete(
            @RequestParam(value = "ids") String[] ids)
            throws Exception {

        return delete(ids);
    }

    public ExtResult delete(String[] ids) {
        try {
            //检查是否有删除权限
//            SecurityUtils.getSubject().checkPermission(
//                    this.getObjectName() + ":" + "delete");
            getBaseService().BaseDelete(ids);
            return ExtResult.success("删除成功！");
        } catch (ServiceException ex) {
            ex.printStackTrace();
            return ExtResult.failure(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ExtResult.failure("操作出错，请重试或联系管理员");
        }
    }


    @SuppressWarnings("unchecked")
    private V poToVo(T obj) {
        return (V) obj;
    }

    @SuppressWarnings("unchecked")
    protected T voToPo(V obj) {
        return (T) obj;
    }

}
