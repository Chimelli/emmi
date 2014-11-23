package com.github.chimelli.emmi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Picture {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	@Column(columnDefinition = "blob")
	private byte[] picture;
	private String caption;
	
	public Picture() {
		
	}
	
	public Picture(byte[] picture, String caption) {
		this.picture = picture;
		this.caption = caption;
	}
	
	public long getId() {
		return id;
	}

	public byte[] getPicture() {
		return picture;
	}
	
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
}
