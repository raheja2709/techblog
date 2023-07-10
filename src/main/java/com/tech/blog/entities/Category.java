package com.tech.blog.entities;

public class Category {

	private Integer cId;
	private String name;
	private String description;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Category(Integer cId, String name, String description) {
		super();
		this.cId = cId;
		this.name = name;
		this.description = description;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
