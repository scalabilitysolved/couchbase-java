package com.scalabilitysolved.couchbase.domain;

import org.joda.time.LocalDateTime;

public class User {

	private String id;
	private String type = "user";
	private String source;
	private String creationDate;

	public User(String id, String source) {
		this.id = id;
		this.source = source;
		this.creationDate = LocalDateTime.now().toString();
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return this.type;
	}

	public String getSource() {
		return this.source;
	}

	public String getCreationDate() {
		return this.creationDate;
	}

}
