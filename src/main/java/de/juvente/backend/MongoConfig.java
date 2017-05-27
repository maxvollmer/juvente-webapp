package de.juvente.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "de.juvente.backend.repositories")
public class MongoConfig extends AbstractMongoConfiguration
{
	@Override
	protected String getDatabaseName() {
		return "test";
	}

	@Override
	public Mongo mongo() throws Exception {
		final MongoClient mongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017), 
									MongoClientOptions.builder()
									.socketTimeout(1000)
									.minHeartbeatFrequency(25)
									.heartbeatSocketTimeout(1000)
									.maxWaitTime(10000)
									.build());
		return mongoClient;
	}

	@Override
	protected String getMappingBasePackage() {
		return "de.juvente";
	}
}
