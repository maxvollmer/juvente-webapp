package de.juvente.backend.data;

import org.springframework.data.annotation.Id;

public class Text
{
	@Id
	private String id;

	private String key;
	private String de;
	private String en;

	public Text() {
	}

	public Text(final String key, final String de, final String en) {
		this.key = key;
		this.de = de;
		this.en = en;
	}

	public String getKey() {  
		return key;  
	}

	public void setKey(final String key) {  
		this.key = key;  
	}

	public String getDe() {  
		return de;  
	}

	public void setDe(final String de) {  
		this.de = de;  
	}

	public String getEn() {  
		return en;  
	}

	public void setEn(final String en) {  
		this.en = en;  
	}

	public String getId() {  
		return id;  
	}
}
