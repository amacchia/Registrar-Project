import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {

	public static void main(String[] args) {		
		// Build the objects needed to represent the registration process
		Catalog catalog = new Catalog();
		Term term = new Term("Fall 2017");  // change to the current term
		Faculty faculty = new Faculty();
		Student student = new Student(1, "Macchia");  // change to your last name

		//Create List of Time slots
		List<Timeslot> timeslots = new ArrayList<>();
		
		//Create List of Sections
		List<Section> sections = new ArrayList<>();
		
		// Populate these objects
		createInstructors(faculty);  // Send the faculty object to method for instructor population
		createCourses(catalog);	     // Send the catalog object to method for course population
		createTimeslots(timeslots);  // Send the time slots object to method for time slot population
		// Send the time slots, sections, catalog, and faculty to method for section population
		createSections(timeslots, sections, catalog, faculty);
		// Add the sections to the term
		for (Section section : sections) {
			term.addSection(section);
		}
		
		@SuppressWarnings("unused")
		RegistrarGUI gui = new RegistrarGUI(term, student);	
	}

	private static void createInstructors(Faculty faculty) {
		faculty.addInstructor(new Instructor(101, "Johnson"));
		faculty.addInstructor(new Instructor(102, "Kay"));
		faculty.addInstructor(new Instructor(103, "Xu"));
		faculty.addInstructor(new Instructor(104, "Mulligan"));
		faculty.addInstructor(new Instructor(105, "Muldoon"));
		faculty.addInstructor(new Instructor(106, "Stanzione"));
		faculty.addInstructor(new Instructor(107, "Brady"));
		faculty.addInstructor(new Instructor(108, "Sawyer"));
		faculty.addInstructor(new Instructor(109, "Brown"));
		faculty.addInstructor(new Instructor(110, "Harrison"));
		faculty.addInstructor(new Instructor(111, "Ford"));
		faculty.addInstructor(new Instructor(112, "Danzinger"));
		faculty.addInstructor(new Instructor(113, "Clarke"));
		faculty.addInstructor(new Instructor(114, "Abraham"));
		faculty.addInstructor(new Instructor(115, "Perkowski"));
		faculty.addInstructor(new Instructor(116, "Brando"));
	}

	private static void createCourses(Catalog catalog) {
		catalog.addCourse(new Course("ART 01.101", "Art Appreciation", Department.ART));	
		catalog.addCourse(new Course("ART 01.201", "Painting with Oils", Department.ART));
		catalog.addCourse(new Course("ART 01.202", "Painting with Water Colors", Department.ART));
		catalog.addCourse(new Course("BIOL 01.110", "Genetics", Department.BIOLOGY));
		catalog.addCourse(new Course("BIOL 04.301", "Anatomy and Physiology", Department.BIOLOGY));		
		catalog.addCourse(new Course("CHEM 01.101", "Introduction to Chemistry", Department.CHEMISTRY));
		catalog.addCourse(new Course("CHEM 01.201", "Organic Chemistry", Department.CHEMISTRY));
		catalog.addCourse(new Course("CHEM 01.301", "Analytical Chemistry", Department.CHEMISTRY));
		catalog.addCourse(new Course("CSC 04.114", "Object Oriented Programming", Department.COMPUTER_SCIENCE));
		catalog.addCourse(new Course("CSC 04.301", "Human Computer Interaction", Department.COMPUTER_SCIENCE));
		catalog.addCourse(new Course("CSC 07.211", "Artificial Intelligence", Department.COMPUTER_SCIENCE));
		catalog.addCourse(new Course("CSC 04.370", "Software Engineering", Department.COMPUTER_SCIENCE));
		catalog.addCourse(new Course("CSC 04.210", "Data Structures and Algorithms", Department.COMPUTER_SCIENCE));
		catalog.addCourse(new Course("ECON 01.101", "Microeconomics", Department.ECONOMICS));
		catalog.addCourse(new Course("HIS 01.101", "Western Civilization", Department.HISTORY));
		catalog.addCourse(new Course("HIS 01.331", "Civil Wars", Department.HISTORY));
		catalog.addCourse(new Course("MUS 01.214", "The Genres of Rock Music", Department.MUSIC));
		catalog.addCourse(new Course("PHIL 01.111", "Ethics", Department.PHILOSOPHY));
		catalog.addCourse(new Course("PHIL 01.221", "Existentialism", Department.PHILOSOPHY));
		catalog.addCourse(new Course("PHY 02.121", "Introduction to Mechanics", Department.PHYSICS));
		catalog.addCourse(new Course("PSY 04.114", "Abnormal Psychology", Department.PHYSICS));		
	}
	
	// Creates time slots from 8am Monday to 6pm Friday
	private static void createTimeslots(List<Timeslot> timeslots) {

		// Create a time slot for Monday - Friday
		for (DayOfWeek weekday = DayOfWeek.MONDAY; !weekday.equals(DayOfWeek.SATURDAY); weekday = weekday.plus(1)) {
			
			// Create a time slot for each 50 minute interval from 8am to 6pm
			for (LocalTime time = LocalTime.of(8, 0); time.isBefore(LocalTime.of(18, 0)); time = time.plusMinutes(50)) {
			timeslots.add(new Timeslot(weekday, time, time.plusMinutes(50)));
			}			
		}
	}

	// Creates Sections
	private static void createSections(List<Timeslot> timeslots, List<Section> sections,
			Catalog catalog, Faculty faculty) {
				
		// Use random number generator to randomly index into timeslots list and instructors arraylist
		Random randomIndex = new Random();
		
		// For each course create a Traditional, Hybrid, and Online section
		for (Course course : catalog.getCourses()) {
			Instructor instructor = faculty.getinstructors().
					get(randomIndex.nextInt(faculty.getinstructors().size())); // Get a random instructor for a course
			
			sections.add(new Traditional(course,								// Course
					instructor, 												// Instructor
					timeslots.get(randomIndex.nextInt(timeslots.size())), 		// First Meeting Time
					timeslots.get(randomIndex.nextInt(timeslots.size())))); 	// Second Meeting Time
			
			sections.add(new Hybrid(course,										// Course
					instructor, 												// Instructor
					timeslots.get(randomIndex.nextInt(timeslots.size())))); 	// Meeting Time
			
			sections.add(new Online(course,	// course
					instructor)); 			// Instructor
		}
	}
}