package com.stone.service;

import com.stone.bean.MessageDO;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/28 20:47
 */
public interface SolrBeanService extends SolrCrudRepository<MessageDO, String> {

}
