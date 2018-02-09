import java.awt.Component;

import javax.swing.JOptionPane;
// Representation of a hybrid section of a course, meaning the course meets once in person the rest is online
public class Hybrid extends Section implements RegistrationVerification {
	private Timeslot meetingTime; // The single meeting time of the hybrid section
	
	public Timeslot getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Timeslot meetingTime) {
		this.meetingTime = meetingTime;
	}

	public Hybrid(Course course, Instructor instructor, Timeslot meetingTime) {
		super(course, instructor);
		this.meetingTime = meetingTime;
	}

	// Returns the meeting time of the hybrid section
	@Override
	String getSchedule() {
		return meetingTime.toString();
	}

	// Before a student registers for a hybrid course, validate that they have online connectivity
	@Override
	public int validateChoice(Component parentComponent) {
		return JOptionPane.showConfirmDialog(parentComponent,
				"Hybrid classes require online connectivity. Are you sure you want to register for this class?",
				"Online registration verification",
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE);	
	}

}
