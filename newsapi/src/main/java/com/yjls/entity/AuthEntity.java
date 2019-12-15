package com.yjls.entity;

import java.io.Serializable;

public class AuthEntity implements Serializable {
    private Integer auid;//权限id
    private String auname;//权限名称
    private String aupath;//权限路径
    private Integer autype;//权限类型
    private Integer aupid;//父级权限id
    private boolean isChecked;


    public AuthEntity() {
    }


    public AuthEntity(Integer auid, String auname, String aupath, Integer autype, Integer aupid, boolean isChecked) {
        this.auid = auid;
        this.auname = auname;
        this.aupath = aupath;
        this.autype = autype;
        this.aupid = aupid;
        this.isChecked = isChecked;
    }

    public Integer getAuid() {
        return auid;
    }

    public void setAuid(Integer auid) {
        this.auid = auid;
    }

    public String getAuname() {
        return auname;
    }

    public void setAuname(String auname) {
        this.auname = auname;
    }

    public String getAupath() {
        return aupath;
    }

    public void setAupath(String aupath) {
        this.aupath = aupath;
    }

    public Integer getAutype() {
        return autype;
    }

    public void setAutype(Integer autype) {
        this.autype = autype;
    }

    public Integer getAupid() {
        return aupid;
    }

    public void setAupid(Integer aupid) {
        this.aupid = aupid;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "AuthEntity{" +
                "auid=" + auid +
                ", auname='" + auname + '\'' +
                ", aupath='" + aupath + '\'' +
                ", autype=" + autype +
                ", aupid=" + aupid +
                ", isChecked=" + isChecked +
                '}';
    }
}