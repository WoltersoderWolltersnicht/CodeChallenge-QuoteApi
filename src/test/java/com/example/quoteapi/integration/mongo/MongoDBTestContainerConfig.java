package com.example.quoteapi.integration.mongo;

import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class MongoDBTestContainerConfig {
    
    public static MongoDBContainer mongoDBContainer =  new MongoDBContainer(DockerImageName.parse("mongo:latest"))
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        var mappedPort = mongoDBContainer.getMappedPort(27017);
        System.out.println("hy ");
        System.setProperty("MONGODB_HOST", "localhost");
        System.setProperty("MONGODB_DATABASE", "Quotes");
        System.setProperty("MONGODB_PORT", String.valueOf(mappedPort));
    }
}