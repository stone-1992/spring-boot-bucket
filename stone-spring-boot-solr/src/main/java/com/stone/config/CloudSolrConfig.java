package com.stone.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/29 9:39
 */
@Slf4j
@Configuration
public class CloudSolrConfig {

    /**
     * solr集群地址（也可以改为zk地址）
     */
    @Value("${solr.cloud.zk-host}")
    public String SOLR_HOST;

    /**
     * 注入cloudSolrClient
     *
     * @return
     */
    @Bean
    public CloudSolrClient solrClient() {
        List<String> solrHosts = new ArrayList<String>();
        String[] zks = SOLR_HOST.split(",");
        for (int i = 0; i < zks.length; i++) {
            solrHosts.add(zks[i]);
        }
        log.info("solr zk 地址： {}", solrHosts);
        Optional<String> zkChroot = Optional.ofNullable(null);
        CloudSolrClient solrClient = new CloudSolrClient.Builder(solrHosts, zkChroot)
                .withConnectionTimeout(5000)
                .withSocketTimeout(5000)
                .build();
        //设置默认collection
        solrClient.setDefaultCollection("bp_collection");
        return solrClient;
    }
}
