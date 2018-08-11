package com.hbf.sys;

import com.hbf.jpa.BaseDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MenuDao extends BaseDao<Menu,String> {

    @Query("select p from  Menu p where p.sparentid = ?1 ")
    List<Menu> getMenuByParentId(String parentid, Sort sort);

    @Query("select count(*) from Menu m where m.sname=?1 and m.sparentid=?2 ")
    long count(String sname, String sparentid);

    @Query("select count(*) from Menu m where m.sname=?1 and m.sparentid=?2 and m.id=?3")
    long count(String sname, String sparentid, String id);



}
