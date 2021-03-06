package de.juvente.backend.security

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import org.springframework.security.access.prepost.PreAuthorize

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@juventeSecurityFilter.check(authentication)")
annotation SecurityCheck {
	
}