package com.hbf.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by haobingfu on 2018/4/16.
 */
@Component
@Transactional
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> list(){
        return this.roleDao.findAll();
    }
}
