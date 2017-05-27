package de.juvente.backend.repositories

import de.juvente.backend.data.Text
import org.springframework.data.mongodb.repository.MongoRepository

interface TextRepository extends MongoRepository<Text, String> {
	def Text findByKey(String key);
}
