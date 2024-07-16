package com.pharmacy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEDICINE_TBL_PM")
public class Medicine {

	@Id
	@GeneratedValue
	@Column(name = "MEDICINE_ID")
	private Long id;
	@Column(name = "MEDICINE_NAME")
	private String name;
	@Column(name = "MEDICINE_DESCRIPTION")
	private String description;
	@Column(name = "MEDICINE_PRICE")
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}

}
