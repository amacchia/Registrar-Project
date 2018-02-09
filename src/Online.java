import java.awt.Component;

import javax.swing.JOptionPane;
// Representation of an online section of a course, meaning the section never meets in person
// and all work is done online.
public class Online extends Section implements RegistrationVerification {
	public Online(Course course, Instructor instructor) {
		super(course, instructor);
	}

	// Returns the string online because the section never meets in person.
	@Override
	String getSchedule() {
		return "Online";
	}

	// Before a student registers for a online section, validate that they meet the requirements
	@Override
	public int validateChoice(Component parentComponent) {
		return JOptionPane.showConfirmDialog(parentComponent,
				"Remote classes require online connectivity and good time management skills."
				+ " Are you sure you want to register for this class?",
				"Online registration verification",
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE);		
	}
}