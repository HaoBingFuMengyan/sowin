package com.hbf.sys;

import com.hbf.jpa.BaseDao;
import com.hbf.jpa.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by haobingfu on 2018/8/5.
 */
@Component
@Transactional
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuDao menuDao;

    @Override
    protected BaseDao<Menu, String> getBaseDao() {
        return menuDao;
    }

    @Override
    protected void BaseSaveCheck(Menu obj) {

    }
}
