package com.light.springboot.controller;

import com.light.springboot.es.document.KnowledgeEntryDocument;
import com.light.springboot.es.repository.knowledgeEntryDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private knowledgeEntryDocumentRepository knowledgeEntryDocumentRepository;

    @GetMapping("/helloworld")
    public String helloworld() {
        // boolean index = elasticsearchTemplate.createIndex(KnowledgeEntryDocument.class);
        //elasticsearchTemplate.putMapping(KnowledgeEntryDocument.class);
        // boolean b = elasticsearchTemplate.deleteIndex(KnowledgeEntryDocument.class);
        KnowledgeEntryDocument knowledgeEntryDocument = new KnowledgeEntryDocument();
        knowledgeEntryDocument.setId("1");
        knowledgeEntryDocument.setTeamId(134L);
        knowledgeEntryDocument.setTenantId(29146);
        knowledgeEntryDocument.setAgentUserId("agentUserId");
        KnowledgeEntryDocument save = knowledgeEntryDocumentRepository.save(knowledgeEntryDocument);
        return String.valueOf(save);
    }

    @GetMapping("/delete")
    public String delete() {
        boolean b = elasticsearchTemplate.deleteIndex(KnowledgeEntryDocument.class);
        return String.valueOf(b);
    }

    @GetMapping("/createIndex")
    public String createIndex() {
        boolean index = elasticsearchTemplate.createIndex(KnowledgeEntryDocument.class);

        return String.valueOf(index);
    }

    @GetMapping("/putMapping")
    public String putMapping() {
        boolean b = elasticsearchTemplate.putMapping(KnowledgeEntryDocument.class);
        return String.valueOf(b);
    }

    @GetMapping("/search")
    public String search() {
        return String.valueOf(true);
    }


}