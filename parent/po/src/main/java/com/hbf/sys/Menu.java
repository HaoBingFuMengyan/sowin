package com.hbf.sys;

import com.hbf.sysBase.MenuBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
}
