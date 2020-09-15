package com.foton.okla;

import java.util.List;

import org.apache.jena.graph.Graph;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.ORG;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.hibernate.Session;

import com.foton.okla.models.Dish;

public class App {
	private static Session session = HibernateUtil.getSessionFactory().openSession();

	public static void main(String[] args) {

		ParameterizedSparqlString qs = new ParameterizedSparqlString(
				"PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
						+ "PREFIX dbo:   <http://dbpedia.org/ontology/>\n"
						+ "PREFIX owl:   <http://www.w3.org/2002/07/owl#>\n"
						+ "PREFIX wikidata: <http://www.wikidata.org/entity/>\n" + "\n" + "select distinct ?uri \n"
						+ "where {\n" + "?uri rdf:type dbo:Food;\n" + "      rdf:type wikidata:Q2095.\n"
						+ "} LIMIT 300");

		QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

		ResultSet dishesResult = exec.execSelect();

		Dish dish;
		String uri;

		while (dishesResult.hasNext()) {
			dish = new Dish();
			uri = dishesResult.nextSolution().getResource("uri").toString();
			System.out.println(
					"PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
					"PREFIX dbo:   <http://dbpedia.org/ontology/>\n" + 
					"PREFIX owl:   <http://www.w3.org/2002/07/owl#>\n" + 
					"PREFIX wikidata: <http://www.wikidata.org/entity/>\n" + 
					"\n" + 
					"select ?label ?description ?types ?calories ?subjects ?images ?ingredients ?countries ?variants ?companies\n" + 
					"\n" + 
					"where {\n" + 
					"<http://dbpedia.org/resource/Pizza>  rdfs:label   ?label .\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Pizza>  dbo:abstract   ?description .}\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Pizza>  dbo:type       ?types .}\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Pizza>  dbp:calories   ?calories .}\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Pizza>  dct:subject    ?subjects .}\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Pizza>  dbo:thumbnail  ?images .}\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Pizza>  dbo:ingredient ?ingredients .}\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Pizza>  dbo:country    ?countries .}\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Pizza>  dbo:hasVariant ?variants .}\n" + 
					"OPTIONAL {<http://dbpedia.org/resource/Ramen>  dbo:product    ?companies .}\n" + 
					"\n" + 
					"FILTER (LANG(?label)='en' && LANG(?description)='en')\n" + 
					"} LIMIT 300");
			
		}

	}

	public void saveInDb(Dish dish) {
		session.beginTransaction();

		session.save(dish);

		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
}