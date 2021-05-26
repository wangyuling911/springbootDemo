package com.wyl.server.es.repository;

import com.wyl.server.es.document.KnowledgeEntryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface knowledgeEntryDocumentRepository extends ElasticsearchRepository<KnowledgeEntryDocument,String> {
}
