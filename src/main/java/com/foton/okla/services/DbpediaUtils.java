package com.foton.okla.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import com.foton.okla.models.Dish;

public class DbpediaUtils {

	public static void populateDishRS(Dish dish, ResultSet dishResult) {
		dishResult.hasNext();
		QuerySolution qs = dishResult.next();
		dish.setLabel(qs.getLiteral("label").toString().replace("@en", ""));

		dish.setDescription(qs.getLiteral("description").toString().replace("@en", ""));

		if (qs.getLiteral("calories") != null) {
			dish.setCalories(qs.getLiteral("calories").toString());
		}

		List<String> companies = new ArrayList<String>();
		List<String> countries = new ArrayList<String>();
		List<String> images = new ArrayList<String>();
		List<String> ingredients = new ArrayList<String>();
		List<String> subjects = new ArrayList<String>();
		List<String> types = new ArrayList<String>();
		List<String> variants = new ArrayList<String>();

		if (qs.getResource("images") != null) {
			images.add(qs.getResource("images").toString());
		}

		dish.setImages(images);

		while (dishResult.hasNext()) {
			qs = dishResult.nextSolution();
			if (qs.getLiteral("companies") != null) {
				addIfUnique(companies,
						qs.getLiteral("companies").toString().replace("http://dbpedia.org/resource/", ""));
			}
			if (qs.getResource("countries") != null) {
				addIfUnique(countries,
						qs.getResource("countries").toString().replace("http://dbpedia.org/resource/", ""));
			}
			if (qs.getResource("ingredients") != null) {
				addIfUnique(ingredients,
						qs.getResource("ingredients").toString().replace("http://dbpedia.org/resource/", ""));
			}
			if (qs.getResource("subjects") != null) {
				addIfUnique(subjects,
						qs.getResource("subjects").toString().replace("http://dbpedia.org/resource/Category:", ""));
			}
			if (qs.getResource("types") != null) {
				addIfUnique(types, qs.getResource("types").toString().replace("http://dbpedia.org/resource/", ""));
			}
			if (qs.getResource("variants") != null) {
				addIfUnique(variants, qs.getResource("variants").toString().replace("http://dbpedia.org/resource/", ""));
			}
		}
		dish.setCompanies(companies);
		dish.setCountries(countries);

		dish.setIngredients(ingredients);
		dish.setSubjects(subjects);
		dish.setTypes(types);
		dish.setVariants(variants);
	};

	private static void addIfUnique(List<String> list, String str) {
		if (!list.contains(str)) {
			list.add(str);
		}
	}
}
