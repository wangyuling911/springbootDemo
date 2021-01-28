package com.light.springboot.config;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.light.springboot.es.repository"})
public class ElasticSearchConfig {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Bean
    public Client transportClient() throws Exception {
        TransportClientFactoryBean factory = new TransportClientFactoryBean();
        factory.setClusterNodes(elasticsearchHost);
        factory.setClusterName(clusterName);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception {

        return new ElasticsearchTemplate(transportClient());
    }
}
