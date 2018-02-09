import java.util.ArrayList;
// An array list of instructors that represents the faculty of a university
public class Faculty {

	ArrayList<Instructor> instructors;
	
	public Faculty() {
		instructors = new ArrayList<>();
	}
	
	public void addInstructor(Instructor instructor) {
		instructors.add(instructor);
	}
	
	public ArrayList<Instructor> getinstructors(){
		return instructors;
	}
	
}
