package com.hbf.sysBase;

import com.hbf.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by haobingfu on 2018/4/15.
 */
@MappedSuperclass
public class RolePurviewBase extends BaseEntity {
    private   String sroleid; //角色ID String
    private   String spurviewid; //权限码ID String
    /**
     角色ID String
     */
    @Column(name="sroleid",length=32)
    public String getSroleid() {
        return  sroleid;//角色ID String
    }
    /**
     角色ID String
     */
    public void setSroleid(String  sroleid) {
        this.sroleid =  sroleid;//角色ID String
    }
    /**
     权限码ID String
     */
    @Column(name="spurviewid",length=32)
    public String getSpurviewid() {
        return  spurviewid;//权限码ID String
    }
    /**
     权限码ID String
     */
    public void setSpurviewid(String  spurviewid) {
        this.spurviewid =  spurviewid;//权限码ID String
    }
}
