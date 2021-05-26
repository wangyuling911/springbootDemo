package com.wyl.provide.es.repository;

import com.wyl.provide.es.document.KnowledgeEntryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface knowledgeEntryDocumentRepository extends ElasticsearchRepository<KnowledgeEntryDocument,String> {
}
