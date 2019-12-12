package com.yjls.entity;

import java.io.Serializable;
import java.util.Date;

public class CommentEntity implements Serializable {
    private Integer commentid;
    private Date commenttime;
    private String commentcontent;
    private Integer showid;
    private Integer userid;

    //存储用户信息
    private UserEntity userEntity;

    private NewsEntity newsEntity;
    private  int PageNum=1;
    private  int PageSize=10;


    public CommentEntity() {
    }

    public CommentEntity(Integer commentid, Date commenttime, String commentcontent, Integer showid, Integer userid, UserEntity userEntity, NewsEntity newsEntity, int pageNum, int pageSize) {
        this.commentid = commentid;
        this.commenttime = commenttime;
        this.commentcontent = commentcontent;
        this.showid = showid;
        this.userid = userid;
        this.userEntity = userEntity;
        this.newsEntity = newsEntity;
        PageNum = pageNum;
        PageSize = pageSize;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public Integer getShowid() {
        return showid;
    }

    public void setShowid(Integer showid) {
        this.showid = showid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public NewsEntity getNewsEntity() {
        return newsEntity;
    }

    public void setNewsEntity(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int pageNum) {
        PageNum = pageNum;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "commentid=" + commentid +
                ", commenttime=" + commenttime +
                ", commentcontent='" + commentcontent + '\'' +
                ", showid=" + showid +
                ", userid=" + userid +
                ", userEntity=" + userEntity +
                ", newsEntity=" + newsEntity +
                ", PageNum=" + PageNum +
                ", PageSize=" + PageSize +
                '}';
    }
}
