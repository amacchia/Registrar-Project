// Representation of a traditional section,
// meaning the section meets twice a week
public class Traditional extends Section {
	private Timeslot firstMeeting;	// The section's first meeting
	private Timeslot secondMeeting;	// The section's second meeting

	public Traditional(Course course, Instructor instructor,
			Timeslot firstMeeting,
			Timeslot secondMeeting) {
		super(course, instructor);
		this.firstMeeting = firstMeeting;
		this.secondMeeting = secondMeeting;
	}

	public Timeslot getFirstMeeting() {
		return firstMeeting;
	}

	public Timeslot getSecondMeeting() {
		return secondMeeting;
	}

	// Returns the meeting times of the traditional section
	@Override
	String getSchedule() {
		return firstMeeting.toString() + ", " + secondMeeting.toString();
	}
}