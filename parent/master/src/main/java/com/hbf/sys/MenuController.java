package com.hbf.sys;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hbf.exception.ServiceException;
import com.hbf.ext.ExtResult;
import com.hbf.jpa.BaseService;
import com.hbf.web.BaseController;
import com.hbf.web.Layer;
import com.hbf.web.Msg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by haobingfu on 2018/8/5.
 */

@Controller
@RequestMapping(value = "/sys/menu")
public class MenuController extends BaseController<Menu, Menu> {

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

    /**
     * 菜单列表
     *
     * @param model
     * @return
     */
//    @RequiresPermissions("menu:list")
    @RequestMapping(value = "index.shtml")
    public String list(Model model) {
        List<Menu> list = Lists.newArrayList();
        List<Menu> sourcelist = menuService.getAllMenu();
        Menu.sortList(list, sourcelist, Menu.getRootId(), true);
        model.addAttribute("list", list);
        return getPackName() + "/" + getObjectName();
    }

    /**
     * 编辑和查看
     *
     * @param menu
     * @param model
     * @return
     */
    @RequestMapping(value = "form.shtml")
    public String form(Menu menu, Model model) {
        // 获取排序号，最末节点排序号+30
        if (!StringUtils.isBlank(menu.getId())) {
            menu = menuService.getMenuById(menu.getId());
            if (menu != null) {
                menu.setParent(menuService.getMenuById(menu.getSparentid()));
            }
        } else {
            if (menu.getSparentid() != null) {//增加下级菜单
                menu.setParent(menuService.getMenuById(menu.getSparentid()));
            } else {//增加新菜单
                menu.setParent(menuService.getMenuById("0"));
            }

        }
        model.addAttribute("menu", menu);
        return getPackName() + "/" + getObjectName() + "-detail";
    }


    //    @RequiresPermissions("menu:edit")
    @RequestMapping(value = "save.shtml")
    public String save(@Valid Menu menu, Model model, RedirectAttributes redirectAttributes) throws Exception {

        try {
            if (menuService.isNameSame(menu.getSname(), menu.getParent().getId(), menu.getId())) {
                Msg.error(redirectAttributes, "保存失败，菜单名称已存在！");
            }
            menu.setSparentid(menu.getParent().getId());// 添加父节点
            // 如果id为空就就添加
            if (StringUtils.isBlank(menu.getId())) {
                menuService.saveMenu(menu, null);
            } else {// 修改
                menuService.saveMenu(menu, menu.getId());
            }
            Msg.success(model, "菜单保存成功！", 1);
            return Layer.close();
        } catch (Exception ex) {
            Msg.error(model, ex.getMessage());
            model.addAttribute("menu", menu);
            return getPackName() + "/" + getObjectName() + "-detail";
        }

    }

    /**
     * 菜单树
     *
     * @param extId
     * @param isShowHide
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "treeData.shtml")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String isShowHide, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Menu> list = menuService.getAllMenu();
        for (int i = 0; i < list.size(); i++) {
            Menu e = list.get(i);
            if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()))) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getSparentid());
                map.put("name", e.getSname());
                mapList.add(map);
            }
        }
        return mapList;
    }


    /**
     * 删除菜单包括子菜单
     *
     * @param ids
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "delete.json")
    @ResponseBody
    public ExtResult doDelete(String[] ids, RedirectAttributes redirectAttributes) {
        try {
            menuService.deleteMune(ids);
            Msg.error(redirectAttributes, "删除菜单成功");
            return ExtResult.success("删除菜单成功");
        } catch (ServiceException ex) {
            ex.printStackTrace();
            Msg.error(redirectAttributes, ex.getMessage());
            return ExtResult.failure(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Msg.error(redirectAttributes, e.getMessage());
            return ExtResult.failure("系统错误，请联系管理员");
        }
    }

}
