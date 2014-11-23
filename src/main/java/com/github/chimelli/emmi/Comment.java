package com.github.chimelli.emmi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	@ManyToOne
	@JsonIgnore
	private Picture picture;
	private String name;
	private String comment;
	
	public Comment() {
		
	}
	
	public Comment(Picture picture, String name, String comment) {
		this.picture = picture;
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

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}
}
