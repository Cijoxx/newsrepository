package com.yjls.mapper;


import com.github.pagehelper.PageInfo;
import com.yjls.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CommentMapper {

    //根据新闻id查询评论
    public List<CommentEntity> queryCommentByNewsId(Integer showid);

    //用户添加品论
    public boolean addComment(CommentEntity commentEntity);

    //管理员查看新闻及评论
    public List<CommentEntity> adminSeeNewsandCon();

    //删除一条新闻评论
    public  boolean  delcomment(int commentid);

    //批量删除新闻评论
    public  boolean delmanycomment(String ids);

    //评论成功 给评论数加一
    public boolean upConmment(int showid);

    //根据新闻id或者评论id查询
    public List<CommentEntity> searchByuseridOrshowid(CommentEntity commentEntity);

}
