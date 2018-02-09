import java.util.HashMap;
// Representation of a student at a university
public class Student extends Person {
	private String major;
	// HashMap with CRN as key and the Section as the value that represents the student's current schedule
	private HashMap<Integer, Section> currentSections = new HashMap<>();

	public Student(String firstName, String middleName, String lastName, String email, 
			String ssn, int age, int id, String major) {
		super(firstName, middleName, lastName, email, ssn, age, id);
		this.major = major;
	}
	
	public Student(int id, String lastName) {
		super(id, lastName);
	}
	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	// Adds a given section to the student's schedule
	public void add(Section section) {
		currentSections.put(section.getCRN(), section);
	}
	
	// Drops a given section from the student's schedule
	public void drop(Section section) {
		currentSections.remove(section.getCRN());
	}
	
	// Returns the students current schedule
	public HashMap<Integer, Section> getCurrentSections() {
		return currentSections;
	}	
}