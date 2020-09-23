package com.stone.modules.service.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.hutool.core.io.FileUtil;
import com.stone.common.util.JsonParseUtils;
import com.stone.modules.domain.FunctionOperation;
import com.stone.modules.service.FunctionOperationService;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;

/**
 * swagger api接口爬取类
 *
 * @author Stone
 * @since 2020年9月22日 14:30:39
 */
@Slf4j
public class SwaggerApiCrawler extends BreadthCrawler {

    private Long appId;

    private FunctionOperationService functionOperationService;

    /**
     * 自增id设置初始值
     */
    private Integer seqId = 4300;

    private String preFuncSql = "insert into bp_function_operation (id, app_id, name, request_type, url, order_num, status, logic_del) values (";

    public SwaggerApiCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        setThreads(50);
        // 设置连接超时时间和读取超时时间
        this.getConf().setConnectTimeout(5000).setReadTimeout(5000);
        try {
            String url = "172.16.102.169:9020/" + crawlPath + "/v2/api-docs";
            log.info(url);
            CrawlDatum datum = new CrawlDatum(url).meta("url", url);
            addSeed(datum);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public SwaggerApiCrawler(String urlPrefix, String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        setThreads(50);
        // 设置连接超时时间和读取超时时间
        this.getConf().setConnectTimeout(5000).setReadTimeout(5000);
        try {
            String url = urlPrefix + crawlPath + "/v2/api-docs";
            log.info(url);
            CrawlDatum datum = new CrawlDatum(url).meta("url", url);
            addSeed(datum);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.meta("url");
        log.info("api接口地址:" + url);
        String html = page.html();
        try {
            List<FunctionOperation> apiResults = new ArrayList<>();
            Map<String, Object> ret = JsonParseUtils.json2Bean(html, Map.class);
            String basePath = ret.get("basePath").toString();
            LinkedHashMap<String, Object> paths = (LinkedHashMap<String, Object>) ret.get("paths");
            for (Entry<String, Object> entry : paths.entrySet()) {
                // 请求uri
                String uri = basePath + entry.getKey();
                LinkedHashMap<String, Object> values = (LinkedHashMap<String, Object>) entry.getValue();
                for (Entry<String, Object> value : values.entrySet()) {
                    // 请求类型
                    String methodType = value.getKey().toUpperCase();
                    LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) value.getValue();
                    // 请求描述
                    String summary = params.get("summary").toString();
                    FunctionOperation apiResult = new FunctionOperation();
                    apiResult.setAppId(appId);
                    apiResult.setUrl(uri);
                    apiResult.setRequestType(methodType);
                    apiResult.setName(summary);
                    apiResult.setCode(null);
                    apiResult.setOrderNum(99);
                    apiResults.add(apiResult);
                    log.info(uri + "---" + methodType + "---" + summary);
                }
            }
            StringBuffer sql = new StringBuffer();
            log.info("去重前接口个数：{}", apiResults != null ? apiResults.size() : 0);
            // 判断是否存在api
            for (Iterator<FunctionOperation> iterator = apiResults.iterator(); iterator.hasNext(); ) {
                FunctionOperation apiResult = iterator.next();
                boolean funcIsExist = functionOperationService.funcIsExist(apiResult);
                if (funcIsExist) {
                    // iterator.remove();
                }
            }
            log.info("去重后接口个数：{}", apiResults != null ? apiResults.size() : 0);
            // 批量保存api
            if (apiResults != null && apiResults.size() > 0) {
                apiResults.forEach(r -> {
                    sql.append(preFuncSql);
                    // id, app_id, name, request_type, url, order_num, status, remark, logic_del
                    sql.append("'").append(seqId++).append("',");
                    sql.append("'").append(r.getAppId()).append("',");
                    sql.append("'").append(r.getName()).append("',");
                    sql.append("'").append(r.getRequestType()).append("',");
                    sql.append("'").append(r.getUrl()).append("',");
                    sql.append("'").append(r.getOrderNum()).append("',");
                    sql.append("'").append(1).append("',");
                    sql.append("'").append(0).append("');\n");
                });
            }
            File file1 = FileUtil.newFile("D:/1/func_" + appId + ".sql");
            FileUtil.appendString(String.valueOf(sql), file1, "utf-8");
        } catch (Exception e) {
            log.error("爬取url == {} 异常", url);
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) throws Exception {
        SwaggerApiCrawler crawler = new SwaggerApiCrawler("http://172.16.102.169:9020/", "personnel", true);
        crawler.start(1);
        // AntPathMatcher matcher = new AntPathMatcher();
        // boolean b = matcher.match("/basedata/lines/{id}/classsystems",
        // "/basedata/lines/classsystems");
        // System.out.println(b);
        // http://172.16.102.169:9020/personnel/v2/api-docs
    }


    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public void setFunctionOperationService(FunctionOperationService functionOperationService) {
        this.functionOperationService = functionOperationService;
    }

}
