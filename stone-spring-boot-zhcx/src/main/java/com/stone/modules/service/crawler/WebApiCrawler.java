package com.stone.modules.service.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 扫描web页面接口
 *
 * @author Stone
 * @since 2020年9月22日 14:31:23
 */
public class WebApiCrawler extends BreadthCrawler {

    public WebApiCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        // TODO Auto-generated method stub

    }
}
