package com.hbf.sys;

import com.hbf.jpa.BaseDao;
import com.hbf.jpa.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 查询所有菜单
     *
     * @return
     */
    @Transactional( readOnly = true)
    public List<Menu> getAllMenu() {
        return menuDao.findAll(new Sort(new Order(Sort.Direction.ASC, "isort")));
    }
}
