package com.hbf.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hbf.sysBase.MenuBase;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "sys_menu")
public class Menu extends MenuBase{

    private Menu parent;	// 父级菜单

    @Transient
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @JsonIgnore
    public static String getRootId(){
        return "0";
    }


    @JsonIgnore
    public static void sortList(List<Menu> list, List<Menu> sourcelist, String parentId, boolean cascade){
        for (int i=0; i<sourcelist.size(); i++){
            Menu e = sourcelist.get(i);
            if (e.getSparentid()!=null&& StringUtils.isNotBlank(e.getSparentid().trim())
                    && e.getSparentid().equals(parentId)){
                list.add(e);
                if (cascade){
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j=0; j<sourcelist.size(); j++){
                        Menu child = sourcelist.get(j);
                        if (child.getSparentid()!=null
                                && child.getSparentid().equals(e.getId())){
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
