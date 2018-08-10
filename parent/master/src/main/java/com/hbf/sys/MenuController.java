package com.hbf.sys;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hbf.jpa.BaseService;
import com.hbf.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by haobingfu on 2018/8/5.
 */

@Controller
@RequestMapping(value = "/sys/menu")
public class MenuController extends BaseController<Menu,Menu>{

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
     * 菜单树
     * @param extId
     * @param isShowHide
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "treeData.shtml")
    public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String isShowHide, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Menu> list = menuService.getAllMenu();
        for (int i=0; i<list.size(); i++){
            Menu e = list.get(i);
            if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()))){
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getSparentid());
                map.put("name", e.getSname());
                mapList.add(map);
            }
        }
        return mapList;
    }
}
