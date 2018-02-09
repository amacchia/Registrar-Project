// Represents an Instructor at a university
public class Instructor extends Person {
	private String department; // The department that the instructor belongs to
	
	public Instructor(String firstName, String middleName, String lastName, String email, String ssn, int age, int id, String department) {
		super(firstName, middleName, lastName, email, ssn, age, id);
		this.department = department;
	}

	public Instructor(int id, String lastName) {
		super(id, lastName);
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
