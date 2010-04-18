package com.thoughtworks.mingle.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Murmur {
	private int id;
	private Author author;
	private String body;
	private Date createdAt;

	public void setId(String string) {
		this.id = Integer.parseInt(string);
	}

	public int getId() {
		return id;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getCreatedAtString() {
		long milli = new Date().getTime() - createdAt.getTime();
		String format = "(posted %s ago)";

		Long seconds = milli / 1000;
		if (seconds <= 59)
			return String.format(format, seconds + " seconds");

		Long minutes = seconds / 60;
		if (minutes <= 2)
			return String.format(format, "a minute");
		if (minutes <= 60)
			return String.format(format, minutes + " minutes");

		Long hours = minutes / 60;
		if (hours <= 23)
			return String.format(format, hours + " hours");

		Long days = hours / 24;
		return String.format(format, days + " days");
	}

	public void setCreatedAtFrom(String dateString) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
			createdAt = dateFormat.parse(dateString);
		} catch (Exception e) {
			createdAt = new Date();
		}
	}
}