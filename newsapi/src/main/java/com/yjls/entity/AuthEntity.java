package com.yjls.entity;

import java.io.Serializable;

public class AuthEntity implements Serializable {
    private Integer auid;
    private String auname;
    private String aupath;
    private Integer autype;
    private Integer aupid;

    public AuthEntity() {
    }

    public AuthEntity(Integer auid, String auname, String aupath, Integer autype, Integer aupid) {
        this.auid = auid;
        this.auname = auname;
        this.aupath = aupath;
        this.autype = autype;
        this.aupid = aupid;
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

    @Override
    public String toString() {
        return "AuthEntity{" +
                "auid=" + auid +
                ", auname='" + auname + '\'' +
                ", aupath='" + aupath + '\'' +
                ", autype=" + autype +
                ", aupid=" + aupid +
                '}';
    }
}
