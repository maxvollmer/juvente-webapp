package de.juvente.backend.data

import org.eclipse.xtend.lib.annotations.Accessors
import org.springframework.data.annotation.Id
import org.eclipse.xtend.lib.annotations.AccessorType

@Accessors
class Member {
	
	@Id
	@Accessors(AccessorType.PUBLIC_GETTER)
	private String id;
	
}