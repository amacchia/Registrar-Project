// Representation of a section of a course
public abstract class Section {
	private int CRN; // The course registration number
	private Course course; // The course that this is a section of
	private Instructor instructor; // The instructor of the section
	
	private static int CrnCounter = 40000; // Start creating CRN's at 40001
	
	public Section(Course course, Instructor instructor) {
		this.CRN = CrnCounter + 1;
		this.course = course;
		this.instructor = instructor;
		CrnCounter++;
	}
	
	public Course getCourse() {
		return course;
	}

	public int getCRN() {
		return CRN;
	}

	public void setCRN(int cRN) {
		CRN = cRN;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	// Abstract method that returns the meeting times of a section
	abstract String getSchedule();		
}
