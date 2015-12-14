//package com.pixelworld.config;
//
//import com.mongodb.Mongo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//import javax.inject.Inject;
//
///**
// * Created by BladeInShine on 15/12/13.
// */
//@Configuration
//@EnableMongoRepositories("com.pixelworld.repository")
//@PropertySource("classpath:application.properties")
//@Import(value = MongoAutoConfiguration.class)
//public class DatabaseConfig extends AbstractMongoConfiguration {
//
//    private final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
//
//    @Inject
//    private Mongo mongo;
//
//    @Inject
//    private MongoProperties mongoProperties;
//
//    @Bean
//    public ValidatingMongoEventListener validatingMongoEventListener() {
//        return new ValidatingMongoEventListener(validator());
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        return new LocalValidatorFactoryBean();
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return mongoProperties.getDatabase();
//    }
//
//    @Override
//    public Mongo mongo() throws Exception {
//        return mongo;
//    }
//}
