package com.yjls.entity;


import java.io.Serializable;
import java.util.List;

public class RoleEntity implements Serializable {
    private Integer rid;
    private String rname;
    private String rdesc;

    private List<AuthEntity> authlist;

    public RoleEntity() {
    }

    public RoleEntity(Integer rid, String rname, String rdesc, List<AuthEntity> authlist) {
        this.rid = rid;
        this.rname = rname;
        this.rdesc = rdesc;
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
                ", authlist=" + authlist +
                '}';
    }
}