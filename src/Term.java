import java.util.ArrayList;
// Representation of a semester term at university
public class Term {
	String term;
	
	ArrayList<Section> sections;
	
	public Term(String term) {
		this.term = term;
		sections = new ArrayList<>();
	}
	
	// Adds a section to a term
	public void addSection(Section section) {
		sections.add(section);
	}
	
	public ArrayList<Section> getSections() {
		return sections;
	}
}
