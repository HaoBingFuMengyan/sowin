package com.hbf.sysBase;

import com.hbf.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by haobingfu on 2018/4/15.
 */
@MappedSuperclass
public class RoleBase extends BaseEntity {
    private   String srolename; //角色名称 String
    private   String sremark; //备注 String
    /**
     角色名称 String
     */
    @Column(name="srolename",length=50)
    public String getSrolename() {
        return  srolename;//角色名称 String
    }
    /**
     角色名称 String
     */
    public void setSrolename(String  srolename) {
        this.srolename =  srolename;//角色名称 String
    }
    /**
     备注 String
     */
    @Column(name="sremark",length=2000)
    public String getSremark() {
        return  sremark;//备注 String
    }
    /**
     备注 String
     */
    public void setSremark(String  sremark) {
        this.sremark =  sremark;//备注 String
    }
}
