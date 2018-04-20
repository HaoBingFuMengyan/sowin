package com.hbf.sysBase;

import com.hbf.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class MenuBase extends BaseEntity {
    private   String smenuno; //菜单编号 String
    private   String sname; //菜单名称 String
    private   String stitle; //菜单标题 String
    private   Integer bisroot=0; //是否父节点 int
    private   Integer bisleaf=0; //是否叶子节点 int
    private   String sparentid; //父节点ID String
    private   String spurid; //权限ID String
    private   Long imenulevel; //菜单阶层 long
    private   String simagepath; //菜单图标 String
    private   String smenupath; //菜单路径 String
    private   Integer bisdisplay=0; //是否显示菜单 int
    private   String sremark; //备注 String
    private   Long isort; //排序编号 long
    private   Date dadddate; //添加日期 Date
    /**
     菜单编号 String
     */
    @Column(name="smenuno",length=50)
    public String getSmenuno() {
        return  smenuno;//菜单编号 String
    }
    /**
     菜单编号 String
     */
    public void setSmenuno(String  smenuno) {
        this.smenuno =  smenuno;//菜单编号 String
    }
    /**
     菜单名称 String
     */
    @Column(name="sname",length=50)
    public String getSname() {
        return  sname;//菜单名称 String
    }
    /**
     菜单名称 String
     */
    public void setSname(String  sname) {
        this.sname =  sname;//菜单名称 String
    }
    /**
     菜单标题 String
     */
    @Column(name="stitle",length=100)
    public String getStitle() {
        return  stitle;//菜单标题 String
    }
    /**
     菜单标题 String
     */
    public void setStitle(String  stitle) {
        this.stitle =  stitle;//菜单标题 String
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
     父节点ID String
     */
    @Column(name="sparentid",length=32)
    public String getSparentid() {
        return  sparentid;//父节点ID String
    }
    /**
     父节点ID String
     */
    public void setSparentid(String  sparentid) {
        this.sparentid =  sparentid;//父节点ID String
    }
    /**
     权限ID String
     */
    @Column(name="spurid",length=32)
    public String getSpurid() {
        return  spurid;//权限ID String
    }
    /**
     权限ID String
     */
    public void setSpurid(String  spurid) {
        this.spurid =  spurid;//权限ID String
    }
    /**
     菜单阶层 long
     */
    @Column(name="imenulevel")
    public Long getImenulevel() {
        return  imenulevel;//菜单阶层 long
    }
    /**
     菜单阶层 long
     */
    public void setImenulevel(Long  imenulevel) {
        this.imenulevel =  imenulevel;//菜单阶层 long
    }
    /**
     菜单图标 String
     */
    @Column(name="simagepath",length=300)
    public String getSimagepath() {
        return  simagepath;//菜单图标 String
    }
    /**
     菜单图标 String
     */
    public void setSimagepath(String  simagepath) {
        this.simagepath =  simagepath;//菜单图标 String
    }
    /**
     菜单路径 String
     */
    @Column(name="smenupath",length=300)
    public String getSmenupath() {
        return  smenupath;//菜单路径 String
    }
    /**
     菜单路径 String
     */
    public void setSmenupath(String  smenupath) {
        this.smenupath =  smenupath;//菜单路径 String
    }
    /**
     是否显示菜单 int
     BoolType:
     0:NO:否
     1:YES:是

     */
    @Column(name="bisdisplay")
    public Integer getBisdisplay() {
        return  bisdisplay;//是否显示菜单 int
    }
    /**
     是否显示菜单 int
     BoolType:
     0:NO:否
     1:YES:是

     */
    public void setBisdisplay(Integer  bisdisplay) {
        this.bisdisplay =  bisdisplay;//是否显示菜单 int
    }
    /**
     备注 String
     */
    @Column(name="sremark",length=500)
    public String getSremark() {
        return  sremark;//备注 String
    }
    /**
     备注 String
     */
    public void setSremark(String  sremark) {
        this.sremark =  sremark;//备注 String
    }
    /**
     排序编号 long
     */
    @Column(name="isort")
    public Long getIsort() {
        return  isort;//排序编号 long
    }
    /**
     排序编号 long
     */
    public void setIsort(Long  isort) {
        this.isort =  isort;//排序编号 long
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

