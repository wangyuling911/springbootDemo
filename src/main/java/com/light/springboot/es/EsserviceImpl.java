package com.light.springboot.es;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Slf4j
public class EsserviceImpl implements IEsService {

    @Autowired
    public RestHighLevelClient client;


    protected static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        // 默认缓冲限制为100MB，此处修改为30MB。
        builder.setHttpAsyncResponseConsumerFactory(new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    @Override
    public void createIndexRequest(String index) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index)
                .settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 1));

        String a = "{\"properties\":{\"realName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"ignore_above\":256,\"type\":\"keyword\"}},\"analyzer\":\"standard\"},\"name\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"ignore_above\":256,\"type\":\"keyword\"}},\"analyzer\":\"stop\"},\"id\":{\"type\":\"long\"},\"desc\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"ignore_above\":256,\"type\":\"keyword\"}}}}}";

        createIndexRequest.mapping(a, XContentType.JSON);

        try {

            IndicesClient indices = client.indices();
            CreateIndexResponse createIndexResponse = indices.create(createIndexRequest, COMMON_OPTIONS);

            log.info(" 所有节点确认响应 : {}", createIndexResponse.isAcknowledged());
            log.info(" 所有分片的复制未超时 :{}", createIndexResponse.isShardsAcknowledged());
        } catch (IOException e) {
            log.error("创建索引库【{}】失败", index, e);
        }
    }

    @Override
    public void deleteIndexRequest(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        try {
            AcknowledgedResponse response = client.indices().delete(request, COMMON_OPTIONS);
            System.out.println(response.isAcknowledged());
        } catch (IOException e) {
            log.error("删除索引库【{}】失败", index, e);
        }
    }

    @Override
    public void updateRequest(String index, String id, Object object) {
        UpdateRequest updateRequest = new UpdateRequest(index, id);
        updateRequest.doc(BeanUtil.beanToMap(object), XContentType.JSON);
        try {
            client.update(updateRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            log.error("更新索引文档 {" + index + "} 数据 {" + object + "} 失败", e);
        }
    }

    @Override
    public void insertRequest(String index, String id, Object object) {
        IndexRequest indexRequest = new IndexRequest(index).id(id).source(BeanUtil.beanToMap(object), XContentType.JSON);
        try {
            client.index(indexRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            log.error("创建索引文档 {" + index + "} 数据 {" + object + "} 失败", e);
        }

    }


    @Override
    public void deleteRequest(String index, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index, id);
        try {
            client.delete(deleteRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            log.error("删除索引文档 {" + index + "} 数据id {" + id + "} 失败", e);
        }
    }
}
