package de.juvente.backend

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import com.mongodb.MongoClient
import com.mongodb.ServerAddress
import com.mongodb.MongoClientOptions

@Configuration
@EnableMongoRepositories(basePackages = "de.juvente.backend.repositories")
class MongoConfig extends AbstractMongoConfiguration {

	override getDatabaseName() {
		"test"
	}

	override getMappingBasePackage() {
		"de.juvente"
	}

	override mongo() {
		new MongoClient(new ServerAddress("127.0.0.1", 27017), 
							MongoClientOptions.builder
							.socketTimeout(1000)
							.minHeartbeatFrequency(25)
							.heartbeatSocketTimeout(1000)
							.maxWaitTime(10000)
							.build);
	}
}