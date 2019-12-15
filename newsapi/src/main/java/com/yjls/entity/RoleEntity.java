package com.yjls.entity;


import java.io.Serializable;
import java.util.List;

public class RoleEntity implements Serializable {
    private Integer rid;//角色id
    private String rname;//角色名
    private String rdesc;//角色备注
    private Integer rstatus = 1;//角色状态

    private Integer pageNum = 1;
    private Integer pageSize = 3;

    private List<AuthEntity> authlist;

    public RoleEntity() {
    }

    public RoleEntity(Integer rid, String rname, String rdesc, Integer rstatus, Integer pageNum, Integer pageSize, List<AuthEntity> authlist) {
        this.rid = rid;
        this.rname = rname;
        this.rdesc = rdesc;
        this.rstatus = rstatus;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.authlist = authlist;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public Integer getRstatus() {
        return rstatus;
    }

    public void setRstatus(Integer rstatus) {
        this.rstatus = rstatus;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<AuthEntity> getAuthlist() {
        return authlist;
    }

    public void setAuthlist(List<AuthEntity> authlist) {
        this.authlist = authlist;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", rdesc='" + rdesc + '\'' +
                ", rstatus=" + rstatus +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", authlist=" + authlist +
                '}';
    }
}