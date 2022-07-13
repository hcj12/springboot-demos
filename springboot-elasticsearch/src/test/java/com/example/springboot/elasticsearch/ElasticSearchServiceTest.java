package com.example.springboot.elasticsearch;

import com.example.springboot.elasticsearch.service.ElasticSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ElasticSearchServiceTest {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    public void testCreateIndex() throws Exception {
        elasticSearchService.createIndex("teacher");
    }

    @Test
    public void testAddDocument() throws Exception {
        boolean flag = elasticSearchService.addDocument("teacher", "1",
                "{" +
                        "\"message\": \"25 years old\"" +
                        "}");
        System.out.println(flag);
    }


}
