package com.github.chimelli.emmi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private String name;
	private String comment;
	
	public Comment() {
		
	}
	
	public Comment(String name, String comment) {
		this.name = name;
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
