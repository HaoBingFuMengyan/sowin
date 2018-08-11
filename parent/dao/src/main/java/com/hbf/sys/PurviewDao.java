package com.hbf.sys;

import com.hbf.jpa.BaseDao;

import java.util.List;

/**
 * Created by haobingfu on 2018/8/11.
 */
public interface PurviewDao extends BaseDao<Purview,String> {

    List<Purview> findBySparentid(String id);

}
