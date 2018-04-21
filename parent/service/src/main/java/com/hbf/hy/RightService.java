package com.hbf.hy;

import com.hbf.jpa.BaseDao;
import com.hbf.jpa.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@org.springframework.transaction.annotation.Transactional
public class RightService extends BaseService<Right> {

    @Autowired
    private RightDao rightDao;

    @Override
    protected BaseDao<Right, String> getBaseDao() {
        return rightDao;
    }

    @Override
    protected void BaseSaveCheck(Right obj) {

    }

}
