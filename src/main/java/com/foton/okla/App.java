package com.foton.okla;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.hibernate.Session;

import com.foton.okla.models.Dish;
import com.foton.okla.services.DbpediaUtils;

public class App {
	private static Session session = HibernateUtil.getSessionFactory().openSession();

	public static void main(String[] args) {

		ParameterizedSparqlString qs = new ParameterizedSparqlString(
				"PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
						+ "PREFIX dbo:   <http://dbpedia.org/ontology/>\n"
						+ "PREFIX owl:   <http://www.w3.org/2002/07/owl#>\n"
						+ "PREFIX wikidata: <http://www.wikidata.org/entity/>\n" + "\n" + "select distinct ?uri \n"
						+ "where {\n" + "?uri rdf:type dbo:Food;\n" + "      rdf:type wikidata:Q2095.\n"
						+ "} LIMIT 1000");

		QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

		ResultSet dishesResult = exec.execSelect();

		Dish dish;
		String uri;
		ResultSet dishResult;

		String dishQuery = "PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX dbo:   <http://dbpedia.org/ontology/>\n" + "PREFIX owl:   <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX wikidata: <http://www.wikidata.org/entity/>\n" 
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX dbp:  <http://dbpedia.org/property/>\n"
				+ "PREFIX dc:   <http://purl.org/dc/elements/1.1/>\n"
				+ "PREFIX dct:  <http://purl.org/dc/terms/>\n"
				+ "select ?label ?description ?types ?calories ?subjects ?images ?ingredients ?countries ?variants ?companies\n"
				+ "\n" + "where {\n" + "{0}  rdfs:label   ?label .\n"
				+ "OPTIONAL {{0}  dbo:abstract   ?description .}\n" + "OPTIONAL {{0}  dbo:type       ?types .}\n"
				+ "OPTIONAL {{0}  dbp:calories   ?calories .}\n" + "OPTIONAL {{0}  dct:subject    ?subjects .}\n"
				+ "OPTIONAL {{0}  dbo:thumbnail  ?images .}\n" + "OPTIONAL {{0}  dbo:ingredient ?ingredients .}\n"
				+ "OPTIONAL {{0}  dbo:country    ?countries .}\n" + "OPTIONAL {{0}  dbo:hasVariant ?variants .}\n"
				+ "OPTIONAL {{0}  dbo:product    ?companies .}\n" + "\n"
				+ "FILTER (LANG(?label)='en' && LANG(?description)='en')\n" + "} LIMIT 300";

		while (dishesResult.hasNext()) {
			dish = new Dish();
			uri = dishesResult.nextSolution().getResource("uri").toString();

			dish.setDbUri(uri);
			dish.setFromDb(true);
			
			qs = new ParameterizedSparqlString(dishQuery.replace("{0}", "<" + uri + ">"));
			exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());
			dishResult = exec.execSelect();

			DbpediaUtils.populateDishRS(dish, dishResult);
			
			saveInDb(dish);
		}
		HibernateUtil.shutdown();
	}

	public static void saveInDb(Dish dish) {
		session.beginTransaction();

		session.save(dish);

		session.getTransaction().commit();
		
	}
}