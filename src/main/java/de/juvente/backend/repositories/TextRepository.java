package de.juvente.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.juvente.backend.data.Text;

public interface TextRepository extends MongoRepository<Text, String>
{
	Text findByKey(String language);
}
