package com.yjls.entity;

import java.io.Serializable;
import java.util.Date;

public class NewsEntity implements Serializable {

    private Integer showid;
    private String showtitle;
    private String showpicture;
    private String showcontent;
    private Integer showtype;
    private Date showtime;
    private Integer showviews;
    private Integer showgivegood;
    private Integer showcomment;
    private Integer showstatus;
    private Integer userid;

    private Integer pageNum = 1;
    private Integer pageSize = 12;

    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public NewsEntity() {
    }

    public NewsEntity(Integer showid, String showtitle, String showpicture, String showcontent, Integer showtype, Date showtime, Integer showviews, Integer showgivegood, Integer showcomment, Integer showstatus, Integer userid, Integer pageNum, Integer pageSize) {
        this.showid = showid;
        this.showtitle = showtitle;
        this.showpicture = showpicture;
        this.showcontent = showcontent;
        this.showtype = showtype;
        this.showtime = showtime;
        this.showviews = showviews;
        this.showgivegood = showgivegood;
        this.showcomment = showcomment;
        this.showstatus = showstatus;
        this.userid = userid;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getShowid() {
        return showid;
    }

    public void setShowid(Integer showid) {
        this.showid = showid;
    }

    public String getShowtitle() {
        return showtitle;
    }

    public void setShowtitle(String showtitle) {
        this.showtitle = showtitle;
    }

    public String getShowpicture() {
        return showpicture;
    }

    public void setShowpicture(String showpicture) {
        this.showpicture = showpicture;
    }

    public String getShowcontent() {
        return showcontent;
    }

    public void setShowcontent(String showcontent) {
        this.showcontent = showcontent;
    }

    public Integer getShowtype() {
        return showtype;
    }

    public void setShowtype(Integer showtype) {
        this.showtype = showtype;
    }

    public Date getShowtime() {
        return showtime;
    }

    public void setShowtime(Date showtime) {
        this.showtime = showtime;
    }

    public Integer getShowviews() {
        return showviews;
    }

    public void setShowviews(Integer showviews) {
        this.showviews = showviews;
    }

    public Integer getShowgivegood() {
        return showgivegood;
    }

    public void setShowgivegood(Integer showgivegood) {
        this.showgivegood = showgivegood;
    }

    public Integer getShowcomment() {
        return showcomment;
    }

    public void setShowcomment(Integer showcomment) {
        this.showcomment = showcomment;
    }

    public Integer getShowstatus() {
        return showstatus;
    }

    public void setShowstatus(Integer showstatus) {
        this.showstatus = showstatus;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    @Override
    public String toString() {
        return "NewsEntity{" +
                "showid=" + showid +
                ", showtitle='" + showtitle + '\'' +
                ", showpicture='" + showpicture + '\'' +
                ", showcontent='" + showcontent + '\'' +
                ", showtype=" + showtype +
                ", showtime=" + showtime +
                ", showviews=" + showviews +
                ", showgivegood=" + showgivegood +
                ", showcomment=" + showcomment +
                ", showstatus=" + showstatus +
                ", userid=" + userid +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", user=" + user +
                '}';
    }
}
