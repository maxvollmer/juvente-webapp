package de.juvente.backend.security

import org.springframework.stereotype.Component
import org.springframework.security.core.Authentication

@Component("juventeSecurityFilter")
class SecurityFilter {
	def boolean check(Authentication authentication) {
		println("check: "+authentication)
		true
	}
}
