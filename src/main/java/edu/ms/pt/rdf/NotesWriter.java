package edu.ms.pt.rdf;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class NotesWriter {
	
	private Model model; 	public Model getModel() {return model;}

	public NotesWriter(Model model) {
		super();
		this.model = model;
	}
	
	public NotesWriter() {
		super();
		this.model = ModelFactory.createDefaultModel();
		
		java.nio.file.Path path = Paths.get("src\\main\\webapp\\rdf", "notes.rdf");
		//java.nio.file.Path path = Paths.get("/tmp/deployment/application/ROOT/rdf", "notes.rdf"); //AMAZON
		
		this.model.read(path.toUri().toString(), null, "RDF/XML");
	}
	
	public void createNotes(String resourceName, String notes) {
		Model newModel = ModelFactory.createDefaultModel();
		newModel.createResource(FOAF.NS + resourceName).addProperty(model.createProperty("http://prabhakar.com/" , "notes"), model.createLiteral(notes));
		
		try {
			this.model.add(newModel);
			
			this.model.write(new FileWriter("src\\main\\webapp\\rdf\\notes.rdf"), "RDF/XML");
			//this.model.write(new FileWriter("/tmp/deployment/application/ROOT/rdf/notes.rdf"), "RDF/JSON"); //AMAZON
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main (String [] args) {
		new NotesWriter().createNotes("mjjjjjjjjjjoron", "-++++++++++++------");
		
	}
	
}