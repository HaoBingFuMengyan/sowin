package com.hbf.hyBase;

import com.hbf.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class RightBase extends BaseEntity {

    private   String spurno; //权限编号 String
    private   String spurcode; //权限码 String
    private   String spurname; //权限名称 String
    private   Integer isitetype=0; //分站 int
    private   Integer bisroot=0; //是否父节点 int
    private   Integer bisleaf=0; //是否叶子节点 int
    private   String sparentid; //父权限 String
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
     分站 int
     SiteType:
     0:MASTER:后台
     1:FRONT:前台
     */
    @Column(name="isitetype")
    public Integer getIsitetype() {
        return  isitetype;//分站 int
    }
    /**
     分站 int
     SiteType:
     0:MASTER:后台
     1:FRONT:前台
     */
    public void setIsitetype(Integer  isitetype) {
        this.isitetype =  isitetype;//分站 int
    }
    /**
     是否父节点 int
     BoolType:
     0:NO:否
     1:YES:是

     */
    @Column(name="bisroot")
    public Integer getBisroot() {
        return  bisroot;//是否父节点 int
    }
    /**
     是否父节点 int
     BoolType:
     0:NO:否
     1:YES:是

     */
    public void setBisroot(Integer  bisroot) {
        this.bisroot =  bisroot;//是否父节点 int
    }
    /**
     是否叶子节点 int
     BoolType:
     0:NO:否
     1:YES:是

     */
    @Column(name="bisleaf")
    public Integer getBisleaf() {
        return  bisleaf;//是否叶子节点 int
    }
    /**
     是否叶子节点 int
     BoolType:
     0:NO:否
     1:YES:是

     */
    public void setBisleaf(Integer  bisleaf) {
        this.bisleaf =  bisleaf;//是否叶子节点 int
    }
    /**
     父权限 String
     */
    @Column(name="sparentid",length=32)
    public String getSparentid() {
        return  sparentid;//父权限 String
    }
    /**
     父权限 String
     */
    public void setSparentid(String  sparentid) {
        this.sparentid =  sparentid;//父权限 String
    }
    /**
     访问路径 String
     */
    @Column(name="surlpath",length=1000)
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
    @Column(name="sdescription",length=2000)
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
