package de.juvente.backend.data

import org.eclipse.xtend.lib.annotations.Accessors
import org.springframework.data.annotation.Id
import org.eclipse.xtend.lib.annotations.AccessorType

@Accessors
class Text {

	@Id
	@Accessors(AccessorType.PUBLIC_GETTER)
	private String key;

	private String de;
	private String en;

	new() {}

	new(String key, String de, String en) {
		this.key = key;
		this.de = de;
		this.en = en;
	}

}