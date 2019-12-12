package com.yjls.service;

import com.github.pagehelper.PageInfo;
import com.yjls.entity.NewsEntity;

import java.util.List;

public interface NewsService {

    //新闻展示
    public List<NewsEntity> listNews(NewsEntity newsEntity);

    /*新闻按浏览量展示*/
    public List<NewsEntity> listbyviews();

    /*新闻按时间展示4条*/
    public List<NewsEntity> listbytime(Integer newsRows);

    /*根据新闻id查询信息详细信息*/
    public NewsEntity searchNewsById(Integer showid);

    //管理员查看新闻
    public PageInfo<NewsEntity> getnews(NewsEntity newsEntity);

    //管理员查看新闻详情
    public NewsEntity getnewsdetail(Integer showid);

    //管理员根据新闻id删除新闻
    public boolean delnews(Integer showid);

    //管理员根据新闻id批量删除新闻
    public boolean delnewsbyids(String idstr);

    //管理员审核新闻通过
    public boolean checknewspass(Integer showid);

    //管理员审核新闻未通过
    public boolean checknewsfail(Integer showid);

    //管理员恢复新闻
    public boolean makenewslive(Integer showid);

    //新闻点赞
    public boolean givegood(Integer showid);

    //新闻浏览量
    public boolean views(Integer showid);
}
