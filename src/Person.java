import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String ssn;
	private int age;
	private int id;
	
	public static int oldestAge = 0;
	
	public Person(String firstName, String middleName, String lastName, String email, String ssn, int age, int id) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.ssn = ssn;
		this.age = age;
		this.id = id;
	}
	
	public Person(int id, String lastName) {
		this.id = id;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	
	public String toString() {
		return this.firstName + " " + this.middleName + " " + this.lastName;
	}
	
	public String getEmailDomain() {
		return this.email.substring(email.indexOf("@") + 1);
	}
	
	public String getLast4SSN() {
		return ssn.substring(ssn.length() - 4);
	}
	
	public static boolean isOldEnough(int age) {
		if (age <= 16) {
			return false;
		} else {
			if (age > oldestAge) {
				oldestAge = age;
			}
			return true;
		}
	}
	
	public static boolean isValidEmail(String email) {
		final String regex = "[\\w.]+@[\\w.]+.[\\w.]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(email);
		return m.matches();
	}
	
	public static boolean isValidSSN(final String ssn) {
		final String regex = "\\d\\d\\d-\\d\\d-\\d\\d\\d\\d";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(ssn);
		return m.matches();
	}
}
