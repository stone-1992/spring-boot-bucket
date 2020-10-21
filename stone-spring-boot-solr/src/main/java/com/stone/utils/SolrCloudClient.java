//package com.stone.utils;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrQuery.ORDER;
//import org.apache.solr.client.solrj.impl.CloudSolrClient;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocumentList;
//
//
///**
// * solr全文搜索引擎集群客户端
// *
// * @title
// * @author 龚进
// * @date 2018年6月4日
// * @version 1.0
// */
//public class SolrCloudClient {
//
//	private CloudSolrClient cloudClient;
//
//	/**
//	 * zk集群地址
//	 */
//	private static List<String> zkHosts;
//	// = Lists.newArrayList("172.16.102.11:2181", "172.16.102.12:2181",
//	// "172.16.102.13:2181");
//
//	static {
//		// 读取配置文件zk地址
//		SolrServerConfig config = (SolrServerConfig) SpringContextUtils.getBean("solrServerConfig");
//		if (BeanUtils.isNotBlank(config)) {
//			String zkHost = config.getZkHost();
//			String[] zkHostArr = zkHost.split(",");
//			zkHosts = Arrays.asList(zkHostArr);
//		}
//	}
//
//	/**
//	 * 默认查询条数
//	 */
//	private static final int DEFAULT_ROWS = 20;
//
//	public SolrCloudClient(String core) {
//		Optional<String> zkChroot = Optional.ofNullable(null);
//		cloudClient = new CloudSolrClient.Builder(zkHosts, zkChroot).withConnectionTimeout(5000).withSocketTimeout(5000)
//				.build();
//		cloudClient.setDefaultCollection(core);
//	}
//
//	/**
//	 * 查询结果集合
//	 *
//	 * @param queryCondition
//	 *            查询条件(例 查询所有- *:*,并且 - and,或 - or,范围 [* TO *])
//	 * @param rows
//	 *            返回记录数
//	 * @return
//	 * @throws Exception
//	 */
//	public SolrDocumentList queryList(String queryCondition, Integer rows) throws Exception {
//		SolrQuery query = new SolrQuery();
//		query.setQuery(queryCondition);
//		if (null == rows) {
//			query.setRows(DEFAULT_ROWS);
//		} else {
//			query.setRows(rows);
//		}
//		query.setSort("uuid", ORDER.desc);
//		QueryResponse response = cloudClient.query(query);
//		SolrDocumentList documents = response.getResults();
//		// 关闭资源
//		// cloudClient.close();
//		return documents;
//	}
//
//	/**
//	 * 查询集合返回javabean
//	 *
//	 * @param queryCondition
//	 * @param rows
//	 * @param clazz
//	 * @return
//	 * @throws Exception
//	 */
//	public <E> List<E> queryList(String queryCondition, Integer rows, Class<E> clazz, String... filterQuery)
//			throws Exception {
//		SolrQuery query = new SolrQuery();
//		query.setQuery(queryCondition);
//		if (filterQuery.length > 0) {
//			query.setFilterQueries(filterQuery);
//		}
//		if (null == rows) {
//			query.setRows(DEFAULT_ROWS);
//		} else {
//			query.setRows(rows);
//		}
//		// 设置100%匹配q的搜索字段
//		query.add("defType", "edismax");
//		query.add("mm", "100%");
//		query.setSort("uuid", ORDER.desc);
//		QueryResponse response = cloudClient.query(query);
//		List<E> list = response.getBeans(clazz);
//		// 关闭资源
//		// cloudClient.close();
//		return list;
//	}
//
//	/**
//	 * 添加文档
//	 *
//	 * @param bean
//	 * @throws Exception
//	 */
//	public <E> void addBean(E bean) throws Exception {
//		// 添加对象
//		cloudClient.addBean(bean);
//		// 提交操作
//		// cloudClient.commit();
//		// 关闭资源
//		// cloudClient.close();
//	}
//
//	/**
//	 * 批量添加文档
//	 *
//	 * @param beans
//	 * @throws Exception
//	 */
//	public <E> void addBeans(List<E> beans) throws Exception {
//		// 添加对象
//		cloudClient.addBeans(beans);
//		// 提交操作
//		cloudClient.commit();
//		// 关闭资源
//		// cloudClient.close();
//	}
//
//	/**
//	 * 删除文档 通过查询条件
//	 *
//	 * @param condition
//	 * @throws Exception
//	 */
//	public void delete(String condition) throws Exception {
//		// 执行删除
//		cloudClient.deleteByQuery(condition);
//		// 提交操作
//		cloudClient.commit();
//		// 关闭资源
//		// cloudClient.close();
//	}
//
//	/**
//	 * 根据条件统计
//	 *
//	 * @param queryCondition
//	 * @param filterQuery
//	 * @return
//	 * @throws Exception
//	 */
//	public long countByCondition(String queryCondition, String... filterQuery) throws Exception {
//		SolrQuery query = new SolrQuery();
//		query.setQuery(queryCondition);
//		if (filterQuery.length > 0) {
//			query.setFilterQueries(filterQuery);
//		}
//		query.setRows(DEFAULT_ROWS);
//		// 设置100%匹配q的搜索字段
//		query.add("defType", "edismax");
//		query.add("mm", "100%");
//		query.setSort("uuid", ORDER.desc);
//		QueryResponse response = cloudClient.query(query);
//		long count = response.getResults().getNumFound();
//		return count;
//	}
//
//	public static void main(String[] args) throws Exception {
//
//	}
//}
