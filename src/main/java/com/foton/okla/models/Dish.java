package com.foton.okla.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dishes")
public class Dish {
	@Id
	@GeneratedValue
	private Long id;

	private String dbUri;

	private boolean fromDb;

	private String label;

	@Column(columnDefinition = "text")
	private String description;

	@ElementCollection
	private List<String> types;
	private String inventor;
	private int calories;

	@ElementCollection
	private List<String> subjects;

	@ElementCollection
	private List<String> images;

	@Column(columnDefinition = "text")
	private String preparationSheet;

	private String preparationVideo;

	@ElementCollection
	private List<String> ingredients;

	@ElementCollection
	private List<String> countries;

	@ElementCollection
	private List<Dish> variants;

	@ElementCollection
	private List<Dish> similarDishes;

	@ElementCollection
	private List<String> companies;

	@ElementCollection
	private List<String> chefs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDbUri() {
		return dbUri;
	}

	public void setDbUri(String dbUri) {
		this.dbUri = dbUri;
	}

	public boolean isFromDb() {
		return fromDb;
	}

	public void setFromDb(boolean fromDb) {
		this.fromDb = fromDb;
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

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
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

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
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

	public List<Dish> getVariants() {
		return variants;
	}

	public void setVariants(List<Dish> variants) {
		this.variants = variants;
	}

	public List<Dish> getSimilarDishes() {
		return similarDishes;
	}

	public void setSimilarDishes(List<Dish> similarPlats) {
		this.similarDishes = similarPlats;
	}

	public List<String> getCompanies() {
		return companies;
	}

	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}

	public List<String> getChefs() {
		return chefs;
	}

	public void setChefs(List<String> chefs) {
		this.chefs = chefs;
	}

}