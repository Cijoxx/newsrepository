package com.yjls.entity;

import java.io.Serializable;
import java.util.List;

public class UserEntity implements Serializable {
    private Integer userid;
    private String username;
    private String userpass;
    private String useraccount;
    private Integer userrole;
    private String userphoto;
    private Integer userstatus = 1;
    private String applyrealname;
    private String applyidcard;
    private String applyreason;

    private Integer pageNum = 1;
    private Integer pageSize = 4;

    private RoleEntity role;

    public UserEntity() {
    }

    public UserEntity(Integer userid, String username, String userpass, String useraccount, Integer userrole, String userphoto, Integer userstatus, String applyrealname, String applyidcard, String applyreason, Integer pageNum, Integer pageSize, RoleEntity role) {
        this.userid = userid;
        this.username = username;
        this.userpass = userpass;
        this.useraccount = useraccount;
        this.userrole = userrole;
        this.userphoto = userphoto;
        this.userstatus = userstatus;
        this.applyrealname = applyrealname;
        this.applyidcard = applyidcard;
        this.applyreason = applyreason;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.role = role;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public Integer getUserrole() {
        return userrole;
    }

    public void setUserrole(Integer userrole) {
        this.userrole = userrole;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public String getApplyrealname() {
        return applyrealname;
    }

    public void setApplyrealname(String applyrealname) {
        this.applyrealname = applyrealname;
    }

    public String getApplyidcard() {
        return applyidcard;
    }

    public void setApplyidcard(String applyidcard) {
        this.applyidcard = applyidcard;
    }

    public String getApplyreason() {
        return applyreason;
    }

    public void setApplyreason(String applyreason) {
        this.applyreason = applyreason;
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

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", useraccount='" + useraccount + '\'' +
                ", userrole=" + userrole +
                ", userphoto='" + userphoto + '\'' +
                ", userstatus=" + userstatus +
                ", applyrealname='" + applyrealname + '\'' +
                ", applyidcard=" + applyidcard +
                ", applyreason='" + applyreason + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", role=" + role +
                '}';
    }
}
