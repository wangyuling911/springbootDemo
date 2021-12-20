package com.light.springboot.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author haopeng
 */
@Service
@Slf4j
public class LolService extends EsserviceImpl {

    public void insertBach(String index, List<Lol> list) {
        if (list.isEmpty()) {
            log.warn("bach insert index but list is empty ...");
            return;
        }
        list.forEach((lol) -> {
            super.insertRequest(index, lol.getId().toString(), lol);
        });
    }

    public List<Lol> searchList(String index) {
        SearchResponse searchResponse = search(index);
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Lol> lolList = new ArrayList<>();
        Arrays.stream(hits).forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Lol lol = BeanUtil.mapToBean(sourceAsMap, Lol.class, true);
            lolList.add(lol);
        });
        return lolList;
    }

    public List<Lol> searchList(String index, String id) {
        SearchResponse searchResponse = searchJingZhun(index, id);
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Lol> lolList = new ArrayList<>();
        Arrays.stream(hits).forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Lol lol = BeanUtil.mapToBean(sourceAsMap, Lol.class, true);
            lolList.add(lol);
        });
        return lolList;
    }

    protected SearchResponse search(String index) {

        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //bool符合查询
        //BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
        //        .filter(QueryBuilders.matchQuery("name", "盖伦"))
        //        .must(QueryBuilders.matchQuery("desc", "部落"))
        //        .should(QueryBuilders.matchQuery("realName", "光辉"));

        //分页
        //searchSourceBuilder.from(1).size(2);
        // 排序
        //searchSourceBuilder.sort("", SortOrder.DESC);

        ////误拼写时的fuzzy模糊搜索方法 2表示允许的误差字符数
        //QueryBuilders.fuzzyQuery("title", "ceshi").fuzziness(Fuzziness.build("2"));
        searchRequest.source(searchSourceBuilder);
        System.out.println(searchSourceBuilder.toString());
        System.out.println(JSONUtil.parseObj(searchSourceBuilder.toString()).toStringPretty());
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    protected SearchResponse searchJingZhun(String index, String id) {

        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //bool符合查询
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder().
                must(QueryBuilders.matchQuery("id", id));
        searchSourceBuilder.query(boolQueryBuilder);

        //分页
        //searchSourceBuilder.from(1).size(2);
        // 排序
        //searchSourceBuilder.sort("", SortOrder.DESC);

        ////误拼写时的fuzzy模糊搜索方法 2表示允许的误差字符数
        //QueryBuilders.fuzzyQuery("title", "ceshi").fuzziness(Fuzziness.build("2"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

}
