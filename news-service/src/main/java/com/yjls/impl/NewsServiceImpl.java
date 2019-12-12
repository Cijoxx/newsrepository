package com.yjls.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjls.entity.NewsEntity;
import com.yjls.mapper.NewsMapper;
import com.yjls.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<NewsEntity> listNews(NewsEntity newsEntity) {
        return newsMapper.listNews(newsEntity);
    }

    /*新闻按浏览量展示*/
    @Override
    public List<NewsEntity> listbyviews() {
        return newsMapper.listbyviews();
    }

    /*新闻按发布时间展示*/
    @Override
    public List<NewsEntity> listbytime(Integer newsRows) {
        return newsMapper.listbytime(newsRows);
    }

    /*根据新闻id查询信息详细信息*/
    public NewsEntity searchNewsById(Integer showid){
        return newsMapper.searchNewsById(showid);
    }

    //管理员查看新闻
    @Override
    public PageInfo<NewsEntity> getnews(NewsEntity newsEntity) {
        PageHelper.startPage(newsEntity.getPageNum(),newsEntity.getPageSize());
        List<NewsEntity> list=newsMapper.getnews(newsEntity);
        PageInfo<NewsEntity> newsEntityPageInfo = new PageInfo<>(list);
        return newsEntityPageInfo;
    }

    //管理员查看新闻详情
    @Override
    public NewsEntity getnewsdetail(Integer showid) {
        return newsMapper.getnewsdetail(showid);
    }

    //管理员根据新闻id删除新闻
    public boolean delnews(Integer showid) {
        String str = "(" + showid + ")";
        return newsMapper.delnews(str);
    }

    //管理员批量删除
    @Override
    public boolean delnewsbyids(String ids) {
        String str = "("+ids+")";
        return newsMapper.delnews(str);
    }

    //管理员审核新闻通过
    @Override
    public boolean checknewspass(Integer showid) {
        return newsMapper.checknewspass(showid);
    }

    //管理员审核新闻未通过
    @Override
    public boolean checknewsfail(Integer showid) {
        return newsMapper.checknewsfail(showid);
    }

    //管理员恢复新闻
    @Override
    public boolean makenewslive(Integer showid) {
        return newsMapper.makenewslive(showid);
    }

    //新闻点赞
    public boolean givegood(Integer showid){
        return newsMapper.givegood(showid);
    }

    //新闻浏览量
    @Override
    public boolean views(Integer showid) {
        return newsMapper.views(showid);
    }

}
