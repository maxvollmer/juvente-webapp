package de.juvente.spring.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

@EnableGlobalMethodSecurity(prePostEnabled=true)
class SecurityConfig {

	@Autowired
	def configureGlobal(AuthenticationManagerBuilder auth) {
	}

}
