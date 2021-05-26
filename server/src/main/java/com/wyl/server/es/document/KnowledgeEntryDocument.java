package com.wyl.server.es.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Copyright 2017年6月7日, Easemob.inc
 * All rights reserved.
 *
 * @author liuzheng
 */
@Data
@Document(indexName = "knowledgeentry", type = "knowledgeentry", shards = 20)
public class KnowledgeEntryDocument implements Serializable{
    
    private static final long serialVersionUID = -1704684704619851593L;

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Long)
    private Long teamId;

    @Field(type = FieldType.Integer)
    private Integer tenantId;

    @Field(type = FieldType.Keyword)
    private String agentUserId;


}

