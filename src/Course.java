// Representation of course
public class Course {
	private String courseNumber; // The course number
	private String title;	// The name of the course
	private Department department;	// The department of the course
	
	public Course(String courseNumber, String title, Department department) {
		this.courseNumber = courseNumber;
		this.title = title;
		this.department = department;
	}
	
	public Department getDepartment() {
		return department;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}