import java.util.ArrayList;
// A catalog of courses
public class Catalog {
	
	ArrayList<Course> courses;
	
	public Catalog() {
		courses = new ArrayList<>();
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
}
