package com.light.springboot.es.repository;

import com.light.springboot.es.document.KnowledgeEntryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface knowledgeEntryDocumentRepository extends ElasticsearchRepository<KnowledgeEntryDocument,String> {
}
