package com.stone.controller;

import cn.hutool.core.util.StrUtil;
import com.stone.bean.MessageDO;
import com.stone.service.SolrBeanService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/28 20:07
 */
@RestController
@RequestMapping(value = "solr")
public class SolrController {

    @Autowired
    SolrBeanService solrBeanService;

    @Autowired
    CloudSolrClient cloudSolrClient;

    @GetMapping("")
    public List<MessageDO> testSolr(@RequestParam(value = "noticeMessage") String noticeMessage,
                                    @RequestParam(value = "noticeBigType") Integer noticeBigType,
                                    @RequestParam(value = "pageNo") Integer pageNo,
                                    @RequestParam(value = "pageSize") Integer pageSize) throws IOException, SolrServerException {

        SolrQuery solrQuery = new SolrQuery();
        if (StrUtil.isNotBlank(noticeMessage)) {
            solrQuery.set("q", noticeMessage);
        } else {
            solrQuery.set("q", "*");
        }
        if (Objects.nonNull(noticeBigType)) {
            solrQuery.set("q", "noticeBigType:" + noticeBigType);
        }
        solrQuery.setSort("creatTime", SolrQuery.ORDER.desc);
        solrQuery.setStart((pageNo - 1) * pageSize);
        solrQuery.setRows(pageSize);
        solrQuery.set("df", "noticeMessage");

        // solrQuery.set("q", "lineId : 7 or lineId : 8");

        QueryResponse query = cloudSolrClient.query(solrQuery);

        long numFound = query.getResults().getNumFound();
        System.err.println("记录总条数：" + numFound);

        System.err.println(query);
        return query.getBeans(MessageDO.class);
    }

    @PostMapping
    public void addSolr(@RequestBody MessageDO messageSearch) {
        try {
            messageSearch.setCreator(1001L);
            messageSearch.setCreatTime(new Date());
            messageSearch.setModifier(1001L);
            messageSearch.setModifyTime(new Date());
            cloudSolrClient.addBean(messageSearch);
            cloudSolrClient.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

}
