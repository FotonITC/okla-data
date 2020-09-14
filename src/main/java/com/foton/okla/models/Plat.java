package com.foton.okla.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plat")
public class Plat {
	@Id
	@GeneratedValue
	private Long id;

	private String label;

	@Column(columnDefinition = "text")
	private String description;

	private String type;
	private String inventor;
	private int calories;

	@Column(columnDefinition = "text")
	private String preparationSheet;

	private String preparationVideo;
	
	@ElementCollection
	private List<String> ingredients;
	
	@ElementCollection
	private List<String> countries;
	
	@ElementCollection
	private List<Plat> variants;
	
	@ElementCollection
	private List<Plat> similarPlats;
	
	@ElementCollection
	private List<String> prducts;
	
	@ElementCollection
	private List<String> chefs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInventor() {
		return inventor;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getPreparationSheet() {
		return preparationSheet;
	}

	public void setPreparationSheet(String preparationSheet) {
		this.preparationSheet = preparationSheet;
	}

	public String getPreparationVideo() {
		return preparationVideo;
	}

	public void setPreparationVideo(String preparationVideo) {
		this.preparationVideo = preparationVideo;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<Plat> getVariants() {
		return variants;
	}

	public void setVariants(List<Plat> variants) {
		this.variants = variants;
	}

	public List<Plat> getSimilarPlats() {
		return similarPlats;
	}

	public void setSimilarPlats(List<Plat> similarPlats) {
		this.similarPlats = similarPlats;
	}

	public List<String> getPrducts() {
		return prducts;
	}

	public void setPrducts(List<String> prducts) {
		this.prducts = prducts;
	}

	public List<String> getChefs() {
		return chefs;
	}

	public void setChefs(List<String> chefs) {
		this.chefs = chefs;
	}

}
