package com.hbf.sys;

import com.hbf.jpa.BaseDao;
import com.hbf.jpa.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by haobingfu on 2018/4/16.
 */
@Component
@Transactional
public class RoleService extends BaseService<Role>{

    @Autowired
    private RoleDao roleDao;

    @Override
    protected BaseDao<Role, String> getBaseDao() {
        return roleDao;
    }

    @Override
    protected void BaseSaveCheck(Role obj) {

    }

    public List<Role> list(){
        return this.roleDao.findAll();
    }

    public Page<Role> pageList(Pageable pageable){
        return roleDao.findAll(pageable);
    }

}
