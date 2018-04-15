package com.hbf.sysBase;

import com.hbf.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@MappedSuperclass
public class PurviewBase extends BaseEntity {

    private   String spurno; //权限编号 String
    private   String spurcode; //权限码 String
    private   String spurname; //权限名称 String
    private   String sparentname; //父菜单名称 String
    private   String sparentid; //父菜单ID String
    private   String surlpath; //访问路径 String
    private   String sdescription; //权限说明 String
    private   String sjpname; //简拼名 String
    private   String spyname; //全拼名 String
    private   Date dadddate; //添加日期 Date
    /**
     权限编号 String
     */
    @Column(name="spurno",length=50)
    public String getSpurno() {
        return  spurno;//权限编号 String
    }
    /**
     权限编号 String
     */
    public void setSpurno(String  spurno) {
        this.spurno =  spurno;//权限编号 String
    }
    /**
     权限码 String
     */
    @Column(name="spurcode",length=50)
    public String getSpurcode() {
        return  spurcode;//权限码 String
    }
    /**
     权限码 String
     */
    public void setSpurcode(String  spurcode) {
        this.spurcode =  spurcode;//权限码 String
    }
    /**
     权限名称 String
     */
    @Column(name="spurname",length=50)
    public String getSpurname() {
        return  spurname;//权限名称 String
    }
    /**
     权限名称 String
     */
    public void setSpurname(String  spurname) {
        this.spurname =  spurname;//权限名称 String
    }
    /**
     父菜单名称 String
     */
    @Column(name="sparentname",length=50)
    public String getSparentname() {
        return  sparentname;//父菜单名称 String
    }
    /**
     父菜单名称 String
     */
    public void setSparentname(String  sparentname) {
        this.sparentname =  sparentname;//父菜单名称 String
    }
    /**
     父菜单ID String
     */
    @Column(name="sparentid",length=32)
    public String getSparentid() {
        return  sparentid;//父菜单ID String
    }
    /**
     父菜单ID String
     */
    public void setSparentid(String  sparentid) {
        this.sparentid =  sparentid;//父菜单ID String
    }
    /**
     访问路径 String
     */
    @Column(name="surlpath",length=300)
    public String getSurlpath() {
        return  surlpath;//访问路径 String
    }
    /**
     访问路径 String
     */
    public void setSurlpath(String  surlpath) {
        this.surlpath =  surlpath;//访问路径 String
    }
    /**
     权限说明 String
     */
    @Column(name="sdescription",length=500)
    public String getSdescription() {
        return  sdescription;//权限说明 String
    }
    /**
     权限说明 String
     */
    public void setSdescription(String  sdescription) {
        this.sdescription =  sdescription;//权限说明 String
    }
    /**
     简拼名 String
     */
    @Column(name="sjpname",length=50)
    public String getSjpname() {
        return  sjpname;//简拼名 String
    }
    /**
     简拼名 String
     */
    public void setSjpname(String  sjpname) {
        this.sjpname =  sjpname;//简拼名 String
    }
    /**
     全拼名 String
     */
    @Column(name="spyname",length=100)
    public String getSpyname() {
        return  spyname;//全拼名 String
    }
    /**
     全拼名 String
     */
    public void setSpyname(String  spyname) {
        this.spyname =  spyname;//全拼名 String
    }
    /**
     添加日期 Date
     */
    @Column(name="dadddate")
    public Date getDadddate() {
        return  dadddate;//添加日期 Date
    }
    /**
     添加日期 Date
     */
    public void setDadddate(Date  dadddate) {
        this.dadddate =  dadddate;//添加日期 Date
    }
}
