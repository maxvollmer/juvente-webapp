package de.juvente.backend.repositories

import de.juvente.backend.data.Text
import de.juvente.backend.security.JuventeAccessSecuredRepository

interface TextRepository extends JuventeAccessSecuredRepository<Text, String> {
	def Text findByKey(String key);
}
