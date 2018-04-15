package com.hbf.sys;

import com.hbf.sysBase.RoleBase;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by haobingfu on 2018/4/15.
 */
@Entity
@Table(name = "sys_role")
public class Role extends RoleBase{
    private static final long serialVersionUID = 1L;
}
