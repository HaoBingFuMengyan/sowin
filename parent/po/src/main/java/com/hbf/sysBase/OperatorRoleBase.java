package com.hbf.sysBase;

import com.hbf.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by haobingfu on 2018/4/15.
 */
@MappedSuperclass
public class OperatorRoleBase extends BaseEntity {
    private   String soperatorid; //操作员ID String
    private   String sroleid; //角色ID String
    /**
     操作员ID String
     */
    @Column(name="soperatorid",length=32)
    public String getSoperatorid() {
        return  soperatorid;//操作员ID String
    }
    /**
     操作员ID String
     */
    public void setSoperatorid(String  soperatorid) {
        this.soperatorid =  soperatorid;//操作员ID String
    }
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
}
